package com.example.activitytest;

public class Nogizaka46 {
    private String name;
    private int imageId;
    private  String positionId;

    public Nogizaka46(String name,int imageId,String positionId){
        this.name=name;
        this.imageId=imageId;
        this.positionId=positionId;
    }

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }

    public String getPositionId(){return positionId;}
}
