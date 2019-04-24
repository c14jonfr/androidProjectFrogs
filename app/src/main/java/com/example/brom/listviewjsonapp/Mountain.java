package com.example.brom.listviewjsonapp;

public class Mountain {
    private String name;
    private String location;
    private int height;

    public Mountain(String inName, String inLocation, int inHeight){
        name=inName;
        location=inLocation;
        height=inHeight;

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

}
