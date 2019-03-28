package com.elAbel.itcrowddemoapp.UI.Main;

import android.content.Context;
import android.os.AsyncTask;

import com.elAbel.itcrowddemoapp.Data.API.ApiConstants;
import com.elAbel.itcrowddemoapp.Data.API.City.CityApiAdapter;
import com.elAbel.itcrowddemoapp.Data.API.City.CityApiService;
import com.elAbel.itcrowddemoapp.Data.Room.LastSearchsDAO;
import com.elAbel.itcrowddemoapp.Data.Room.LastSearchsRoomDB;
import com.elAbel.itcrowddemoapp.Model.City;
import com.elAbel.itcrowddemoapp.Model.CityRoom;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
    private CityApiService cityApiService;
    private CityApiAdapter cityApiAdapter;
    private MutableLiveData<City>searchCityTemp;

    private LastSearchsDAO lastSearchsDAO;
    private LiveData<List<CityRoom>>lastSearchsList;
    public static final String INSERT = "insert";
    public static final String DELETE = "delete";


    public MainRepository(Context context) {
        this.cityApiAdapter = new CityApiAdapter();
        this.cityApiService = cityApiAdapter.getCityApiService();
        searchCityTemp = new MutableLiveData<>();
        LastSearchsRoomDB db = LastSearchsRoomDB.getDB(context);
        lastSearchsDAO = db.lastSearchsDAO();
        lastSearchsList = lastSearchsDAO.getAllSearchs();
    }

    public LiveData<City>getCityTemp(String cityName){
        cityApiService.getCityTemp(cityName, ApiConstants.API_KEY).enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                searchCityTemp.setValue(response.body());
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                City nullCity = new City();
                nullCity.setName("null");
                searchCityTemp.setValue(nullCity);
            }
        });
        return searchCityTemp;
    }

    public LiveData<List<CityRoom>> getLastSearchsList() {
        return lastSearchsList;
    }

    public void insert(CityRoom cityRoom){
        new insertAsyncTask(lastSearchsDAO, INSERT).execute(cityRoom);
    }

    public void deleteItem(CityRoom cityRoom){
        new insertAsyncTask(lastSearchsDAO, DELETE).execute(cityRoom);
    }

    private static class insertAsyncTask extends AsyncTask<CityRoom, Void, Void> {
        private LastSearchsDAO asyncDao;
        private String flag;

        insertAsyncTask(LastSearchsDAO dao, String flag) {
            asyncDao = dao;
            this.flag = flag;
        }

        @Override
        protected Void doInBackground(final CityRoom... params) {
            switch (flag){
                case MainRepository.INSERT:
                    asyncDao.insert(params[0]);
                    break;
                case MainRepository.DELETE:
                    asyncDao.deleteItem(params[0]);
                    break;
            }
            return null;
        }

    }
}
