package com.example.sadik.myapplication;

public class Item {
    private String mImageUrl;
    private String mCreator;
    private String mContent;
private String mHaberUrl;
private String mpublished;


    public Item(String imageUrl, String Creator, String Content, String HaberUrl, String Published){


        mHaberUrl=HaberUrl;
        mpublished=Published;
        mImageUrl=imageUrl;
        mCreator=Creator;
        mContent=Content;

    }
    public String getMpublished() {
        return mpublished;
    }


    public String getmHaberUrl() {
        return mHaberUrl;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getmCreator() {
        return mCreator;
    }

    public String getmContent() {
        return mContent;
    }
}
