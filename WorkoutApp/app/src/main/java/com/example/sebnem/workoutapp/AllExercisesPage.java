package com.example.sebnem.workoutapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AllExercisesPage extends AppCompatActivity {
    String[] nameArray = Exercise.eNames();

    Integer[] imageArray = Exercise.eImages();

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_exercises);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        showData();
    }

    protected void showData(){
        CustomExerciseListAdapter whatever = new CustomExerciseListAdapter(this, nameArray, imageArray);
        listView = (ListView) findViewById(R.id.listview2ID);
        listView.setAdapter(whatever);

        final List<Exercise> list=Exercise.exercises;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(AllExercisesPage.this, ExerciseDetailsPage.class);
                intent.putExtra("name",list.get(position).getExerciseName());
                startActivity(intent);
            }
        });
    }
    //Integer reps=((Muscle)list.get(position)).getReps();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the app bar.
        getMenuInflater().inflate(R.menu.menu_add, menu);
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

            case R.id.action_add:
                startActivityForResult(new Intent(this, AddExerciseActivity.class), 666);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK && requestCode == 666) {
            String name = data.getStringExtra("exercise");

            String[] names = new String[nameArray.length+1];
            Integer[] images = new Integer[imageArray.length+1];

            System.arraycopy(nameArray, 0, names, 0, nameArray.length);
            System.arraycopy(imageArray, 0, images, 0, imageArray.length);

            names[names.length-1] = name;
            images[images.length-1] = imageArray[0];

            nameArray = names;
            imageArray = images;

            showData();
        }
    }
}
