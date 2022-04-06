package com.filipbaric.projekt2rma.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.filipbaric.projekt2rma.model.PCPart;
import com.filipbaric.projekt2rma.model.dao.PCPartBaza;
import com.filipbaric.projekt2rma.model.dao.PCPartDAO;

public class PCPartViewModel extends AndroidViewModel{

    PCPartDAO pcPartDAO;
    private PCPart pcPart;

    public PCPartDAO getPcPartDAO() {
        return pcPartDAO;
    }

    public void setPcPartDAO(PCPartDAO pcPartDAO) {
        this.pcPartDAO = pcPartDAO;
    }

    private LiveData<List<PCPart>> pcParts;

    public void setPcPart(PCPart pcPart) {
        this.pcPart = pcPart;
    }

    public PCPart getPcPart() {
        return pcPart;
    }

    public PCPartViewModel(@NonNull Application application) {
        super(application);
        pcPartDAO = PCPartBaza.getInstance(application.getApplicationContext()).pcPartDAO();
    }

    public LiveData<List<PCPart>> dohvatiPCPart(){
        pcParts = pcPartDAO.dohvatiPCPart();
        return pcParts;
    }

    public void dodajNoviPCPart(){ pcPartDAO.dodajNoviPCPart(pcPart); }

    public void promjeniPCPart(){ pcPartDAO.promjeniPCPart(pcPart); }

    public void obrisiPCPart(){
        pcPartDAO.obrisiPCPart(pcPart);
    }
}
