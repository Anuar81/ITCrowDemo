package com.elAbel.itcrowddemoapp.UI.Main.Adapter;

import com.elAbel.itcrowddemoapp.Model.CityRoom;

public interface RvLastSearchsListener {
    void rvLastSearchsListener(CityRoom cityRoom, LastSearchsVH lastSearchsVH);
    void rvDeleteItem(CityRoom cityRoom);
}
