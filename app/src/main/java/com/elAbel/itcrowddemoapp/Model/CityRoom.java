package com.elAbel.itcrowddemoapp.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "last_searchs")
public class CityRoom {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String name;

    public CityRoom(@NonNull String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }
}
