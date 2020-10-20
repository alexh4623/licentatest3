package com.example.licentatest3;

public class SpinnerItem {
    private String mBusName;
    private int mBusImage;


    public SpinnerItem(String busName,int busImage){
        mBusName=busName;
        mBusImage=busImage;
    }

    public String getBusName(){
        return mBusName;
    }

    public int getBusImage(){
        return mBusImage;
    }
}
