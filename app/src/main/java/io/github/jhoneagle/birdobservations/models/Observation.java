package io.github.jhoneagle.birdobservations.models;

/**
 * Class for Observation object what is used in temporal memory and Observation model that is used to store data in SQL database in a persist way.
 *
 */
public class Observation {
    // SQL table name and column names.
    public static final String TABLE_NAME = "observations";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME_SPECIES = "nameSpecies";
    public static final String COLUMN_RARITY = "rarity";
    public static final String COLUMN_NOTES = "notes";
    public static final String COLUMN_IMAGE_PATH = "imagePath";
    public static final String COLUMN_GEO_LOCATION = "geolocation";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME_SPECIES + " TEXT,"
                    + COLUMN_RARITY + " TEXT,"
                    + COLUMN_NOTES + " TEXT,"
                    + COLUMN_IMAGE_PATH + " TEXT,"
                    + COLUMN_GEO_LOCATION + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    // Actual Observation object starts
    private int id;
    private String nameOfSpecies;
    private String rarity;
    private String notes;
    private String imagePath;
    private String geolocation;
    private String timestamp;

    public Observation() {
    }

    public Observation(int id, String nameOfSpecies, String timestamp, String rarity, String notes, String imagePath, String geolocation) {
        this.id = id;
        this.nameOfSpecies = nameOfSpecies;
        this.timestamp = timestamp;
        this.rarity = rarity;
        this.notes = notes;
        this.imagePath = imagePath;
        this.geolocation = geolocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfSpecies() {
        return nameOfSpecies;
    }

    public void setNameOfSpecies(String nameOfSpecies) {
        this.nameOfSpecies = nameOfSpecies;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(String geolocation) {
        this.geolocation = geolocation;
    }
}
