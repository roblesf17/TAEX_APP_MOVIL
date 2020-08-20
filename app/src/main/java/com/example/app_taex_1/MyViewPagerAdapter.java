package com.example.app_taex_1;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    //Lista de Fragmentos
    List<Fragment> list = new ArrayList<>();

    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    //Para obtener un elemento de la lista
    @Override
    public Fragment getItem(int position) {

        return list.get(position);
    }

    //Para saber cuantos elementos hay en la lista
    @Override
    public int getCount() {

        return list.size();
    }

    //Para agregar fragmentos en la lista
    public void addFragment(Fragment fragment)
    {
        list.add(fragment);
    }
}
