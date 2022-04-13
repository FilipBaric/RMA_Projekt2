package com.filipbaric.projekt2rma.view.adapter;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.filipbaric.projekt2rma.R;
import com.filipbaric.projekt2rma.model.PCPart;

public class PCPartAdapter extends ArrayAdapter<PCPart>{

    private List<PCPart> itemList;
    private PCPartClickListener pcPartClickListener;
    private int resource;
    private Context context;

    public PCPartAdapter(@NonNull Context context, int resource, com.filipbaric.projekt2rma.view.adapter.PCPartClickListener pcPartClickListener) {
        super(context, resource);

        this.resource = resource;
        this.context = context;
        this.pcPartClickListener = pcPartClickListener;
    }

    private static class ViewHolder {

        private TextView id;
        private TextView name;
        private TextView component;
        private ImageView putanjaSlika;
        private TextView datumKupnje;


    }

    @androidx.annotation.NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @androidx.annotation.NonNull ViewGroup parent) {

        View view = convertView;
        PCPart PCPart;
        ViewHolder viewHolder;

        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (inflater != null) {
                view = inflater.inflate(this.resource, null);

                viewHolder.id = view.findViewById(R.id.id);
                viewHolder.name = view.findViewById(R.id.name);
                viewHolder.putanjaSlika = view.findViewById(R.id.putanjaSlika);
                viewHolder.component = view.findViewById(R.id.component);
                viewHolder.datumKupnje = view.findViewById(R.id.datumKupnje);

                //viewHolder.component.setOnTouchListener(this::onTouch);
                //viewHolder.component.setMovementMethod(ScrollingMovementMethod.getInstance());

            } else {
                viewHolder = (ViewHolder) view.getTag();

            }

            PCPart = getItem(position);

            if (PCPart != null) {

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(PCPart.getDatumKupnje());

                viewHolder.id.setText(String.valueOf(PCPart.getId()));
                viewHolder.name.setText(PCPart.getName());
                viewHolder.component.setText(context.getResources().getStringArray(R.array.komponente)[PCPart.getComponent()]);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd. MM. yyyy.");
                try {
                    viewHolder.datumKupnje.setText(simpleDateFormat.format(PCPart.getDatumKupnje()));
                }catch (Exception e){
                    viewHolder.datumKupnje.setText("");
                }


                //viewHolder.datumKupnje.setOnTouchListener(this::onTouch);
                //viewHolder.datumKupnje.setMovementMethod(ScrollingMovementMethod.getInstance());

                if (PCPart.getPutanjaSlika() == null) {
                    Picasso.get().load(R.drawable.nepoznato).fit().centerCrop().into(viewHolder.putanjaSlika);
                } else {
                    Picasso.get().load(PCPart.getPutanjaSlika()).fit().centerCrop().into(viewHolder.putanjaSlika);
                }
            }

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { pcPartClickListener.onItemClick(PCPart); }
            });
        }
        return view;
    }

    @Override
    public int getCount() {
        return itemList == null ? 0 : itemList.size();
    }

    @Nullable
    @Override
    public PCPart getItem(int position) {
        return itemList.get(position);
    }

    public void setItemList(List<PCPart> itemList) {
        this.itemList = itemList;
    }

    public void refreshItem() {
        notifyDataSetChanged();
    }

}
