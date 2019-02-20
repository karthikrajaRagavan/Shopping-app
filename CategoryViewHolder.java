package com.example.app.shoppingapp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app.shoppingapp.R;
import com.example.app.shoppingapp.interface1.IItemClickListener;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView img_product;
    TextView txt_menu_name;

    public void setItemClickListener(IItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    IItemClickListener itemClickListener;



    public CategoryViewHolder(View itemView) {
        super(itemView);

        img_product=(ImageView)itemView.findViewById(R.id.image_product);
        txt_menu_name=(TextView)itemView.findViewById(R.id.txt_menu_name);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v);
    }
}
