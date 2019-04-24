package com.example.brom.listviewjsonapp;

public class Mountain {
    private String name;
    private String location;
    private int height;
    private String imgurl;

    public Mountain(String inName, String inLocation, int inHeight, String inImgUrl){
        name=inName;
        location=inLocation;
        height=inHeight;
        imgurl = inImgUrl;

    }
    public String toString(){
        return  name;
    }

    public String info(){
        String str=name;
        str+=" is located in ";
        str+=location;
        str+=" and has a height of ";
        str+=height;
        str+=" m.a.s.l.";

        return str;

    }
    public String image(){
        return imgurl;
    }

}
