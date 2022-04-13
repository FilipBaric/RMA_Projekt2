package com.filipbaric.projekt2rma.model.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.filipbaric.projekt2rma.model.PCPart;

@Database(entities = {PCPart.class},version = 1,exportSchema = false)
@TypeConverters({Converter.class})
public abstract class PCPartBaza extends RoomDatabase{

    public abstract PCPartDAO pcPartDAO();

    private static PCPartBaza instance;

    public static PCPartBaza getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    PCPartBaza.class,
                    "pcpart-baza"
            ).allowMainThreadQueries().build();
        }
        return instance;
    }
}
