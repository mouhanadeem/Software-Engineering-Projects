package com.project.punjabresturant.Adopter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.punjabresturant.Interface.ItemClickListener;
import com.project.punjabresturant.R;

public class MenuAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView menuTxtView;
    public ImageView imgView;
    public ItemClickListener listener;


    public MenuAdapter(@NonNull View itemView) {
        super(itemView);
        menuTxtView=itemView.findViewById(R.id.name_menu);
        imgView=itemView.findViewById(R.id.img_menu);

        itemView.setOnClickListener(this);
    }

    public void setListener(ItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
listener.onClick(v,getAdapterPosition(),false );
    }
}
