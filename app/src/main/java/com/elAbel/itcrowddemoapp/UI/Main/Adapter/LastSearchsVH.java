package com.elAbel.itcrowddemoapp.UI.Main.Adapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.elAbel.itcrowddemoapp.Model.CityRoom;
import com.elAbel.itcrowddemoapp.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LastSearchsVH extends RecyclerView.ViewHolder {
    @BindView(R.id.txtItemCityName)
    TextView txtItemCityName;
    @BindView(R.id.btnItemDelete)
    Button btnItemDetele;

    public LastSearchsVH(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void setData(CityRoom cityRoom){
        txtItemCityName.setText(cityRoom.getName());
    }
}
