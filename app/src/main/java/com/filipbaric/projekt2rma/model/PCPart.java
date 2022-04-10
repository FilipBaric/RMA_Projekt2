package com.filipbaric.projekt2rma.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "pcpart")
public class PCPart {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name="komponente")
    @NonNull
    private String name;
    private int component;
    private String putanjaSlika;
    private Date datumKupnje;

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

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getComponent() {
        return component;
    }

    public void setComponent(int component) {
        this.component = component;
    }

    public String getPutanjaSlika() {
        return putanjaSlika;
    }

    public void setPutanjaSlika(String putanjaSlika) {
        this.putanjaSlika = putanjaSlika;
    }

    public Date getDatumKupnje() {
        return datumKupnje;
    }

    public void setDatumKupnje(Date datumKupnje) {
        this.datumKupnje = datumKupnje;
    }
}