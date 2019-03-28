package com.elAbel.itcrowddemoapp.Data.Room;

import com.elAbel.itcrowddemoapp.Model.CityRoom;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface LastSearchsDAO {

    @Insert
    void insert(CityRoom cityRoom);

    @Delete
    void deleteItem(CityRoom cityRoom);

    @Query("SELECT * from last_searchs")
    LiveData<List<CityRoom>>getAllSearchs();

}
