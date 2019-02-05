package io.github.jhoneagle.birdobservations.models;

public class Observation {
    public static final String TABLE_NAME = "observations";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME_SPECIES = "nameSpecies";
    public static final String COLUMN_RARITY = "rarity";
    public static final String COLUMN_NOTES = "notes";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private int id;
    private String nameOfSpecies;
    private String timestamp;
    private String rarity;
    private String notes;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME_SPECIES + " TEXT,"
                    + COLUMN_RARITY + " TEXT,"
                    + COLUMN_NOTES + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public Observation() {
    }

    public Observation(int id, String nameOfSpecies, String timestamp, String rarity, String notes) {
        this.id = id;
        this.nameOfSpecies = nameOfSpecies;
        this.timestamp = timestamp;
        this.rarity = rarity;
        this.notes = notes;
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
}
