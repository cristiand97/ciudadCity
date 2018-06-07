package com.cristian.ciudadcity.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cristian.ciudadcity.Lugar;
import com.cristian.ciudadcity.R;
import com.cristian.ciudadcity.adapter.LugarAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MostrarFragment extends Fragment {

    private RecyclerView recyclerLugar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_mostrar, container, false);
        final ArrayList<Lugar> listaLugar = new ArrayList<Lugar>();
        recyclerLugar = vista.findViewById(R.id.recyclerLugar);
        recyclerLugar.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("lugares");
        final LugarAdapter adapter = new LugarAdapter(listaLugar,getActivity());
        recyclerLugar.setAdapter(adapter);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaLugar.removeAll(listaLugar);
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    Lugar lugar = data.getValue(Lugar.class);
                    listaLugar.add(lugar);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return vista;
    }

}
