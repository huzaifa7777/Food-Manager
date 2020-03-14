package com.example.health.manager.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Model {
    private List<MainAppFoodManager> data = new ArrayList<>();

    Model(){
        loadData();
    }

    private void loadData(){
        Collections.addAll(data);
    }

    public void add(MainAppFoodManager object){
        data.add(object);
    }

    public void del(int pos){
        data.remove(pos);
    }

    public Object getItem(int pos){
        return data.get(pos);
    }

    public long getItemId(int pos){
        return pos;
    }

    public int size(){
        return data==null ? 0 : data.size();
    }
}
