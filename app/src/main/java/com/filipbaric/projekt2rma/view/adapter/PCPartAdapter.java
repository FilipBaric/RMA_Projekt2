package com.filipbaric.projekt2rma.view.adapter;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

import com.filipbaric.projekt2rma.R;
import com.filipbaric.projekt2rma.model.PCPart;

public class PCPartAdapter extends ArrayAdapter<PCPart> implements View.OnTouchListener{

    private List<PCPart> itemList;
    private PCPartClickListener PCPartClickListener;
    private int resource;
    private Context context;

    public PCPartAdapter(@NonNull Context context, int resource, com.filipbaric.projekt2rma.view.adapter.PCPartClickListener pcPartClickListener) {
        super(context, resource);

        this.resource = resource;
        this.context = context;
        this.PCPartClickListener = PCPartClickListener;
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        view.getParent().requestDisallowInterceptTouchEvent(true);
        return false;
    }

    private static class ViewHolder {

        private TextView id;
        private TextView name;
        private TextView component;
        private ImageView putanjaSlika;
        private TextView date;


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
                viewHolder.date = view.findViewById(R.id.date);

                viewHolder.component.setOnTouchListener(this::onTouch);
                viewHolder.component.setMovementMethod(ScrollingMovementMethod.getInstance());
            } else {
                viewHolder = (ViewHolder) view.getTag();

            }

            PCPart = getItem(position);

            if (PCPart != null) {
                //viewHolder.name.setText(sportsEquipment.getName() + " - " + context.getResources().getStringArray(R.array.type_sport)[sportsEquipment.getType()]);

                viewHolder.id.setText(String.valueOf(PCPart.getId()));
                viewHolder.name.setText(PCPart.getName());
                viewHolder.component.setText(PCPart.getComponent());
                viewHolder.date.setText(PCPart.getDatumKupnje());

                viewHolder.date.setOnTouchListener(this::onTouch);
                viewHolder.date.setMovementMethod(ScrollingMovementMethod.getInstance());

                if (PCPart.getPutanjaSlika() == null) {
                    Picasso.get().load(R.drawable.nepoznato).fit().centerCrop().into(viewHolder.putanjaSlika);
                } else {
                    Picasso.get().load(PCPart.getPutanjaSlika()).fit().centerCrop().into(viewHolder.putanjaSlika);
                }
            }

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { PCPartClickListener.onItemClick(PCPart); }
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