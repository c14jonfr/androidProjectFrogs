package com.example.brom.listviewjsonapp;

public class Frog{
    private String name;
    private String location;
    private int height;
    private String imgurl;
    private String licensename;

    public Frog(String inName, String inLocation, int inHeight, String inImgUrl, String license){
        name=inName;
        location=inLocation;
        height=inHeight;
        imgurl =inImgUrl;
        licensename = license;

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
    public String license(){return licensename;}

}
