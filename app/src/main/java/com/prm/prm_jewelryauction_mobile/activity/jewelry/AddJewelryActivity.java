package com.prm.prm_jewelryauction_mobile.activity.jewelry;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.prm.prm_jewelryauction_mobile.R;
import com.prm.prm_jewelryauction_mobile.adapter.MaterialAdapter.MaterialAdapter;
import com.prm.prm_jewelryauction_mobile.data.request.jewelry.AddJewelryRequest;
import com.prm.prm_jewelryauction_mobile.data.request.jewelry.JewelryMaterialRequest;
import com.prm.prm_jewelryauction_mobile.model.BrandModel;
import com.prm.prm_jewelryauction_mobile.model.CategoryModel;
import com.prm.prm_jewelryauction_mobile.model.CollectionModel;
import com.prm.prm_jewelryauction_mobile.model.JewelryMaterialModel;
import com.prm.prm_jewelryauction_mobile.model.MaterialModel;
import com.prm.prm_jewelryauction_mobile.service.ApiJewelryService;
import com.prm.prm_jewelryauction_mobile.config.RetrofitClient;
import com.prm.prm_jewelryauction_mobile.model.enum1.JewelryCondition;
import com.prm.prm_jewelryauction_mobile.model.enum1.Sex;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddJewelryActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_PICK = 1;
    private static final int REQUEST_CODE_PERMISSIONS = 100;
    private EditText etJewelryName, etJewelryDescription, etSize;
    private Spinner spinnerCategory, spinnerCollection, spinnerBrand, spinnerCondition, spinnerSex;
    private Button btnUploadImage, btnSubmit;
    private ImageView imgPreview, btnAddMaterial;
    private List<CategoryModel> categoryList;
    private List<BrandModel> brandList;
    private List<CollectionModel> collectionList;
    private Uri imageUri;
    private JewelryCondition selectedCondition;
    private Sex selectedSex;
    private RecyclerView rvMaterials;

    private MaterialAdapter materialAdapter;
    private List<MaterialModel> availableMaterials;
    private List<MaterialModel> selectedMaterials;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_jewelry);

        etJewelryName = findViewById(R.id.etJewelryName);
        etJewelryDescription = findViewById(R.id.etJewelryDescription);
        etSize = findViewById(R.id.etSize);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerCollection = findViewById(R.id.spinnerCollection);
        spinnerBrand = findViewById(R.id.spinnerBrand);
        spinnerCondition = findViewById(R.id.spinnerCondition);
        spinnerSex = findViewById(R.id.rgSex);
        btnUploadImage = findViewById(R.id.btnUploadImage);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnAddMaterial = findViewById(R.id.btnAddMaterial);
        imgPreview = findViewById(R.id.imgPreview);
        rvMaterials = findViewById(R.id.rvMaterials);

        loadCategories();
        loadBrands();
        setupConditionSpinner();
        setupSexSpinner();
        availableMaterials = new ArrayList<>();
        selectedMaterials = new ArrayList<>();
        loadMaterials();
        setupRecyclerView();
        btnUploadImage.setOnClickListener(v -> {
            if (checkStoragePermission()) {
                openImagePicker();
            } else {
                requestStoragePermission();
            }
        });

        btnSubmit.setOnClickListener(v -> {
           addJewelry();
        });
        btnAddMaterial.setOnClickListener(v -> {
            addMaterialItem();
            checkAddButtonState();
        });
    }



    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imgPreview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Không thể tải ảnh", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean checkStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13 trở lên
            return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
                    == PackageManager.PERMISSION_GRANTED;
        } else {
            return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED;
        }
    }

    private void requestStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13 trở lên
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_MEDIA_IMAGES}, REQUEST_CODE_PERMISSIONS);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSIONS);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Đã cấp quyền", Toast.LENGTH_SHORT).show();
                openImagePicker();
            } else {
                Toast.makeText(this, "Từ chối cấp quyền", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void addMaterialItem() {
        if (selectedMaterials == null) {
            selectedMaterials = new ArrayList<>();
        }
        if (selectedMaterials != null && !availableMaterials.isEmpty()) {
            MaterialModel emptyMaterial = new MaterialModel(0, "", "");
            selectedMaterials.add(emptyMaterial);
            materialAdapter.notifyItemInserted(selectedMaterials.size() - 1);
        } else {
            Toast.makeText(this, "No materials available to add", Toast.LENGTH_SHORT).show();
        }
        checkAddButtonState();
    }

    private void checkAddButtonState() {
        if (selectedMaterials.size() >= availableMaterials.size()) {
            btnAddMaterial.setVisibility(View.GONE);
        } else {
            btnAddMaterial.setVisibility(View.VISIBLE);
        }
    }

    private void setupRecyclerView() {
        materialAdapter = new MaterialAdapter(this, availableMaterials, selectedMaterials, new MaterialAdapter.MaterialDeleteListener() {
            @Override
            public void onMaterialDeleted() {
                checkAddButtonState();
            }
        });
        rvMaterials.setLayoutManager(new LinearLayoutManager(this));
        rvMaterials.setAdapter(materialAdapter);
    }


    private void loadCategories() {
        ApiJewelryService apiService = RetrofitClient.getRetrofitInstanceWithToken(this).create(ApiJewelryService.class);
        Call<List<CategoryModel>> call = apiService.getListCategory();

        call.enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                if (response.isSuccessful()) {
                    categoryList = response.body();

                    List<String> categoryNames = new ArrayList<>();
                    for (CategoryModel category : categoryList) {
                        categoryNames.add(category.getName());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(AddJewelryActivity.this, android.R.layout.simple_spinner_item, categoryNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCategory.setAdapter(adapter);

                    spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            int selectedCategoryId = categoryList.get(position).getId();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {}
                    });
                } else {
                    Toast.makeText(AddJewelryActivity.this, "Failed to load categories: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {
                Toast.makeText(AddJewelryActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadBrands() {
        ApiJewelryService apiService = RetrofitClient.getRetrofitInstanceWithToken(this).create(ApiJewelryService.class);
        Call<List<BrandModel>> call = apiService.getListBrand();

        call.enqueue(new Callback<List<BrandModel>>() {
            @Override
            public void onResponse(Call<List<BrandModel>> call, Response<List<BrandModel>> response) {
                if (response.isSuccessful()) {
                    brandList = response.body();

                    List<String> brandNames = new ArrayList<>();
                    for (BrandModel brand : brandList) {
                        brandNames.add(brand.getName());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(AddJewelryActivity.this, android.R.layout.simple_spinner_item, brandNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerBrand.setAdapter(adapter);

                    spinnerBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            long selectedBrandId = brandList.get(position).getId();
                            loadCollections(selectedBrandId);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {}
                    });
                } else {
                    Toast.makeText(AddJewelryActivity.this, "Failed to load brands: " + response.code() + " - " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<BrandModel>> call, Throwable t) {
                Toast.makeText(AddJewelryActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadCollections(long brandId) {
        ApiJewelryService apiService = RetrofitClient.getRetrofitInstanceWithToken(this).create(ApiJewelryService.class);
        Call<List<CollectionModel>> call = apiService.getListCollectionByBrand(brandId);

        call.enqueue(new Callback<List<CollectionModel>>() {
            @Override
            public void onResponse(Call<List<CollectionModel>> call, Response<List<CollectionModel>> response) {
                if (response.isSuccessful()) {
                    collectionList = response.body();

                    List<String> collectionNames = new ArrayList<>();
                    for (CollectionModel collection : collectionList) {
                        collectionNames.add(collection.getName());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(AddJewelryActivity.this, android.R.layout.simple_spinner_item, collectionNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCollection.setAdapter(adapter);
                } else {
                    Toast.makeText(AddJewelryActivity.this, "Failed to load collections: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CollectionModel>> call, Throwable t) {
                Toast.makeText(AddJewelryActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadMaterials() {
        ApiJewelryService apiService = RetrofitClient.getRetrofitInstanceWithToken(this).create(ApiJewelryService.class);
        Call<List<MaterialModel>> call = apiService.getListMaterial();

        call.enqueue(new Callback<List<MaterialModel>>() {
            @Override
            public void onResponse(Call<List<MaterialModel>> call, Response<List<MaterialModel>> response) {
                if (response.isSuccessful()) {
                    availableMaterials = response.body();

                    Log.d("API Response", "Materials: " + availableMaterials.toString());

                    if (availableMaterials != null && !availableMaterials.isEmpty()) {
                        materialAdapter.updateAvailableMaterials(availableMaterials);
                        materialAdapter.notifyDataSetChanged(); // Cập nhật RecyclerView
                    } else {
                        availableMaterials = new ArrayList<>();  // Ensure list is initialized
                        Toast.makeText(AddJewelryActivity.this, "No materials available", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddJewelryActivity.this, "Failed to load materials: " + response.code() + " - " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MaterialModel>> call, Throwable t) {
                Toast.makeText(AddJewelryActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupConditionSpinner() {
        ArrayAdapter<JewelryCondition> conditionAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, JewelryCondition.values());
        conditionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCondition.setAdapter(conditionAdapter);
        spinnerCondition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCondition = JewelryCondition.values()[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedCondition = null;
            }
        });
    }

    private void setupSexSpinner() {
        ArrayAdapter<Sex> sexAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Sex.values());
        sexAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSex.setAdapter(sexAdapter);

        spinnerSex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSex = Sex.values()[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedSex = null;
            }
        });
    }

    private void addJewelry() {
        String name = etJewelryName.getText().toString().trim();
        String description = etJewelryDescription.getText().toString().trim();
        String size = etSize.getText().toString().trim();
        int categoryId = categoryList.get(spinnerCategory.getSelectedItemPosition()).getId();
        String collectionName = collectionList.get(spinnerCollection.getSelectedItemPosition()).getName();
        String brandName = brandList.get(spinnerBrand.getSelectedItemPosition()).getName();

        if (name.isEmpty() || description.isEmpty() || size.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        List<JewelryMaterialRequest> jewelryMaterials = materialAdapter.getSelectedJewelryMaterials();

        Map<String, RequestBody> jewelryRequestMap = new HashMap<>();
        jewelryRequestMap.put("name", RequestBody.create(MediaType.parse("text/plain"), name));
        jewelryRequestMap.put("description", RequestBody.create(MediaType.parse("text/plain"), description));
        jewelryRequestMap.put("category", RequestBody.create(MediaType.parse("text/plain"), String.valueOf(categoryId)));
        jewelryRequestMap.put("size", RequestBody.create(MediaType.parse("text/plain"), size));
        jewelryRequestMap.put("color", RequestBody.create(MediaType.parse("text/plain"), "color")); // assuming color is a static value
        jewelryRequestMap.put("sex", RequestBody.create(MediaType.parse("text/plain"), selectedSex.name()));
        jewelryRequestMap.put("brand", RequestBody.create(MediaType.parse("text/plain"), brandName));
        jewelryRequestMap.put("jewelryCondition", RequestBody.create(MediaType.parse("text/plain"), selectedCondition.name()));
        jewelryRequestMap.put("collection", RequestBody.create(MediaType.parse("text/plain"), collectionName));

        for (int i = 0; i < jewelryMaterials.size(); i++) {
            JewelryMaterialRequest material = jewelryMaterials.get(i);
            jewelryRequestMap.put("materials[" + i + "].idMaterial", RequestBody.create(MediaType.parse("text/plain"), String.valueOf(material.getIdMaterial())));
            jewelryRequestMap.put("materials[" + i + "].weight", RequestBody.create(MediaType.parse("text/plain"), String.valueOf(material.getWeight())));
        }







        MultipartBody.Part imageThumbnailPart = null;
        if (imageUri != null) {
            RequestBody requestBody = createRequestBodyFromUri(imageUri);
            if (requestBody != null) {
                imageThumbnailPart = MultipartBody.Part.createFormData("imageThumbnail", "image.jpg", requestBody);
            } else {
                Toast.makeText(this, "Không thể tạo RequestBody từ ảnh", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        List<MultipartBody.Part> imagesParts = new ArrayList<>();

        ApiJewelryService apiService = RetrofitClient.getRetrofitInstanceWithToken(this)
                .create(ApiJewelryService.class);

        apiService.addJewelry(jewelryRequestMap, imageThumbnailPart, imagesParts)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(AddJewelryActivity.this, "Thêm trang sức thành công", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            try {
                                String errorBody = response.errorBody().string();
                                Toast.makeText(AddJewelryActivity.this, "Thêm trang sức thất bại: " + errorBody, Toast.LENGTH_LONG).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                                Toast.makeText(AddJewelryActivity.this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(AddJewelryActivity.this, "Lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private RequestBody createRequestBodyFromUri(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            byte[] bytes = getBytes(inputStream);
            return RequestBody.create(MediaType.parse("image/*"), bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    private void resetForm() {
        etJewelryName.setText("");
        etJewelryDescription.setText("");
        etSize.setText("");
        spinnerCategory.setSelection(0);
        spinnerCollection.setSelection(0);
        spinnerBrand.setSelection(0);
        spinnerCondition.setSelection(0);
        spinnerSex.setSelection(0);
        imgPreview.setImageURI(null);
        imageUri = null;
    }



}
