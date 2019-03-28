package com.elAbel.itcrowddemoapp.Data.API.City;

import com.elAbel.itcrowddemoapp.Data.API.ApiConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CityApiAdapter {

    public CityApiService getCityApiService(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(CityApiService.class);
    }
}
