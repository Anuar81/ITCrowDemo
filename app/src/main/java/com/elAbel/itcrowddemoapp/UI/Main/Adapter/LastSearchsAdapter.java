package com.elAbel.itcrowddemoapp.UI.Main.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elAbel.itcrowddemoapp.Model.CityRoom;
import com.elAbel.itcrowddemoapp.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LastSearchsAdapter extends RecyclerView.Adapter<LastSearchsVH> {
    private RvLastSearchsListener listener;
    private Context context;
    private List<CityRoom>cityRoomList;

    public LastSearchsAdapter(RvLastSearchsListener listener, Context context) {
        this.listener = listener;
        this.context = context;
        cityRoomList = new ArrayList<>();
    }

    public void setCityRoomList(List<CityRoom> cityRoomList) {
        this.cityRoomList = cityRoomList;
    }

    @NonNull
    @Override
    public LastSearchsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_last_search, parent,false);
        return new LastSearchsVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LastSearchsVH holder, int position) {
        final CityRoom cityRoom = cityRoomList.get(position);
        holder.setData(cityRoom);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.rvLastSearchsListener(cityRoom, holder);
            }
        });

        holder.btnItemDetele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.rvDeleteItem(cityRoom);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cityRoomList.size();
    }

}
