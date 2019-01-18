package com.example.miguel.testforgymondo.Objects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.miguel.testforgymondo.R;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Adapter extends BaseAdapter {
    private Context context;
    private ArrayList<Exercise> exerciseList;
    private HashMap<Integer,ArrayList<Bitmap>> imageMap;
    private HashMap<Integer,String> categoryMap;
    private HashMap<Integer,String> muscleMap;
    private HashMap<Integer,String> equipmentMap;


    public Adapter(Context context, ArrayList<Exercise> exerciseList, HashMap<Integer, ArrayList<Bitmap>> imageMap, HashMap<Integer, String> categoryMap, HashMap<Integer, String> muscleMap, HashMap<Integer, String> equipmentMap) {
        this.context = context;
        this.exerciseList = exerciseList;
        this.imageMap = imageMap;
        this.categoryMap = categoryMap;
        this.muscleMap = muscleMap;
        this.equipmentMap = equipmentMap;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(ArrayList<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public HashMap<Integer, ArrayList<Bitmap>> getImageMap() {
        return imageMap;
    }

    public void setImageMap(HashMap<Integer, ArrayList<Bitmap>> imageMap) {
        this.imageMap = imageMap;
    }

    public HashMap<Integer, String> getCategoryMap() {
        return categoryMap;
    }

    public void setCategoryMap(HashMap<Integer, String> categoryMap) {
        this.categoryMap = categoryMap;
    }

    public HashMap<Integer, String> getMuscleMap() {
        return muscleMap;
    }

    public void setMuscleMap(HashMap<Integer, String> muscleMap) {
        this.muscleMap = muscleMap;
    }

    public HashMap<Integer, String> getEquipmentMap() {
        return equipmentMap;
    }

    public void setEquipmentMap(HashMap<Integer, String> equipmentMap) {
        this.equipmentMap = equipmentMap;
    }

    @Override
    public int getCount() {
        return exerciseList.size();
    }

    @Override
    public Object getItem(int position) {
        return exerciseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Exercise listItem = (Exercise) getItem(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);

        TextView groupName = convertView.findViewById(R.id.groupName);
        TextView exerciseName = convertView.findViewById(R.id.exerciseName);
        TextView equipment = convertView.findViewById(R.id.equipment);
        TextView muscles = convertView.findViewById(R.id.muscles);
        ImageView image = convertView.findViewById(R.id.imageView);
        if(imageMap.containsKey(listItem.getId())) {
            image.setImageBitmap(getImageMap().get(listItem.getId()).get(0));
        }
        else{
            image.setImageResource(R.drawable.default_image);
        }
        groupName.setText(getCategoryName(listItem.getCategory()));
        exerciseName.setText(listItem.getName());
        equipment.setText(getEquipmentName(listItem.getEquipment(),true));
        muscles.setText(getMuscleName(listItem.getMuscles(),listItem.getMusclesSecondary(),true));
        return convertView;
    }

    public String getEquipmentName(List<Integer> equipment, Boolean shortVersion) {
        String result ="";
        int counter = 0;
        for (Integer i : equipment){
            if (counter > 3 && shortVersion){
                return result + ",...";
            }
            try {
                if (result.equals("")) {
                    result = getEquipmentMap().get(i);
                } else {
                    result = result + ", " + getEquipmentMap().get(i);
                }
            }
            catch(Exception e){ }
            counter ++;
        }
        return result;
    }

    public String getCategoryName(int category) {
        String result = getCategoryMap().get(category);
        return result;
    }

    public String getMuscleName(List<Integer> muscles,List<Integer> musclesSecondary, Boolean shortVersion) {
        String result ="";
        int counter = 0;
        for (Integer i : muscles){
            if (counter > 4 && shortVersion){
                return result + ",...";
            }
            try {
                if (result.equals("")){
                    result = getMuscleMap().get(i);
                }
                else {
                    result = result + ", " + getMuscleMap().get(i);
                }

            }
            catch(Exception e){ }
            counter ++;
        }
        if (musclesSecondary.size()>0){
            for (int i : musclesSecondary){
                if (counter > 4 && shortVersion){
                    return result + ",...";
                }
                   result = result + ", " + getMuscleMap().get(i);
                counter ++;
               }
        }
        return result;
    }

    public void addExercise(Exercise exercise){
        this.exerciseList.add(exercise);
    }
    public void addCategory(Category category){
        this.categoryMap.put(category.getId(),category.getName());
    }
    public void addMuscle(Muscle muscle){
        this.muscleMap.put(muscle.getId(),muscle.getName());
    }
    public void addEquipment(Equipment equipment){
        this.equipmentMap.put(equipment.getId(),equipment.getName());
    }

    public void addImage(final ExerciseImage exerciseImage){

        new AsyncTask<Void, Void, Void>() {
            Bitmap bmp = null;

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    InputStream in = new URL(exerciseImage.getImage()).openStream();
                    Bitmap bmp = BitmapFactory.decodeStream(in);
                    addImageToMap(exerciseImage.getExercise(),bmp);
                } catch (Exception e) {
                    // log error
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                if (bmp != null)
                    addImageToMap(exerciseImage.getExercise(),bmp);
            }

        }.execute();

    }
    private void addImageToMap (int id, Bitmap bmp){
        if(imageMap.containsKey(id)){
            this.imageMap.get(id).add(bmp);
        }
        else{
            this.imageMap.put(id,new ArrayList<Bitmap>());
            this.imageMap.get(id).add(bmp);
        }

    }

}
