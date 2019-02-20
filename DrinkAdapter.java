package com.example.app.shoppingapp.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.app.shoppingapp.Model.Drink;
import com.example.app.shoppingapp.R;
import com.example.app.shoppingapp.Utils.Common;
import com.example.app.shoppingapp.interface1.IItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkViewHolder> {
    Context context;
    List<Drink> drinkList;

    public DrinkAdapter(Context context, List<Drink> drinkList) {
        this.context = context;
        this.drinkList = drinkList;
    }

    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.drink_item_layout,null);
        return new DrinkViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkViewHolder holder, final int position) {
        holder.txt_price.setText(new StringBuilder("$").append(drinkList.get(position).Price).toString());
        holder.txt_drink_name.setText(drinkList.get(position).Name);
        holder.btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddToCartDialog(position);

            }
        });
        Picasso.with(context)
                .load(drinkList.get(position).Link)
                .into(holder.img_product);

      holder.setItemClickListener(new IItemClickListener() {
          @Override
          public void onClick(View v) {
              Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show();
          }
      });
    }

    private void showAddToCartDialog(final int position) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View itemView=LayoutInflater.from(context)
                .inflate(R.layout.add_to_cart_layout,null);

        ImageView img_product_dialog=(ImageView)itemView.findViewById(R.id.img_cart_product);
        final ElegantNumberButton txt_count=(ElegantNumberButton)itemView.findViewById(R.id.txt_count);
        TextView txt_product_dialog=(TextView)itemView.findViewById(R.id.txt_cart_product_name);

        EditText edt_comment=(EditText)itemView.findViewById(R.id.edt_comment);

        RadioButton rdi_sizeM=(RadioButton)itemView.findViewById(R.id.rdi_SizeM);
        RadioButton rdi_sizeL=(RadioButton)itemView.findViewById(R.id.rdi_SizeL);

        rdi_sizeM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                Common.size=0;
            }
        });
        rdi_sizeL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Common.size=1;
            }
        });

        RadioButton rdi_red=(RadioButton)itemView.findViewById(R.id.rdi_Red);
        RadioButton rdi_Green=(RadioButton)itemView.findViewById(R.id.rdi_Green);
        RadioButton rdi_Blue=(RadioButton)itemView.findViewById(R.id.rdi_Blue);

        rdi_Blue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                  Common.color="Red";
            }
        });
        rdi_red.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Common.color="Blue";
            }
        });
        rdi_Green.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Common.color="Green";
            }
        });
        Picasso.with(context)
                .load(drinkList.get(position).Link)
                .into(img_product_dialog);
        txt_product_dialog.setText(drinkList.get(position).Name);

        builder.setView(itemView);
        builder.setNegativeButton("ADD TO CART", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(Common.color =="-1"){
                    Toast.makeText(context,"please choose color",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Common.size == -1){
                    Toast.makeText(context,"please choose size",Toast.LENGTH_SHORT).show();
                    return;
                }
                showConfirmDialog(position,txt_count.getNumber(),Common.size,Common.color);
                dialog.dismiss();
            }
        });
         builder.show();

    }

    private void showConfirmDialog(int position, String number, int size, String color) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View itemView=LayoutInflater.from(context)
                .inflate(R.layout.confirm_add_to_cart_layout,null);

        //view
        ImageView img_product_dialog=(ImageView)itemView.findViewById(R.id.img_product);
        TextView txt_product_dialog=(TextView) itemView.findViewById(R.id.txt_cart_product_name);
        TextView txt_product_price=(TextView)itemView.findViewById(R.id.txt_cart_product_price);
        TextView txt_color=(TextView)itemView.findViewById(R.id.txt_color);

        //set data
        Picasso.with(context).load(drinkList.get(position).Link).into(img_product_dialog);
        txt_product_dialog.setText(new StringBuilder(drinkList.get(position).Name).append(" x ")
                .append(number)
                .append(Common.size == 0 ? " Size M":" Size L").toString());
        txt_color.setText(new StringBuilder("Color: ").append(Common.color).toString());
        double price=(Double.parseDouble(drinkList.get(position).Price)* Double.parseDouble(number));
        if(Common.size == 1)
            price+=3.0;

        txt_product_price.setText(new StringBuilder("$").append(price));

        builder.setNegativeButton("CONFIRM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        builder.setView(itemView);
        builder.show();
    }

    @Override
    public int getItemCount() {
       return drinkList.size();

    }
}
