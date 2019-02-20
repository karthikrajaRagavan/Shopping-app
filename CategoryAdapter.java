package com.example.app.shoppingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app.shoppingapp.DrinkActivity;
import com.example.app.shoppingapp.Model.Category;
import com.example.app.shoppingapp.R;
import com.example.app.shoppingapp.Utils.Common;
import com.example.app.shoppingapp.interface1.IItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    Context context;
    List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.menu_item_layout,null);

        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, final int position) {
        Picasso.with(context)
                .load(categories.get(position).Link)
                .into(holder.img_product);


        holder.txt_menu_name.setText(categories.get(position).Name);
        holder.setItemClickListener(new IItemClickListener() {
            @Override
            public void onClick(View v) {
                Common.currentCategory= categories.get(position);

                context.startActivity(new Intent(context, DrinkActivity.class));

            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
