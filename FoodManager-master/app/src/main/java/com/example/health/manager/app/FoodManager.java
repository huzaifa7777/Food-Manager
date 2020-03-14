package com.example.health.manager.app;

import java.io.Serializable;

public class FoodManager implements Serializable{

        private String name;
        private String brand;
        private int portion;
        private int kCal;
        private int carbohydrates;
        private int fat;
        private int protein;

        //Constructors

        public FoodManager(){
            name = brand = "";
            portion = kCal = carbohydrates = fat = protein = -1;
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

}
