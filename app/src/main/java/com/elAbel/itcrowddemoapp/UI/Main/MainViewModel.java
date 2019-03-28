package com.elAbel.itcrowddemoapp.UI.Main;

import android.app.Application;

import com.elAbel.itcrowddemoapp.Model.City;
import com.elAbel.itcrowddemoapp.Model.CityRoom;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MainViewModel extends AndroidViewModel {
    private Boolean splashView = false;
    private LiveData<City> searchCity;
    private MainRepository repository;
    private LiveData<List<CityRoom>>lastSearchsList;

    public MainViewModel(Application application) {
        super(application);
        repository = new MainRepository(application);
        lastSearchsList = repository.getLastSearchsList();
    }

    public Boolean getSplashView() {
        return splashView;
    }

    public void setSplashView(Boolean splashView) {
        this.splashView = splashView;
    }

    public void searchCityTemp(String cityName){
        searchCity = repository.getCityTemp(cityName);
    }

    public void insert(CityRoom cityRoom){
        repository.insert(cityRoom);
    }

    public void deleteItem(CityRoom cityRoom){
        repository.deleteItem(cityRoom);
    }

    public LiveData<City> getSearchCity() {
        if(searchCity == null){
            searchCityTemp("Buenos Aires");
        }
        return searchCity;
    }

    public LiveData<List<CityRoom>> getLastSearchsList() {
        return lastSearchsList;
    }


}
