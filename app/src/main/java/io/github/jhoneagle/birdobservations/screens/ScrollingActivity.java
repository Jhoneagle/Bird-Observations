package io.github.jhoneagle.birdobservations.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import io.github.jhoneagle.birdobservations.R;
import io.github.jhoneagle.birdobservations.db.Dao;
import io.github.jhoneagle.birdobservations.db.ObservationDao;
import io.github.jhoneagle.birdobservations.models.Observation;
import io.github.jhoneagle.birdobservations.models.ObservationAdapter;

public class ScrollingActivity extends AppCompatActivity {
    public static final String RARITY = "io.github.jhoneagle.birdobservations.RARITYMESSAGE";
    public static final String NAME = "io.github.jhoneagle.birdobservations.NAMEMESSAGE";
    public static final String NOTES = "io.github.jhoneagle.birdobservations.NOTESMESSAGE";
    public static final int TEXT_REQUEST = 1;

    private Dao<Observation, Long> observationDb;
    private ObservationAdapter observationAdapter;
    private Spinner orderBy;

    protected String orderValue = "Time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listView = (ListView) findViewById(R.id.observations_list);
        orderBy = (Spinner) findViewById(R.id.spinner_order);
        observationDb = new ObservationDao(this);
        observationAdapter = new ObservationAdapter(this, observationDb.getAll());
        listView.setAdapter(observationAdapter);

        orderBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedItem = (String) orderBy.getSelectedItem();
                if (!orderValue.contains(selectedItem)) {
                    observationAdapter.sort(selectedItem);
                    orderValue = selectedItem;
                    observationAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    public void openForm(View view) {
        Intent intent = new Intent(this, FormActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra(NAME) != null ? data.getStringExtra(NAME) : "";
                String notes = data.getStringExtra(NOTES) != null ? data.getStringExtra(NOTES) : "";
                String rarity = data.getStringExtra(RARITY) != null ? data.getStringExtra(RARITY) : "";

                if  (!name.isEmpty() && !notes.isEmpty() && !rarity.isEmpty()) {
                    Observation newOne = new Observation();
                    newOne.setNameOfSpecies(name);
                    newOne.setNotes(notes);
                    newOne.setRarity(rarity);
                    newOne.setImageId(-1);

                    long insertedId = observationDb.insert(newOne);
                    observationAdapter.add(observationDb.getOne(insertedId));
                    observationAdapter.sort((String) orderBy.getSelectedItem());
                    observationAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}
