package io.github.jhoneagle.birdobservations.screens;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

import io.github.jhoneagle.birdobservations.R;
import io.github.jhoneagle.birdobservations.db.Dao;
import io.github.jhoneagle.birdobservations.db.ObservationDao;
import io.github.jhoneagle.birdobservations.models.Observation;

public class ScrollingActivity extends AppCompatActivity {
    private Dao<Observation, Long> observationDb;
    private List<Observation> observationsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        observationDb = new ObservationDao(this);
        observationsList.addAll(observationDb.getAll());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }
}
