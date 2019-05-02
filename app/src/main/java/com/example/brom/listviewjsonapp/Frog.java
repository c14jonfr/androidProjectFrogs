package com.example.brom.listviewjsonapp;

import android.content.Intent;

public class Frog{
    private String name;
    private String location;
    private int height;
    private String imgurl;
    private String licensename;
    private String profileurl;

    public Frog(String inName, String inLocation, int inHeight, String inImgUrl, String license, String inProfileurl){
        name=inName;
        location=inLocation;
        height=inHeight;
        imgurl =inImgUrl;
        licensename = license;
        profileurl = inProfileurl;

    }
    public String toString(){
        return  name;
    }
    public String profileurl(){return profileurl;}

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
