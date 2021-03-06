package com.example.sebnem.workoutapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WorkoutDetailsPage extends AppCompatActivity implements View.OnClickListener {

    ArrayList<ArrayList<Exercise>> wExerciseList = Workout.wExercises();
    List<Workout> workoutList = Workout.workouts;

    ListView listView;

    String workoutName;

    String workoutDescrip;

    TextView myText;
    TextView myText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        workoutName = getIntent().getStringExtra("name");
        myText = (TextView) findViewById(R.id.textID);
        myText.setText(workoutName);

        workoutDescrip = getIntent().getStringExtra("description");
        myText2 = (TextView) findViewById(R.id.textID6);
        myText2.setText(workoutDescrip);

        Integer workoutImage = getIntent().getIntExtra("image",0);
        ImageView imageView = (ImageView) findViewById(R.id.imageView2);
        imageView.setImageResource(workoutImage);

        ImageButton startWorkout = (ImageButton) findViewById(R.id.startWorkoutButton);
        startWorkout.setOnClickListener(this);

        showData();

    }

    public void onClick(View view) {

        Intent intent = new Intent(WorkoutDetailsPage.this, WorkoutStart.class);
        startActivity(intent);

    }

    protected void showData(){

        int i;

        for(i = 0; i < workoutList.size(); i++){
            if(workoutList.get(i).getWorkoutName().equals(workoutName)) {
                break;
            }
        }

        final List<Exercise> list = Exercise.exercises;//wExerciseList.get(i);

        /*
        String[] nameArray = new String[list.size()];

        for (int j=0;j<nameArray.length;j++){
            nameArray[j]=list.get(j).getExerciseName();
        }

        Integer[] imageArray = new Integer[list.size()];

        for (int j=0;j<imageArray.length;j++){
            imageArray[j]=list.get(j).getImage();
        }*/

        String[] nameArray = Exercise.eNames();

        Integer[] imageArray = Exercise.eImages();


        CustomExerciseListAdapter whatever = new CustomExerciseListAdapter(this, nameArray, imageArray);
        listView = (ListView) findViewById(R.id.listviewWD);
        listView.setAdapter(whatever);
        setListViewHeightBasedOnChildren(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(WorkoutDetailsPage.this, ExerciseDetailsPage.class);
                intent.putExtra("name",list.get(position).getExerciseName());
                startActivity(intent);

                }
            });

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the app bar.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        Intent editintent;
        switch (item.getItemId()) {
            case R.id.action_settings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_profile:
                intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_edit:
                editintent = new Intent(this, EditWorkoutActivity.class);

                editintent.putExtra("name", workoutName);
                editintent.putExtra("description", workoutDescrip);

                startActivityForResult(editintent, 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK

                // get String data from Intent
                workoutName = data.getStringExtra("name");
                workoutDescrip = data.getStringExtra("description");

                // set text view with string
                TextView myText = (TextView) findViewById(R.id.textID);
                myText.setText(workoutName);

                myText2 = (TextView) findViewById(R.id.textID6);
                myText2.setText(workoutDescrip);

            }
        }
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }

            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}
