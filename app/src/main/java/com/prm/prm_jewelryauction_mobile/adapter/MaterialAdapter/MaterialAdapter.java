package com.prm.prm_jewelryauction_mobile.adapter.MaterialAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prm.prm_jewelryauction_mobile.data.request.jewelry.JewelryMaterialRequest;
import com.prm.prm_jewelryauction_mobile.model.MaterialModel;
import com.prm.prm_jewelryauction_mobile.model.JewelryMaterialModel;
import com.prm.prm_jewelryauction_mobile.R;

import java.util.ArrayList;
import java.util.List;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MaterialViewHolder> {
    private final List<MaterialModel> availableMaterials;
    private final List<MaterialModel> selectedMaterials;
    private final List<JewelryMaterialModel> jewelryMaterials;
    private final Context context;
    private MaterialDeleteListener deleteListener;

    public MaterialAdapter(Context context, List<MaterialModel> availableMaterials,
                           List<MaterialModel> selectedMaterials, MaterialDeleteListener deleteListener) {
        this.context = context;
        this.availableMaterials = availableMaterials;
        this.selectedMaterials = selectedMaterials;
        this.jewelryMaterials = new ArrayList<>();
        this.deleteListener = deleteListener;
    }

    public interface MaterialDeleteListener {
        void onMaterialDeleted();
    }

    @NonNull
    @Override
    public MaterialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_material, parent, false);
        return new MaterialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialViewHolder holder, int position) {
        if (position >= selectedMaterials.size()) {
            return;
        }
        MaterialModel selectedMaterial = selectedMaterials.get(position);

        JewelryMaterialModel jewelryMaterial = getJewelryMaterialByMaterial(selectedMaterial);

        holder.etWeight.setText(String.valueOf(jewelryMaterial.getWeight()));

        holder.etWeight.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                String weightStr = holder.etWeight.getText().toString().trim();
                double weight = weightStr.isEmpty() ? 0 : Double.parseDouble(weightStr);
                jewelryMaterial.setWeight(weight);
            }
        });

        List<MaterialModel> availableForSpinner = new ArrayList<>();
        for (MaterialModel material : availableMaterials) {
            if (!isMaterialSelected(material) || material.getId() == selectedMaterial.getId()) {
                availableForSpinner.add(material);
            }
        }

        ArrayAdapter<MaterialModel> adapter = new ArrayAdapter<>(
                context, android.R.layout.simple_spinner_item, availableForSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spMaterialName.setAdapter(adapter);

        int selectedIndex = getSelectedMaterialIndex(selectedMaterial, availableForSpinner);
        holder.spMaterialName.setSelection(selectedIndex >= 0 ? selectedIndex : 0);

        holder.spMaterialName.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int pos, long id) {
                MaterialModel material = availableForSpinner.get(pos);
                selectedMaterial.setId(material.getId());
                selectedMaterial.setName(material.getName());
                selectedMaterial.setUnit(material.getUnit());
                holder.tvUnit.setText(material.getUnit());
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
            }
        });

        holder.btnDelete.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) {
                removeItem(adapterPosition);
                deleteListener.onMaterialDeleted();
            }
        });
    }


    private boolean isMaterialSelected(MaterialModel material) {
        for (MaterialModel selectedMaterial : selectedMaterials) {
            if (selectedMaterial.getId() == material.getId()) {
                return true;
            }
        }
        return false;
    }

    private int getSelectedMaterialIndex(MaterialModel selectedMaterial, List<MaterialModel> availableMaterials) {
        for (int i = 0; i < availableMaterials.size(); i++) {
            if (availableMaterials.get(i).getId() == selectedMaterial.getId()) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return selectedMaterials.size();
    }

    public void updateAvailableMaterials(List<MaterialModel> newMaterials) {
        availableMaterials.clear();
        availableMaterials.addAll(newMaterials);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        selectedMaterials.remove(position);
        notifyItemRemoved(position);
    }


    public static class MaterialViewHolder extends RecyclerView.ViewHolder {
        Spinner spMaterialName;
        EditText etWeight;
        TextView tvUnit;
        ImageView btnDelete;

        public MaterialViewHolder(@NonNull View itemView) {
            super(itemView);
            spMaterialName = itemView.findViewById(R.id.tvMaterialName);
            etWeight = itemView.findViewById(R.id.etWeight);
            tvUnit = itemView.findViewById(R.id.tvUnit);
            btnDelete = itemView.findViewById(R.id.ivDeleteRow);
        }
    }
    public List<JewelryMaterialRequest> getSelectedJewelryMaterials() {
        List<JewelryMaterialRequest> jewelryMaterialRequests = new ArrayList<>();

        for (JewelryMaterialModel jewelryMaterial : jewelryMaterials) {
            jewelryMaterialRequests.add(new JewelryMaterialRequest(
                    (long) jewelryMaterial.getMaterial().getId(),
                    (float) jewelryMaterial.getWeight()));
        }
        return jewelryMaterialRequests;
    }


    private JewelryMaterialModel getJewelryMaterialByMaterial(MaterialModel material) {
        for (JewelryMaterialModel jewelryMaterial : jewelryMaterials) {
            if (jewelryMaterial.getMaterial().getId() == material.getId()) {
                return jewelryMaterial;
            }
        }
        JewelryMaterialModel newJewelryMaterial = new JewelryMaterialModel(0, material, 0);
        jewelryMaterials.add(newJewelryMaterial);
        return newJewelryMaterial;
    }
}
