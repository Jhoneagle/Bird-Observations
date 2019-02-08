package io.github.jhoneagle.birdobservations.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import io.github.jhoneagle.birdobservations.R;

public class ObservationAdapter extends ArrayAdapter<Observation> {
    private Context mContext;

    public ObservationAdapter(@NonNull Context context, List<Observation> list) {
        super(context, 0 , list);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        }

        Observation observation = getItem(position);

        ImageView image = (ImageView) listItem.findViewById(R.id.image);


        TextView name = (TextView) listItem.findViewById(R.id.item_name);
        name.setText(observation.getNameOfSpecies());

        TextView release = (TextView) listItem.findViewById(R.id.item_notes);
        release.setText(observation.getNotes());

        return listItem;
    }

    public void sort(String orderBY) {
        if (orderBY.isEmpty()) {
            orderBY = "Time";
        }

        if (orderBY.contains("Rarity")) {
            super.sort(Comparators.obByRarity);
        } else if (orderBY.contains("Time")) {
            super.sort(Comparators.obByTime);
        } else {
            super.sort(Comparators.obByName);
        }
    }
}