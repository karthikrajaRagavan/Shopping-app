package com.example.app.shoppingapp.Utils;

import com.example.app.shoppingapp.Model.Category;
import com.example.app.shoppingapp.Model.User;
import com.example.app.shoppingapp.Retrofit.IDrinkShopAPI;
import com.example.app.shoppingapp.Retrofit.RetrofitClient;

import retrofit2.Retrofit;

public class Common {
    private static final String BASE_URL="http://10.0.2.2/drinkshop/";
    public static User currentUser = null;
    public static Category currentCategory=null;
    //Hold field
    public static String color = "-1";
    public static int size =-1;


    public  static IDrinkShopAPI getAPI()
    {
        return RetrofitClient.getClient(BASE_URL).create(IDrinkShopAPI.class);
    }
}
