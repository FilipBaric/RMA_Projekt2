package com.filipbaric.projekt2rma.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.filipbaric.projekt2rma.R;
import com.filipbaric.projekt2rma.viewModel.PCPartViewModel;

public class MainActivity extends AppCompatActivity {

    private PCPartViewModel model;

    public PCPartViewModel getModel() {
        return model;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        model = ViewModelProviders.of(this).get(PCPartViewModel.class);
        read();

    }

    public void read(){
        setFragment( new ReadFragment());
    };

    public void cud(){
        setFragment( new CUDFragment());
    };

    private void setFragment(Fragment fragment){
        //fragment.setEnterTransition(new Slide(Gravity.RIGHT));
        //fragment.setExitTransition(new Slide(Gravity.LEFT));
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
    }
}