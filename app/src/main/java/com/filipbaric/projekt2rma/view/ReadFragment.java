package com.filipbaric.projekt2rma.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.filipbaric.projekt2rma.R;
import com.filipbaric.projekt2rma.view.adapter.PCPartAdapter;
import com.filipbaric.projekt2rma.view.adapter.PCPartClickListener;
import com.filipbaric.projekt2rma.model.PCPart;
import com.filipbaric.projekt2rma.viewModel.PCPartViewModel;

public class ReadFragment extends Fragment{

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.lista)
    ListView listView;

    private PCPartViewModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read,
                container, false);
        ButterKnife.bind(this,view);

        model = ((MainActivity)getActivity()).getModel();

        defineList();
        defineSwipe();
        refeshData();

        return view;
    }

    private void refeshData(){
        model.dohvatiPCPart().observe(getViewLifecycleOwner(), new Observer<List<PCPart>>() {
            @Override
            public void onChanged(@Nullable List<PCPart> itemList) {
                swipeRefreshLayout.setRefreshing(false);
                ((PCPartAdapter)listView.getAdapter()).setItemList(itemList);
                ((PCPartAdapter) listView.getAdapter()).refreshItem();

            }
        });
    }

    private void defineSwipe() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refeshData();
            }
        });

    }

    private void defineList() {

        listView.setAdapter( new PCPartAdapter(getActivity(), R.layout.red_liste, new PCPartClickListener() {
            @Override
            public void onItemClick(PCPart PCPart) {
                model.setPcPart(PCPart);
                ((MainActivity)getActivity()).cud();
            }
        }));
    }

    @OnClick(R.id.fab)
    public void addNew(){
        model.setPcPart(new PCPart());
        ((MainActivity)getActivity()).cud();
    }
}
