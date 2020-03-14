package com.example.health.manager.app;

public class MainAppFoodManager {
    private String name;
    private String brand;
    private int portion;
    private int kCal;
    private int carbohydrates;
    private int fat;
    private int protein;
    private String meal;

    //Constructors
    public MainAppFoodManager(){
        name = brand = "";
        portion = kCal = carbohydrates = fat = protein = -1;
    }

    public MainAppFoodManager(String name, String brand, int portion, int kCal, int carbohydrates, int fat, int protein, String meal){
        this.name = name;
        this.brand = brand;
        this.portion = portion;
        this.kCal = kCal;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.protein = protein;
        this.meal = meal;
    }
    // get methods
    public String getName(){
        return name;
    }
    public String getBrand(){
        return brand;
    }
    public int getPortion(){
        return portion;
    }
    public int getKCal(){
        return kCal;
    }
    public int getCarbohydrates(){
        return carbohydrates;
    }
    public int getFat(){
        return fat;
    }
    public int getProtein(){
        return protein;
    }
    public String getMeal(){
        return meal;
    }

    //set methods
    public void setName(String name){
        this.name =  name;
    }
    public void setBrand(String brand){
        this.brand =  brand;
    }
    public void setPortion(int portion){
        this.portion =  portion;
    }
    public void setKCal(int kCal){
        this.kCal =  kCal;
    }
    public void setCarbohydrates(int carbohydrates){
        this.carbohydrates =  carbohydrates;
    }
    public void setFat(int fat){
        this.fat =  fat;
    }
    public void setProtein(int protein) {
        this.protein = protein;
    }
    public void setMeal(String meal){
        this.meal = meal;
    }
}
