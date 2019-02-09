package io.github.jhoneagle.birdobservations.models;

import android.location.Location;

/**
 * Interface for geolocation handling.
 */
public interface LocationResultListener {
    void getLocation(Location location);
}
