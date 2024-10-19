package com.prm.prm_jewelryauction_mobile.adapter.MaterialAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prm.prm_jewelryauction_mobile.model.MaterialModel;
import com.prm.prm_jewelryauction_mobile.R;

import java.util.List;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MaterialViewHolder> {

    private final List<MaterialModel> availableMaterials;
    private final List<MaterialModel> selectedMaterials;
    private final Context context;

    public MaterialAdapter(Context context, List<MaterialModel> availableMaterials,
                           List<MaterialModel> selectedMaterials) {
        this.context = context;
        this.availableMaterials = availableMaterials;
        this.selectedMaterials = selectedMaterials;
    }

    @NonNull
    @Override
    public MaterialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_material, parent, false);
        return new MaterialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialViewHolder holder, int position) {
        MaterialModel selectedMaterial = selectedMaterials.get(position);

        // Create ArrayAdapter for Spinner with available materials
        ArrayAdapter<MaterialModel> adapter = new ArrayAdapter<>(
                context, android.R.layout.simple_spinner_item, availableMaterials);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spMaterialName.setAdapter(adapter);

        // Set the Spinner to the correct material (if already selected)
        int selectedIndex = getSelectedMaterialIndex(selectedMaterial);
        holder.spMaterialName.setSelection(selectedIndex);

        // Handle Spinner item selection
        holder.spMaterialName.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int pos, long id) {
                MaterialModel material = availableMaterials.get(pos);
                selectedMaterial.setId(material.getId()); // Capture the ID of the selected material
                selectedMaterial.setName(material.getName()); // Capture the name
                selectedMaterial.setUnit(material.getUnit()); // Capture the unit
                holder.tvUnit.setText(material.getUnit()); // Update unit TextView
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
                // No action needed
            }
        });

        // Optional: Handle weight input changes
        holder.etWeight.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                String weightStr = holder.etWeight.getText().toString().trim();
                // Optionally validate or store the entered weight
            }
        });
    }

    @Override
    public int getItemCount() {
        return selectedMaterials.size();
    }

    // Helper to get the index of the selected material in the available materials list
    private int getSelectedMaterialIndex(MaterialModel selectedMaterial) {
        for (int i = 0; i < availableMaterials.size(); i++) {
            if (availableMaterials.get(i).getId() == selectedMaterial.getId()) {
                return i;
            }
        }
        return 0; // Default to the first item if no match is found
    }

    // ViewHolder class
    public static class MaterialViewHolder extends RecyclerView.ViewHolder {
        Spinner spMaterialName;
        EditText etWeight;
        TextView tvUnit;

        public MaterialViewHolder(@NonNull View itemView) {
            super(itemView);
            spMaterialName = itemView.findViewById(R.id.tvMaterialName); // Spinner for material name
            etWeight = itemView.findViewById(R.id.etWeight); // Weight input
            tvUnit = itemView.findViewById(R.id.tvUnit); // Unit display
        }
    }
}
