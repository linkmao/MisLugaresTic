package com.grupo.mis_lugares_tic.casos_uso;

import android.app.Activity;
import android.content.Intent;

import com.grupo.mis_lugares_tic.presentacion.AcercaDeActivity;
import com.grupo.mis_lugares_tic.presentacion.MapaActivity;
import com.grupo.mis_lugares_tic.presentacion.PreferenciasActivity;

public class CasosUsoActividades {

    protected Activity actividad;

    //constructor de la clase
    public CasosUsoActividades(Activity actividad) {
        this.actividad = actividad;
    }

    public void lanzarAcercaDe() {
        actividad.startActivity(new Intent(actividad, AcercaDeActivity.class));
    }

    public void lanzarPreferencias(int codidoSolicitud) {
      actividad.startActivityForResult(new Intent(actividad,
        PreferenciasActivity.class), codidoSolicitud);
   }

    public void lanzarMapa() {
      actividad.startActivity(new Intent(actividad, MapaActivity.class));
   }


}
