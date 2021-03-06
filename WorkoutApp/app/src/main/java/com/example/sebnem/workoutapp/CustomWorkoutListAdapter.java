package com.example.sebnem.workoutapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomWorkoutListAdapter extends ArrayAdapter {

    //to reference the Activity
    private final Activity context;

    //to store the workout images
    private final Integer[] imageIDarray;

    //to store the list of workout names
    private final String[] nameArray;


    public CustomWorkoutListAdapter(Activity context, String[] nameArrayParam, Integer[] imageIDArrayParam){

        super(context,R.layout.all_workouts_listview_row,nameArrayParam);

        this.context=context;
        this.imageIDarray = imageIDArrayParam;
        this.nameArray = nameArrayParam;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.all_workouts_listview_row, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView nameTextField = (TextView) rowView.findViewById(R.id.nameTextViewID);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView1ID);

        //this code sets the values of the objects to values from the arrays
        nameTextField.setText(nameArray[position]);
        imageView.setImageResource(imageIDarray[position]);

        return rowView;

    };
}
