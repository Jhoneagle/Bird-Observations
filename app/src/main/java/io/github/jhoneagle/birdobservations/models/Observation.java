package io.github.jhoneagle.birdobservations.models;

public class Observation {
    public static final String TABLE_NAME = "observations";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME_SPECIES = "nameSpecies";
    public static final String COLUMN_RARITY = "rarity";
    public static final String COLUMN_NOTES = "notes";
    public static final String COLUMN_IMAGE_ID = "imageId";
    public static final String COLUMN_GEO_LOCATION = "geolocation";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private int id;
    private String nameOfSpecies;
    private String rarity;
    private String notes;
    private int imageId;
    private String geolocation;
    private String timestamp;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME_SPECIES + " TEXT,"
                    + COLUMN_RARITY + " TEXT,"
                    + COLUMN_NOTES + " TEXT,"
                    + COLUMN_IMAGE_ID + " INTEGER,"
                    + COLUMN_GEO_LOCATION + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public Observation() {
    }

    public Observation(int id, String nameOfSpecies, String timestamp, String rarity, String notes, int imageId, String geolocation) {
        this.id = id;
        this.nameOfSpecies = nameOfSpecies;
        this.timestamp = timestamp;
        this.rarity = rarity;
        this.notes = notes;
        this.imageId = imageId;
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(String geolocation) {
        this.geolocation = geolocation;
    }
}
