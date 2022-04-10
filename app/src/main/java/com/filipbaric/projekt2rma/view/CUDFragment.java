package com.filipbaric.projekt2rma.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.filipbaric.projekt2rma.R;
import com.filipbaric.projekt2rma.model.PCPart;
import com.filipbaric.projekt2rma.viewModel.PCPartViewModel;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CUDFragment extends Fragment {

    static final int SLIKANJE = 1;

    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.component)
    Spinner component;
    @BindView(R.id.datumKupnje)
    EditText datumKupnje;
    @BindView(R.id.slikaCUD)
    ImageView slikaCUD;

    @BindView(R.id.novaKomponenta)
    Button novaKomponenta;
    @BindView(R.id.uslikajKomponentu)
    Button uslikaj;
    @BindView(R.id.promjenaKomponente)
    Button promjenaKomponente;
    @BindView(R.id.obrisiKomponentu)
    Button obrisiKomponentu;

    PCPartViewModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cud,
                container, false);
        ButterKnife.bind(this, view);
        model = ((MainActivity) getActivity()).getModel();
        Picasso picasso = Picasso.get();
        picasso.setIndicatorsEnabled(true);
        picasso.setLoggingEnabled(true);

        picasso.load(R.drawable.nepoznato).fit().centerCrop().into(slikaCUD);


        if (model.getPcPart().getId() == 0) {
            definirajNovuKomponentu();
            return view;
        }
        name.setText(model.getPcPart().getName());
        component.setSelection(model.getPcPart().getComponent());
        datumKupnje.setText(model.getPcPart().getDatumKupnje().toString());
        definirajPromjenaBrisanjeOsoba();

        return view;
    }

    private void definirajNovuKomponentu() {
        promjenaKomponente.setVisibility(View.GONE);
        obrisiKomponentu.setVisibility(View.GONE);
        uslikaj.setVisibility(View.GONE);
        novaKomponenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                novaKomponenta();
            }
        });
    }

    private void novaKomponenta() {
        model.getPcPart().setName(name.getText().toString());
        model.getPcPart().setComponent(component.getSelectedItemPosition());
        //model.getPcPart().setDatumKupnje(datumKupnje.getText().toString());
        model.dodajNoviPCPart();
        nazad();

    }

    private void definirajPromjenaBrisanjeOsoba() {
        PCPart o = model.getPcPart();
        novaKomponenta.setVisibility(View.GONE);
        name.setText(o.getName());
        component.setSelection(o.getComponent());
        Log.d("Putanja slika", "->" + o.getPutanjaSlika());
        if (o.getPutanjaSlika() != null) {
            Picasso.get().load(o.getPutanjaSlika()).fit().centerCrop().into(slikaCUD);
        }

        promjenaKomponente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promjenaKomponente();
            }
        });

        obrisiKomponentu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obrisiKomponentu();
            }
        });

        uslikaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uslikajKomponentu();
            }
        });
    }

    private void uslikajKomponentu() {
        Intent uslikajIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(uslikajIntent.resolveActivity(getActivity().getPackageManager())==null){
            //poruke korisniku
            return;
        }

        File slika= null;
        try{
            slika = kreirajDatotekuSlike();
        }catch (IOException e){
            //poruke korisniku
            return;
        }

        if(slika==null){
            //poruke korisniku
            return;
        }

        Uri slikaUri = FileProvider.getUriForFile(getActivity(),
                "com.filipbaric.provider",
                slika);
        uslikajIntent.putExtra(MediaStore.EXTRA_OUTPUT,slikaUri);
        startActivityForResult(uslikajIntent,SLIKANJE);

    }

    private File kreirajDatotekuSlike() throws IOException {
        String naziv = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_osoba";
        File dir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File datoteka = File.createTempFile(naziv,".jpg",dir);
        model.getPcPart().setPutanjaSlika("file:" + datoteka.getAbsolutePath());
        return datoteka;
    }

    private void promjenaKomponente(){
        model.getPcPart().setName(name.getText().toString());
        model.getPcPart().setComponent(component.getSelectedItemPosition());
        //model.getPcPart().setDatumKupnje(datumKupnje.getText().toString());
        model.promjeniPCPart();
        nazad();
    }

    private void obrisiKomponentu(){
        model.obrisiPCPart();
        nazad();
    }

    @OnClick(R.id.nazad)
    public void nazad(){
        ((MainActivity)getActivity()).read();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SLIKANJE && resultCode == Activity.RESULT_OK){
            model.promjeniPCPart();
            Picasso.get().load(model.getPcPart().getPutanjaSlika()).fit().centerCrop().into(slikaCUD);
        }
    }
}
