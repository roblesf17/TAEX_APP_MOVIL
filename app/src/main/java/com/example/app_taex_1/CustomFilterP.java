package com.example.app_taex_1;

import android.widget.Filter;
import java.util.ArrayList;

public class CustomFilterP extends Filter {
    ArrayList<Taller> filterList;
    MyAdapterPersonal adapter;

    public CustomFilterP(ArrayList<Taller> filterList, MyAdapterPersonal adapterp) {
        this.filterList = filterList;
        this.adapter = adapterp;
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
        adapter.tallerarray = (ArrayList<Taller>) results.values;
        adapter.notifyDataSetChanged();
    }
}
