package com.elAbel.itcrowddemoapp.Data.API.City;

import com.elAbel.itcrowddemoapp.Data.API.ApiConstants;
import com.elAbel.itcrowddemoapp.Model.City;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CityApiService {
    @GET(ApiConstants.WEATHER)
    Call<City> getCityTemp(@Query(ApiConstants.WEATHER_QUERY) String cityName, @Query(ApiConstants.WEATHER_QUERY_KEY) String key);
}
