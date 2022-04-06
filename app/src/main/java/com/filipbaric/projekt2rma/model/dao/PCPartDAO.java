package com.filipbaric.projekt2rma.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.filipbaric.projekt2rma.model.PCPart;

@Dao
public interface PCPartDAO {

    @Query("select * from pcpart order by component")
    LiveData<List<PCPart>> dohvatiPCPart();

    @Insert
    void dodajNoviPCPart(PCPart pcPart);

    @Update
    void promjeniPCPart(PCPart pcPart);

    @Delete
    void obrisiPCPart(PCPart pcPart);
}
