package com.example.miguel.testforgymondo.Activities;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.miguel.testforgymondo.R;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        String nameExercise = bundle.getString("nameExercise");
        String descriptionExercise = bundle.getString("descriptionExercise");
        String idExercise = bundle.getString("idExercise");
        String musclesExercise = bundle.getString("musclesExercise");
        String categoryExercise = bundle.getString("categoryExercise");
        String equipmentExercise = bundle.getString("equipmentExercise");

        ArrayList<Bitmap> images = MainActivity.imageMap.get(Integer.parseInt(idExercise));
        TextView categoryTextView = findViewById(R.id.categoryExerciseDetail);
        TextView exerciseTextView = findViewById(R.id.nameExerciseDetail);
        TextView equipmentTextView = findViewById(R.id.equipmentExerciseDetail);
        TextView musclesTextView = findViewById(R.id.musclesExerciseDetail);
        TextView descriptionTextView = findViewById(R.id.descriptionExerciseDetail);

        if (images != null) {
            LinearLayout linearLayout1 =  findViewById(R.id.linearLayoutElements);
            for(int i = 0 ; i < images.size(); i++) {
                ImageView image = new ImageView(DetailActivity.this);
                image.setImageBitmap(images.get(i));
                linearLayout1.addView(image);
            }
        }
        categoryTextView.setText(categoryExercise);
        exerciseTextView.setText(nameExercise);
        equipmentTextView.setText(equipmentExercise);
        musclesTextView.setText(musclesExercise);
        descriptionTextView.setText(Html.fromHtml(descriptionExercise));
    }
}
