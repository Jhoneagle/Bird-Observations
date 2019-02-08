package io.github.jhoneagle.birdobservations.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import io.github.jhoneagle.birdobservations.R;

public class FormActivity extends AppCompatActivity {
    private Button confirm;
    private Button cancel;
    private Spinner rarity;
    private EditText name;
    private EditText notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        confirm = (Button) findViewById(R.id.confirm);
        cancel = (Button) findViewById(R.id.cancel);
        rarity = (Spinner) findViewById(R.id.spinnerRarity);
        name = (EditText) findViewById(R.id.name_form);
        notes = (EditText) findViewById(R.id.notes_form);
    }

    public void confirm(View view) {
        Intent intent = new Intent(this, ScrollingActivity.class);
        intent.putExtra(ScrollingActivity.RARITY, (String)rarity.getSelectedItem());
        intent.putExtra(ScrollingActivity.NAME, name.getText().toString());
        intent.putExtra(ScrollingActivity.NOTES, notes.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancel(View view) {
        Intent intent = new Intent(this, ScrollingActivity.class);
        name.setText("");
        notes.setText("");
        finish();
    }
}
