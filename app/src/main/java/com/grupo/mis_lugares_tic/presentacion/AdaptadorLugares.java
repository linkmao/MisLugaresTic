package com.grupo.mis_lugares_tic.presentacion;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.grupo.mis_lugares_tic.Aplicacion;
import com.grupo.mis_lugares_tic.R;
import com.grupo.mis_lugares_tic.datos.RepositorioLugares;
import com.grupo.mis_lugares_tic.modelo.GeoPunto;
import com.grupo.mis_lugares_tic.modelo.Lugar;

public class AdaptadorLugares extends RecyclerView.Adapter<AdaptadorLugares.ViewHolder> {

    protected RepositorioLugares lugares; // Lista de lugares a mostrar
    protected View.OnClickListener onClickListener;
    //el constructor de la clase
    public AdaptadorLugares(RepositorioLugares lugares) {
        this.lugares = lugares;
    }

    //Creamos nuestro ViewHolder, con los tipos de elementos a modificar
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nombre, direccion, distancia;
        public ImageView foto;
        public RatingBar valoracion;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            direccion = itemView.findViewById(R.id.direccion);
            foto = itemView.findViewById(R.id.foto);
            valoracion= itemView.findViewById(R.id.valoracion);
            distancia = itemView.findViewById(R.id.distancia);
        }

        // Personalizamos un ViewHolder a partir de un lugar

        public void personaliza(Lugar lugar) {
            nombre.setText(lugar.getNombre());
            direccion.setText(lugar.getDireccion());
            int id = R.drawable.otros;
            switch(lugar.getTipo()) {
                case RESTAURANTE:id = R.drawable.restaurante; break;
                case BAR: id = R.drawable.bar; break;
                case COPAS: id = R.drawable.copas; break;
                case ESPECTACULO:id = R.drawable.espectaculos; break;
                case HOTEL: id = R.drawable.hotel; break;
                case COMPRAS: id = R.drawable.compras; break;
                case EDUCACION: id = R.drawable.educacion; break;
                case DEPORTE: id = R.drawable.deporte; break;
                case NATURALEZA: id = R.drawable.naturaleza; break;
                case GASOLINERA: id = R.drawable.gasolinera; break; }
            foto.setImageResource(id);
            foto.setScaleType(ImageView.ScaleType.FIT_END);
            valoracion.setRating(lugar.getValoracion());
            distancia.setText("---");

            GeoPunto pos=((Aplicacion) itemView.getContext().getApplicationContext()).posicionActual;
            if (pos.equals(GeoPunto.SIN_POSICION) || lugar.getPosicion().equals(GeoPunto.SIN_POSICION)) {
                distancia.setText("... Km");
            } else {
                int d=(int) pos.distancia(lugar.getPosicion());
                if (d < 2000) distancia.setText(d + " m");
                else distancia.setText(d / 1000 + " Km");
            }

        }
    }

    //estos métodos son propios del ViewHolder y deben ser importados
    // Creamos el ViewHolder con la vista de un elemento sin personalizar
    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflamos la vista desde el xml
        View laVIsta_un_elemento = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.elemento_lista, parent, false);
        laVIsta_un_elemento.setOnClickListener(onClickListener);
        return new ViewHolder(laVIsta_un_elemento);
    }

    // Usando como base el ViewHolder y lo personalizamos
    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        Lugar lugar = lugares.elemento(position);
        Log.d("TAG adaptador lugares","clic posicion " + lugares.elemento(position));
        holder.personaliza(lugar);
    }

    // Indicamos el número de elementos de la lista
    @Override public int getItemCount() {
        return lugares.tamaño();
    }

    //set de onclick
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
