package com.filipbaric.projekt2rma.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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
    private String component;
    private String putanjaSlika;
    private String datumKupnje;

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

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getPutanjaSlika() {
        return putanjaSlika;
    }

    public void setPutanjaSlika(String putanjaSlika) {
        this.putanjaSlika = putanjaSlika;
    }

    public String getDatumKupnje() {
        return datumKupnje;
    }

    public void setDatumKupnje(String datumKupnje) {
        this.datumKupnje = datumKupnje;
    }
}