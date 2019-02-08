package io.github.jhoneagle.birdobservations.db;

import android.database.sqlite.SQLiteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import io.github.jhoneagle.birdobservations.models.Observation;

public class ObservationDao extends SQLiteOpenHelper implements Dao<Observation, Long> {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "notes_db";


    public ObservationDao(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Observation.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Observation.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public long insert(Observation save) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Observation.COLUMN_NAME_SPECIES, save.getNameOfSpecies());
        values.put(Observation.COLUMN_RARITY, save.getRarity());
        values.put(Observation.COLUMN_NOTES, save.getNotes());
        values.put(Observation.COLUMN_IMAGE_ID, save.getImageId());
        values.put(Observation.COLUMN_GEO_LOCATION, save.getGeolocation());

        long id = db.insert(Observation.TABLE_NAME, null, values);

        db.close();
        return id;
    }

    @Override
    public Observation getOne(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[]{Observation.COLUMN_ID, Observation.COLUMN_NAME_SPECIES, Observation.COLUMN_RARITY, Observation.COLUMN_NOTES, Observation.COLUMN_IMAGE_ID, Observation.COLUMN_GEO_LOCATION, Observation.COLUMN_TIMESTAMP};
        String selection = Observation.COLUMN_ID + "=?";
        String[] selectionValue = new String[]{String.valueOf(id)};


        Cursor cursor = db.query(Observation.TABLE_NAME, columns, selection, selectionValue, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Observation note = new Observation(
                cursor.getInt(cursor.getColumnIndex(Observation.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Observation.COLUMN_NAME_SPECIES)),
                cursor.getString(cursor.getColumnIndex(Observation.COLUMN_TIMESTAMP)),
                cursor.getString(cursor.getColumnIndex(Observation.COLUMN_RARITY)),
                cursor.getString(cursor.getColumnIndex(Observation.COLUMN_NOTES)),
                cursor.getInt(cursor.getColumnIndex(Observation.COLUMN_IMAGE_ID)),
                cursor.getString(cursor.getColumnIndex(Observation.COLUMN_GEO_LOCATION)));

        cursor.close();
        return note;
    }

    @Override
    public List<Observation> getAll() {
        List<Observation> notes = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + Observation.TABLE_NAME + " ORDER BY " +
                Observation.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Observation note = new Observation();
                note.setId(cursor.getInt(cursor.getColumnIndex(Observation.COLUMN_ID)));
                note.setNameOfSpecies(cursor.getString(cursor.getColumnIndex(Observation.COLUMN_NAME_SPECIES)));
                note.setRarity(cursor.getString(cursor.getColumnIndex(Observation.COLUMN_RARITY)));
                note.setNotes(cursor.getString(cursor.getColumnIndex(Observation.COLUMN_NOTES)));
                note.setImageId(cursor.getInt(cursor.getColumnIndex(Observation.COLUMN_IMAGE_ID)));
                note.setGeolocation(cursor.getString(cursor.getColumnIndex(Observation.COLUMN_GEO_LOCATION)));
                note.setTimestamp(cursor.getString(cursor.getColumnIndex(Observation.COLUMN_TIMESTAMP)));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        db.close();
        return notes;
    }

    @Override
    public int update(Observation update) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Observation.COLUMN_NAME_SPECIES, update.getNameOfSpecies());
        values.put(Observation.COLUMN_RARITY, update.getRarity());
        values.put(Observation.COLUMN_NOTES, update.getNotes());
        values.put(Observation.COLUMN_IMAGE_ID, update.getImageId());
        values.put(Observation.COLUMN_GEO_LOCATION, update.getGeolocation());

        String where = Observation.COLUMN_ID + " = ?";
        String[] whereValue = new String[]{String.valueOf(update.getId())};

        return db.update(Observation.TABLE_NAME, values, where, whereValue);
    }

    @Override
    public void delete(Observation delete) {
        String where = Observation.COLUMN_ID + " = ?";
        String[] whereValue = new String[]{String.valueOf(delete.getId())};

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Observation.TABLE_NAME, where, whereValue);

        db.close();
    }
}