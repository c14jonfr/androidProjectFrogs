package com.example.brom.androidProjectFrogs;

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
       str+=" can be found in ";
        str+=location;
        str+=" and will grow to a size of ";
        str+=height;
        str+=" cm.";

        return str;

    }
    public String image(){
        return imgurl;
    }
    public String license(){return licensename;}

}
