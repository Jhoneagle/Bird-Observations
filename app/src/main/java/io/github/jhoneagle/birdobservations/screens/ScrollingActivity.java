package io.github.jhoneagle.birdobservations.screens;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.jhoneagle.birdobservations.R;
import io.github.jhoneagle.birdobservations.db.Dao;
import io.github.jhoneagle.birdobservations.db.ObservationDao;
import io.github.jhoneagle.birdobservations.models.LocationHandler;
import io.github.jhoneagle.birdobservations.models.LocationResultListener;
import io.github.jhoneagle.birdobservations.models.Observation;
import io.github.jhoneagle.birdobservations.models.ObservationAdapter;

public class ScrollingActivity extends AppCompatActivity implements LocationResultListener {
    /**
     * permissions request code
     */
    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1;

    /**
     * Permissions that need to be explicitly requested from end user.
     */
    private static final String[] REQUIRED_SDK_PERMISSIONS = new String[] {
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE};

    /**
     * Activity result transfer keys.
     */
    public static final String RARITY = "io.github.jhoneagle.birdobservations.RARITYMESSAGE";
    public static final String NAME = "io.github.jhoneagle.birdobservations.NAMEMESSAGE";
    public static final String NOTES = "io.github.jhoneagle.birdobservations.NOTESMESSAGE";
    public static final String IMAGE = "io.github.jhoneagle.birdobservations.IMAGEMESSAGE";
    public static final int TEXT_REQUEST = 1;

    private Dao<Observation, Long> observationDb;
    private ObservationAdapter observationAdapter;
    private Spinner orderBy;
    private LocationHandler locationHandler;
    private Location location;

    protected String orderValue = "Time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        checkPermissions();

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

        locationHandler = new LocationHandler(this, this, 1000, 1000);
        locationHandler.getUserLocation();
        locationHandler.getUserLocation();
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
                String imagePath = data.getStringExtra(IMAGE) != null ? data.getStringExtra(IMAGE) : "";

                if  (!name.isEmpty() && !notes.isEmpty() && !rarity.isEmpty()) {
                    Observation newOne = new Observation();
                    newOne.setNameOfSpecies(name);
                    newOne.setNotes(notes);
                    newOne.setRarity(rarity);
                    newOne.setImagePath(imagePath);

                    locationHandler.getUserLocation();
                    String locationAsString = (this.location == null) ? "" : (location.getLatitude() + "°N, " + location.getLongitude() + "°E");
                    newOne.setGeolocation(locationAsString);

                    long insertedId = observationDb.insert(newOne);
                    observationAdapter.add(observationDb.getOne(insertedId));
                    observationAdapter.sort((String) orderBy.getSelectedItem());
                    observationAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public void getLocation(Location location) {
        this.location = location;
    }

    /**
     * Checks the dynamically-controlled permissions and requests missing permissions from end user.
     */
    protected void checkPermissions() {
        final List<String> missingPermissions = new ArrayList<String>();

        // check all required dynamic permissions
        for (final String permission : REQUIRED_SDK_PERMISSIONS) {
            final int result = ContextCompat.checkSelfPermission(this, permission);
            if (result != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(permission);
            }
        }

        if (!missingPermissions.isEmpty()) {
            // request all missing permissions
            final String[] permissions = missingPermissions.toArray(new String[missingPermissions.size()]);
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_ASK_PERMISSIONS);
        } else {
            final int[] grantResults = new int[REQUIRED_SDK_PERMISSIONS.length];
            Arrays.fill(grantResults, PackageManager.PERMISSION_GRANTED);
            onRequestPermissionsResult(REQUEST_CODE_ASK_PERMISSIONS, REQUIRED_SDK_PERMISSIONS,
                    grantResults);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                for (int index = permissions.length - 1; index >= 0; --index) {
                    if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {
                        // exit the app if one permission is not granted
                        Toast.makeText(this, "Required permission '" + permissions[index]
                                + "' not granted, exiting", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }
                }
                // all permissions were granted
                break;
        }
    }
}
