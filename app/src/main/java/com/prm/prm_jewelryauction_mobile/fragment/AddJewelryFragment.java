package com.prm.prm_jewelryauction_mobile.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.adapter.MaterialAdapter.MaterialAdapter;
import com.prm.prm_jewelryauction_mobile.model.MaterialModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddJewelryFragment extends Fragment {
    private static final int REQUEST_IMAGE_PICK = 1;
    private static final int REQUEST_PERMISSION = 100;
    private RecyclerView rvMaterials;
    private MaterialAdapter materialAdapter;
    private List<MaterialModel> availableMaterials;
    private List<MaterialModel> selectedMaterials;
    private ImageView btnAddMaterial, imgPreview;
    private Spinner spinnerCategory, spinnerCollection, spinnerBrand, spinnerCondition, rgSex;
    private EditText etJewelryName, etJewelryDescription, etSize;
    private Button btnSubmit, btnUploadImage;
    private Uri selectedImageUri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_jewelry, container, false);
        initializeViews(view);
        setupSpinners();
        availableMaterials = generateFakeMaterials();
        selectedMaterials = new ArrayList<>();
        setupRecyclerView();
        btnAddMaterial.setOnClickListener(v -> {
            addMaterialRow();
            checkAddButtonState();  // Check if the button should be disabled
        });
        btnUploadImage.setOnClickListener(v -> {
            if (checkStoragePermission()) {
                openImagePicker();
            }
        });

        return view;
    }

    private void initializeViews(View view) {
        rvMaterials = view.findViewById(R.id.rvMaterials);
        btnAddMaterial = view.findViewById(R.id.btnAddMaterial);
        spinnerCategory = view.findViewById(R.id.spinnerCategory);
        spinnerCollection = view.findViewById(R.id.spinnerCollection);
        spinnerBrand = view.findViewById(R.id.spinnerBrand);
        spinnerCondition = view.findViewById(R.id.spinnerCondition);
        rgSex = view.findViewById(R.id.rgSex);
        etJewelryName = view.findViewById(R.id.etJewelryName);
        etJewelryDescription = view.findViewById(R.id.etJewelryDescription);
        etSize = view.findViewById(R.id.etSize);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        rvMaterials = view.findViewById(R.id.rvMaterials);
        btnAddMaterial = view.findViewById(R.id.btnAddMaterial);
        imgPreview = view.findViewById(R.id.imgPreview);
        btnUploadImage = view.findViewById(R.id.btnUploadImage);

    }

    private void checkAddButtonState() {
        // Hide the button if selectedMaterials size matches availableMaterials size
        if (selectedMaterials.size() >= availableMaterials.size()) {
            btnAddMaterial.setVisibility(View.GONE);  // Hide the Add Material button
        }
    }

    private void setupSpinners() {
        // Setup Category Spinner
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, getCategories());
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);

        // Setup Collection Spinner
        ArrayAdapter<String> collectionAdapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, getCollections());
        collectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCollection.setAdapter(collectionAdapter);

        // Setup Brand Spinner
        ArrayAdapter<String> brandAdapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, getBrands());
        brandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBrand.setAdapter(brandAdapter);

        // Setup Condition Spinner
        ArrayAdapter<String> conditionAdapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, getConditions());
        conditionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCondition.setAdapter(conditionAdapter);

        ArrayAdapter<String> sexAdapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, getSex());
        sexAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rgSex.setAdapter(sexAdapter);
    }

    private List<String> getSex() {
        List<String> sex = new ArrayList<>();
        sex.add("Male");
        sex.add("Female");
        sex.add("Unisex");
        return sex;
    }

    private List<String> getCategories() {
        List<String> categories = new ArrayList<>();
        categories.add("Rings");
        categories.add("Necklaces");
        categories.add("Earrings");
        categories.add("Bracelets");
        return categories;
    }

    private List<String> getCollections() {
        List<String> collections = new ArrayList<>();
        collections.add("Wedding Collection");
        collections.add("Vintage Collection");
        collections.add("Modern Collection");
        return collections;
    }

    private List<String> getBrands() {
        List<String> brands = new ArrayList<>();
        brands.add("Brand A");
        brands.add("Brand B");
        brands.add("Brand C");
        return brands;
    }

    private List<String> getConditions() {
        List<String> conditions = new ArrayList<>();
        conditions.add("New");
        conditions.add("Used");
        conditions.add("Refurbished");
        return conditions;
    }

    private void setupRecyclerView() {
        materialAdapter = new MaterialAdapter(getContext(), availableMaterials, selectedMaterials);
        rvMaterials.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMaterials.setAdapter(materialAdapter);
    }

    private void addMaterialRow() {
        // Add a new empty material to the list
        MaterialModel emptyMaterial = new MaterialModel(
                "", // createdAt (empty string for default)
                "", // updatedAt (empty string for default)
                0,  // id (0 to indicate no specific ID)
                "", // name (empty string)
                ""  // unit (empty string)
        );
        selectedMaterials.add(emptyMaterial);
        materialAdapter.notifyItemInserted(selectedMaterials.size() - 1);
    }

    private List<MaterialModel> generateFakeMaterials() {
        List<MaterialModel> materials = new ArrayList<>();
        materials.add(new MaterialModel("2023-01-01", "2023-02-01", 1, "Gold", "grams"));
        materials.add(new MaterialModel("2023-01-01", "2023-02-01", 2, "Silver", "grams"));
        materials.add(new MaterialModel("2023-01-01", "2023-02-01", 3, "Platinum", "grams"));
        materials.add(new MaterialModel("2023-01-01", "2023-02-01", 4, "Diamond", "carats"));
        return materials;
    }

    private boolean checkStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return true; // No need to request permission for Android 13+
        }
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    requireActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION
            );
            return false;
        }
        return true;
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), selectedImageUri);
                imgPreview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Failed to load image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImagePicker();
            } else {
                Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
