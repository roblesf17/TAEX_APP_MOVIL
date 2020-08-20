package com.example.app_taex_1;

import android.widget.Filter;

import java.util.ArrayList;

public class CustomFilterD extends Filter {
    ArrayList<Taller> filterList;
    MyAdapterDeporte adapterd;

    public CustomFilterD(ArrayList<Taller> filterList, MyAdapterDeporte adapterd) {
        this.filterList = filterList;
        this.adapterd = adapterd;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if(constraint != null && constraint.length()>0){
            constraint=constraint.toString().toUpperCase();
            ArrayList<Taller> filterModels = new ArrayList<>();

            for(int i=0; i<filterList.size();i++){
                if(filterList.get(i).getNombre_taller().toUpperCase().contains(constraint)){
                    filterModels.add(filterList.get(i));
                }
            }

            results.count = filterModels.size();
            results.values = filterModels;
        }
        else {
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapterd.tallerarray = (ArrayList<Taller>) results.values;
        adapterd.notifyDataSetChanged();
    }
}
