package com.grupo.mis_lugares_tic.presentacion;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.grupo.mis_lugares_tic.R;


public class PreferenciasFragment extends PreferenceFragment {

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);
    }


}