package com.elAbel.itcrowddemoapp.Data.Room;

import android.content.Context;

import com.elAbel.itcrowddemoapp.Model.CityRoom;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CityRoom.class}, version = 1)
public abstract class LastSearchsRoomDB extends RoomDatabase {
    public abstract LastSearchsDAO lastSearchsDAO();
    private static volatile LastSearchsRoomDB INSTANCE;

    public static LastSearchsRoomDB getDB(final Context context){
        if (INSTANCE == null){
            synchronized (LastSearchsRoomDB.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LastSearchsRoomDB.class, "last_search_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
