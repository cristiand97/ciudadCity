package com.cristian.ciudadcity.adapter;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cristian.ciudadcity.Lugar;
import com.cristian.ciudadcity.MostrarDatosActivity;
import com.cristian.ciudadcity.R;
import com.cristian.ciudadcity.Usuario;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by USUARIO on 6/06/2018.
 */

public class LugarAdapter extends RecyclerView.Adapter<LugarAdapter.ViewHolderLugar> {

    private ArrayList<Lugar> listaLugar;
    private FragmentActivity activity;


    public LugarAdapter(ArrayList<Lugar> listaLugar, FragmentActivity activity) {
        this.listaLugar = listaLugar;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolderLugar onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lugales,null,false);
        return new ViewHolderLugar(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLugar holder, int position) {
        holder.asingarVariables(listaLugar.get(position));
    }

    @Override
    public int getItemCount() {
        return listaLugar.size();
    }

    public class ViewHolderLugar extends RecyclerView.ViewHolder {
        private TextView txtlugar;
        private ImageView imageFondo;
        private Button btnVerLugar;
        private View vista;

        public ViewHolderLugar(View itemView) {
            super(itemView);
            vista = itemView;
            txtlugar = vista.findViewById(R.id.txtLugar);
            imageFondo = vista.findViewById(R.id.imageFondo);
            btnVerLugar = vista.findViewById(R.id.btnVerLugar);
        }

        public void asingarVariables(final Lugar lugar) {

            txtlugar.setText(lugar.getTitulo());
            Picasso.get()
                    .load(lugar.getUrlDescarga())
                    .resize(50, 50)
                    .centerCrop()
                    .into(imageFondo);
            btnVerLugar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("lactitud",lugar.getLactitud());
                    bundle.putString("longitud",lugar.getLongitud());
                    bundle.putString("nombre",lugar.getTitulo());
                    Intent inte = new Intent(activity, MostrarDatosActivity.class);
                    inte.putExtras(bundle);
                    activity.startActivity(inte);
                    activity.finish();
                }
            });
        }
    }
}
