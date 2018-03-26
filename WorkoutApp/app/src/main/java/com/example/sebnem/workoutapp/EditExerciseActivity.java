package com.example.sebnem.workoutapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditExerciseActivity extends AppCompatActivity {

    Exercise ex=Exercise.exercises.get(0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final int position = getIntent().getIntExtra("pos",0);
        ex=Exercise.exercises.get(position);

        final EditText nameEdit=(EditText)findViewById(R.id.exNameEditText);
        nameEdit.setText(ex.getExerciseName());

        //description
        final EditText descEdit=(EditText)findViewById(R.id.exDescEditText);
        descEdit.setText(ex.getDescription());

        //equipment
        final EditText equpipmentEdit=(EditText)findViewById(R.id.exEquipmentEditText);
        equpipmentEdit.setText(ex.getEquipment());
        //reps

        Button confirmButton=(Button) findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ex.setExerciseName(nameEdit.getText().toString());
                ex.setDescription(descEdit.getText().toString());
                ex.setEquipment(equpipmentEdit.getText().toString());
                Toast.makeText(getApplicationContext(), ex.getExerciseName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(EditExerciseActivity.this, ExerciseDetailsPage.class);
                intent.putExtra("pos",position);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the app bar.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_profile:
                intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
