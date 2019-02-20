package com.example.app.shoppingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.app.shoppingapp.Adapter.DrinkAdapter;
import com.example.app.shoppingapp.Model.Drink;
import com.example.app.shoppingapp.Retrofit.IDrinkShopAPI;
import com.example.app.shoppingapp.Utils.Common;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DrinkActivity extends AppCompatActivity {
    IDrinkShopAPI mService;
    RecyclerView first_drink;
    TextView txt_banner_name;

    CompositeDisposable compositeDisposable=new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        mService= Common.getAPI();
        first_drink=(RecyclerView)findViewById(R.id.recycler_drinks);
        first_drink.setLayoutManager(new GridLayoutManager(this,2));
        first_drink.setHasFixedSize(true);

        txt_banner_name=(TextView)findViewById(R.id.txt_menu_name);
        txt_banner_name.setText(Common.currentCategory.Name);
        loadListDrink(Common.currentCategory.ID);

    }

    private void loadListDrink(String menuid) {
        compositeDisposable.add(mService.getDrink(menuid)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<Drink>>() {
            @Override
            public void accept(List<Drink> drinks) throws Exception {
                displayDrinkList(drinks);

                }


        }));


    }

    private void displayDrinkList(List<Drink> drinks) {
        DrinkAdapter adapter= new DrinkAdapter(this,drinks);
        first_drink.setAdapter(adapter);
    }
}
