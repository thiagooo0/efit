package com.myapp.administrator.myapplication.model;

/**
 * Created by Administrator on 2015/8/16.
 */
//
public class Advert {
    private String imageUrl;
    private String linkUrl;
    private int testDraw;
    public int getTestDraw() {
        return testDraw;
    }

    public void setTestDraw(int testDraw) {
        this.testDraw = testDraw;
    }



    public void setImageUrl(String str)
    {
        imageUrl = str;
    }
    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setLinkUrl(String str)
    {
        linkUrl = str;
    }
    public String getLinkUrl()
    {
        return linkUrl;
    }

    public Advert(String imageUrl, String linkUrl)
    {
        setImageUrl(imageUrl);
        setLinkUrl(linkUrl);
    }
    public Advert()
    {

    }

    public Advert(int testDraw, String linkUrl)
    {
        setTestDraw(testDraw);
        setLinkUrl(linkUrl);
    }
}
