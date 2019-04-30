package com.example.brom.listviewjsonapp;

public class Frog{
    private String name;
    private String location;
    private int height;
    private String imgurl;

    public Frog(String inName, String inLocation, int inHeight, String inImgUrl){
        name=inName;
        location=inLocation;
        height=inHeight;
        imgurl =inImgUrl;

    }
    public String toString(){
        return  name;
    }

    public String info(){
        String str=name;
        str+=" kommer ursprungligen från ";
        str+=location;
        str+=" och blir som störst ";
        str+=height;
        str+=" cm lång.";

        return str;

    }
    public String image(){
        return imgurl;
    }

}
