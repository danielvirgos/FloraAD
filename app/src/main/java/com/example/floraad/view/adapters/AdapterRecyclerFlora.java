package com.example.floraad.view.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.floraad.R;
import com.example.floraad.model.entity.Flora;

import java.util.List;

public class AdapterRecyclerFlora extends RecyclerView.Adapter<AdapterRecyclerFlora.ViewHolder> implements PopupMenu.OnMenuItemClickListener{
    private List<Flora> floras;
    private Activity activity;
    private View view;
    private ViewModel viewModel;
    private NavController navController;
    private Flora flora;

    public AdapterRecyclerFlora(List<Flora> floras, Activity activity, View view) {
        this.floras = floras;
        this.activity = activity;
        this.view = view;
        viewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ViewModel.class);
        navController = Navigation.findNavController(view);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movil,parent,false);
        ViewHolder holder = new ViewHolder(vista);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvNumeroReparaciones.setText(floras.get(holder.getAdapterPosition()).getNumeroReparaciones()+"");
        holder.tvMarca.setText(floras.get(holder.getAdapterPosition()).getMarca());
        holder.tvModelo.setText(floras.get(holder.getAdapterPosition()).getModelo());
        Glide.with(activity).load(floras.get(holder.getAdapterPosition()).getUrl()).into(holder.imgMovil);
        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flora = floras.get(holder.getAdapterPosition());
                menuPopup(v);
            }
        });

    }

    private void menuPopup(View anchor) {
        PopupMenu popup = new PopupMenu(activity, anchor);
        popup.setOnMenuItemClickListener(this);
        popup.getMenuInflater().inflate(R.menu.menu_popup, popup.getMenu());
        popup.show();
    }

    @Override
    public int getItemCount() {
        return floras.size();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){

            case R.id.borraMovil:
                viewModel.deleteMovil(flora.getId());
                navController.navigate(R.id.listaReparacionesFragment);
                navController.navigate(R.id.listaMovilesFragment);


                break;
            case R.id.editarMovil:
                viewModel.setMovilEditar(flora);
                navController.navigate(R.id.editMovilFragment);

                break;

            case R.id.verMovil:
                viewModel.setMovilVer(flora);
                navController.navigate(R.id.vistaMovilFragment);

                break;

        }
        return true;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNumeroReparaciones,tvMarca,tvModelo;
        ImageView imgMovil;
        ConstraintLayout parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumeroReparaciones = itemView.findViewById(R.id.tvNumeroReparaciones);
            tvMarca = itemView.findViewById(R.id.tvMarca);
            tvModelo = itemView.findViewById(R.id.tvModelo);
            imgMovil = itemView.findViewById(R.id.imgMovil);
            parent_layout = itemView.findViewById(R.id.clItemMovil);
        }
    }
}
