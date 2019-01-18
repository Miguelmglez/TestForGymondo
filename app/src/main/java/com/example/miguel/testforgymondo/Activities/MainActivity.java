package com.example.miguel.testforgymondo.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.miguel.testforgymondo.Objects.Adapter;
import com.example.miguel.testforgymondo.RestCall.Api;
import com.example.miguel.testforgymondo.Objects.Category;
import com.example.miguel.testforgymondo.Objects.Equipment;
import com.example.miguel.testforgymondo.Objects.Exercise;
import com.example.miguel.testforgymondo.Objects.ExerciseImage;
import com.example.miguel.testforgymondo.Objects.Muscle;
import com.example.miguel.testforgymondo.R;
import com.example.miguel.testforgymondo.Objects.ResponseJSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ListView list;
    private Adapter adapter = new Adapter(this ,new ArrayList<Exercise>(), new HashMap<Integer, ArrayList<Bitmap>>(), new HashMap<Integer, String>(),new HashMap<Integer, String>(),new HashMap<Integer, String>());
    private int page = 1;
    private int pageImage = 1;
    static HashMap<Integer, ArrayList<Bitmap>> imageMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.listView);
        getImages();
        getCategories();
        getMuscles();
        getEquipment();
        getExercises();
        imageMap = adapter.getImageMap();
        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                try {
                    if (list.getLastVisiblePosition() == list.getAdapter().getCount() - 1
                            && list.getChildAt(list.getChildCount() - 1).getBottom() <= list.getHeight()) {
                        getNextPage();
                    }
                }
                catch (Exception e){
                    Log.d("FAIL","SCROLL");
                }
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                Exercise exercise = (Exercise) adapter.getItem(position);
                intent.putExtra("idExercise",String.valueOf(exercise.getId()));
                intent.putExtra("nameExercise",exercise.getName());
                intent.putExtra("descriptionExercise",exercise.getDescription());
                intent.putExtra("musclesExercise",adapter.getMuscleName(exercise.getMuscles(),exercise.getMusclesSecondary(),false));
                intent.putExtra("equipmentExercise",adapter.getEquipmentName(exercise.getEquipment(),false));
                intent.putExtra("categoryExercise",adapter.getCategoryName(exercise.getCategory()));
                startActivity(intent);
            }
        });
    }
/*
* Here all methods calling the Rest API
*
 */
    private void getExercises() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<ResponseJSON<Exercise>> call = api.getExercises(2);
        call.enqueue(new Callback<ResponseJSON<Exercise>>() {
            @Override
            public void onResponse(Call<ResponseJSON<Exercise>> call, Response<ResponseJSON<Exercise>> response) {
                if (response.isSuccessful()) {
                    Log.d("Success","FirstPage");
                    updateExercises(response.body().getResults());
                    page++;
                }
            }

            @Override
            public void onFailure(Call<ResponseJSON<Exercise>> call, Throwable t) {
                Log.d("Fail","FirstPage");
                getExercises();
            }
        });
    }

    private void getNextPage() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<ResponseJSON<Exercise>> call = api.getExercisesWithPage(2,page);

        call.enqueue(new Callback<ResponseJSON<Exercise>>() {
            @Override
            public void onResponse(Call<ResponseJSON<Exercise>> call, Response<ResponseJSON<Exercise>> response) {
                if (response.isSuccessful()) {

                    Log.d("Success","NextPage");
                    updateExercises(response.body().getResults());
                    page++;
                }
            }

            @Override
            public void onFailure(Call<ResponseJSON<Exercise>> call, Throwable t) {
                Log.d("Fail","NextPage");
                getNextPage();
            }
        });

    }

    private void getCategories() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<ResponseJSON<Category>> call = api.getCategory();
        call.enqueue(new Callback<ResponseJSON<Category>>() {
            @Override
            public void onResponse(Call<ResponseJSON<Category>> call, Response<ResponseJSON<Category>> response) {
                if (response.isSuccessful()) {
                    Log.d("Success","Category");
                    updateCategories(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<ResponseJSON<Category>> call, Throwable t) {
                Log.d("Fail","Category");
                getCategories();
            }
        });
    }
    private void getMuscles() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<ResponseJSON<Muscle>> call = api.getMuscles();
        call.enqueue(new Callback<ResponseJSON<Muscle>>() {
            @Override
            public void onResponse(Call<ResponseJSON<Muscle>> call, Response<ResponseJSON<Muscle>> response) {
                if (response.isSuccessful()) {
                    Log.d("Success","Muscle");
                    updateMuscles(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<ResponseJSON<Muscle>> call, Throwable t) {
                Log.d("Fail","Muscle");
                getMuscles();
            }
        });
    }
    private void getEquipment() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<ResponseJSON<Equipment>> call = api.getEquipment();
        call.enqueue(new Callback<ResponseJSON<Equipment>>() {
            @Override
            public void onResponse(Call<ResponseJSON<Equipment>> call, Response<ResponseJSON<Equipment>> response) {
                if (response.isSuccessful()) {
                    Log.d("Success","Equipment");
                    updateEquipment(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<ResponseJSON<Equipment>> call, Throwable t) {
                Log.d("Fail","Equipment");
                getEquipment();
            }
        });
    }
    private void getImages() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<ResponseJSON<ExerciseImage>> call = api.getExerciseImage();
        call.enqueue(new Callback<ResponseJSON<ExerciseImage>>() {
            @Override
            public void onResponse(Call<ResponseJSON<ExerciseImage>> call, Response<ResponseJSON<ExerciseImage>> response) {
                if (response.isSuccessful()) {
                    Log.d("Success","ExerciseImage");
                    updateImages(response.body().getResults());
                    if (!response.body().getNext().equals(null)){
                        getNextPageImages();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseJSON<ExerciseImage>> call, Throwable t) {
                Log.d("Fail","ExerciseImage");
                getImages();
            }
        });

    }
    private void getNextPageImages() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<ResponseJSON<ExerciseImage>> call = api.getExerciseImageWithPage(pageImage);
        call.enqueue(new Callback<ResponseJSON<ExerciseImage>>() {
            @Override
            public void onResponse(Call<ResponseJSON<ExerciseImage>> call, Response<ResponseJSON<ExerciseImage>> response) {

                if (response.isSuccessful()) {
                    Log.d("Success","ExerciseImageWithPage"+ pageImage);
                    updateImages(response.body().getResults());
                    String errorBody = response.message();
                    if (response.body().getNext() != null){
                        pageImage++;
                        getNextPageImages();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseJSON<ExerciseImage>> call, Throwable t) {
                Log.d("Fail","ExerciseImageWithPage" + pageImage);
                getNextPageImages();
            }
        });

    }

    /*
    * Here all methods to store the info from Rest API into Adapter.
     */
    private void updateImages(List<ExerciseImage> results) {
        for (ExerciseImage e : results){
            this.adapter.addImage(e);
        }
    }

    private void updateCategories(List<Category> results) {
        for (Category e : results){
            this.adapter.addCategory(e);
        }
    }
    private void updateMuscles(List<Muscle> results) {
        for (Muscle e : results){
            this.adapter.addMuscle(e);
        }
    }
    private void updateEquipment(List<Equipment> results) {
        for (Equipment e : results){
            this.adapter.addEquipment(e);
        }
    }
    private void updateExercises(List<Exercise> response){
        Parcelable state = list.onSaveInstanceState();
        addExercises(response);
        list.setAdapter(adapter);
        list.onRestoreInstanceState(state);
    }
    private void addExercises (List<Exercise> response){
        for (Exercise e : response){
            this.adapter.addExercise(e);
        }
    }
}
