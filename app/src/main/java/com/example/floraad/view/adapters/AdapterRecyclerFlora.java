package com.example.floraad.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.floraad.R;
import com.example.floraad.model.entity.Flora;
import com.example.floraad.viewmodel.ViewModel;

import java.util.List;

public class AdapterRecyclerFlora extends RecyclerView.Adapter<AdapterRecyclerFlora.ViewHolder> implements PopupMenu.OnMenuItemClickListener{

    private Flora flora;
    private Context context;
    private List<Flora> floraList;
    private Activity activity;
    private NavController navController;
    private ViewModel viewModel;

    public AdapterRecyclerFlora(Context context, Activity activity, View view) {
        this.context = context;
        this.activity = activity;
        viewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(com.example.floraad.viewmodel.ViewModel.class);
        navController = Navigation.findNavController(view);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flora,parent,false);
        ViewHolder holder = new ViewHolder(vista);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String url ="https://informatica.ieszaidinvergeles.org:10019/ad/felix/public/api/imagen/" + floraList.get(holder.getAdapterPosition()).getId() + "/flora";
        holder.tvNombre.setText(floraList.get(holder.getAdapterPosition()).getNombre()+"");
        holder.tvFamilia.setText(floraList.get(holder.getAdapterPosition()).getFamilia());
        holder.tvHabitat.setText(floraList.get(holder.getAdapterPosition()).getHabitat());
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(holder.imgFlora);

        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuPopup(v);
                flora = floraList.get(holder.getAdapterPosition());
            }
        });

    }

    private void menuPopup(View anchor) {
        PopupMenu popup = new PopupMenu(activity, anchor);
        popup.setOnMenuItemClickListener(this);
        popup.getMenuInflater().inflate(R.menu.menu_popup, popup.getMenu());
        popup.show();
    }

    public void setFloraList(List<Flora> floraList) {
        this.floraList = floraList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(floraList == null) {
            return 0;
        }
        return floraList.size();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){

            case R.id.borrarFlora:
                viewModel.deleteFlora(flora.getId());
                viewModel.deleteImagen(flora.getId());
                navController.navigate(R.id.ListarFloraFragment);

                break;
            case R.id.editarFlora:
                Bundle bundle = new Bundle();
                bundle.putParcelable("flora", flora);
                navController.navigate(R.id.updateFloraFragment, bundle);
                break;

        }
        return true;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNombre,tvHabitat,tvFamilia;
        ImageView imgFlora;
        ConstraintLayout parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFamilia = itemView.findViewById(R.id.tvFamilia);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvHabitat = itemView.findViewById(R.id.tvHabitat);
            imgFlora = itemView.findViewById(R.id.imageView);
            parent_layout = itemView.findViewById(R.id.itemLayout);
        }
    }
}
