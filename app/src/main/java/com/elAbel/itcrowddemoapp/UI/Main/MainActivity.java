package com.elAbel.itcrowddemoapp.UI.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.elAbel.itcrowddemoapp.Model.City;
import com.elAbel.itcrowddemoapp.Model.CityRoom;
import com.elAbel.itcrowddemoapp.R;
import com.elAbel.itcrowddemoapp.UI.Main.Adapter.LastSearchsAdapter;
import com.elAbel.itcrowddemoapp.UI.Main.Adapter.LastSearchsVH;
import com.elAbel.itcrowddemoapp.UI.Main.Adapter.RvLastSearchsListener;
import com.elAbel.itcrowddemoapp.UI.Map.ITCMapsActivity;
import com.elAbel.itcrowddemoapp.UI.Splash.SplashFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RvLastSearchsListener {

    @BindView(R.id.btnShowMap)Button btnShowMap;
    @BindView(R.id.btnSearch)Button btnSearch;
    @BindView(R.id.txtCityName)TextView txtCityName;
    @BindView(R.id.txtTemp)TextView txtTemp;
    @BindView(R.id.txtPressure)TextView txtPressure;
    @BindView(R.id.txtHumidity)TextView txtHumidity;
    @BindView(R.id.txtTempMax)TextView txtTempMax;
    @BindView(R.id.txtTempMin)TextView txtTempMin;
    @BindView(R.id.recyclerMain)RecyclerView rvMain;
    @BindView(R.id.edtMainSearch)EditText edtMainSearch;
    @BindView(R.id.SnackView)View snackView;

    private MainViewModel vm;
    private City city = new City();
    private LastSearchsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //vm
        vm = ViewModelProviders.of(this).get(MainViewModel.class);

        //splash
        if(!vm.getSplashView()){
            launchSplashFrag();
        }

        //recycler
        rvMain.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter = new LastSearchsAdapter(this, this);
        rvMain.setAdapter(adapter);

        //observable para cambios y actualizaciones.
        vm.getSearchCity().observe(this, new Observer<City>() {
            @Override
            public void onChanged(City city) {
                if(city.getName() != null && city.getName().equals("null")){
                    showSnack(getResources().getString(R.string.main_error_net));
                }else if(city.getMessage() != null && !city.getMessage().isEmpty()){
                    showSnack(city.getMessage());
                }else{
                    setActualCity(city);
                    insertSearch();
                }
            }
        });

        vm.getLastSearchsList().observe(this, new Observer<List<CityRoom>>() {
            @Override
            public void onChanged(List<CityRoom> cityRooms) {
                for (CityRoom city:cityRooms
                     ) {
                    adapter.setCityRoomList(cityRooms);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        //botones
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(edtMainSearch.getText())){
                    vm.searchCityTemp(edtMainSearch.getText().toString());
                }
            }
        });

        btnShowMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ITCMapsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(ITCMapsActivity.MAP_CITY_NAME, city.getName());
                bundle.putDouble(ITCMapsActivity.MAP_LAT, city.getCoord().getLat());
                bundle.putDouble(ITCMapsActivity.MAP_LON, city.getCoord().getLon());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private void showSnack(String msg){
        Snackbar.make(snackView,msg, Snackbar.LENGTH_LONG).show();
    }

    private void setActualCity(City city){
        txtCityName.setText(city.getName());
        txtTemp.setText(city.getMain().getTemp().toString());
        txtHumidity.setText(getResources().getString(R.string.main_forecast_humidity) + city.getMain().getHumidity().toString());
        txtPressure.setText(getResources().getString(R.string.main_forecast_pressure) + city.getMain().getPressure().toString());
        txtTempMax.setText(getResources().getString(R.string.main_forecast_max) + city.getMain().getTempMax().toString());
        txtTempMin.setText(getResources().getString(R.string.main_forecast_min) + city.getMain().getTempMin().toString());
        this.city = city;
        btnShowMap.setEnabled(true);
    }

    private void insertSearch(){
        CityRoom cityRoom = new CityRoom(city.getName());
        vm.insert(cityRoom);
    }

    private void launchSplashFrag(){
        vm.setSplashView(true);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frameContainer,new SplashFragment(),null);
        ft.commit();
    }

    @Override
    public void rvLastSearchsListener(CityRoom cityRoom, LastSearchsVH lastSearchsVH) {
        vm.searchCityTemp(cityRoom.getName());
    }

    @Override
    public void rvDeleteItem(CityRoom cityRoom) {
        vm.deleteItem(cityRoom);
    }
}
