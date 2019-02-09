package io.github.jhoneagle.birdobservations.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.github.jhoneagle.birdobservations.R;

/**
 * Custom ArrayAdapter to handle viewing of Observation objects in the list view properly.
 */
public class ObservationAdapter extends ArrayAdapter<Observation> {
    private Context mContext;

    public ObservationAdapter(@NonNull Context context, List<Observation> list) {
        super(context, 0 , list);
        mContext = context;
    }

    /**
     * Handles actual rendering of list items.
     *
     * @return the finalized view object.
     */
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

        TextView notes = (TextView) listItem.findViewById(R.id.item_notes);
        notes.setText(observation.getNotes());

        TextView date = (TextView) listItem.findViewById(R.id.item_date);
        date.setText(observation.getTimestamp());

        TextView rarity = (TextView) listItem.findViewById(R.id.item_rarity);
        rarity.setText(observation.getRarity());

        TextView location = (TextView) listItem.findViewById(R.id.item_geoLocation);
        location.setText(observation.getGeolocation());

        return listItem;
    }

    /**
     * Handles sorting of the list. Has multiple options of comparators that it takes from Comparator class.
     * Supports currently sorting by 'Time', 'Rarity' and 'Name of the species' in a list containing Observation objects.
     *
     * @see Comparators
     *
     * @param orderBY type of sorting wanted.
     */
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