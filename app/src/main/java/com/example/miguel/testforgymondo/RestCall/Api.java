package com.example.miguel.testforgymondo.RestCall;

import com.example.miguel.testforgymondo.Objects.Category;
import com.example.miguel.testforgymondo.Objects.Equipment;
import com.example.miguel.testforgymondo.Objects.Exercise;
import com.example.miguel.testforgymondo.Objects.ExerciseImage;
import com.example.miguel.testforgymondo.Objects.Muscle;
import com.example.miguel.testforgymondo.Objects.ResponseJSON;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String URL = "http://wger.de/api/v2/";

    @GET("exercise")
    Call<ResponseJSON<Exercise>> getExercises(@Query("status") int status);
    @GET("exercise")
    Call<ResponseJSON<Exercise>> getExercisesWithPage(@Query("status") int status, @Query("page") int page);
    @GET("muscle")
    Call<ResponseJSON<Muscle>> getMuscles();
    @GET("equipment")
    Call<ResponseJSON<Equipment>> getEquipment();
    @GET("exercisecategory")
    Call<ResponseJSON<Category>> getCategory();
    @GET("exerciseimage")
    Call<ResponseJSON<ExerciseImage>> getExerciseImage();
    @GET("exerciseimage")
    Call<ResponseJSON<ExerciseImage>> getExerciseImageWithPage(@Query("page") int page);
}
