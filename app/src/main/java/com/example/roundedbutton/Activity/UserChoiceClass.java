package com.example.roundedbutton.Activity;

import android.graphics.drawable.Drawable;

public class UserChoiceClass {
    String id;
    String title;
    String imageURL;
    Drawable drawable;
    String fragmentName;
    Boolean selected;

    public UserChoiceClass() {
        this.imageURL = "";
        this.title = "";

    }

    public UserChoiceClass(String id, String title, String imageURL, Drawable drawable, String fragmentName) {
        this.id = id;
        this.title = title;
        this.imageURL = imageURL;
        this.drawable = drawable;
        this.fragmentName = fragmentName;
        this.selected = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getFragmentName() {
        return fragmentName;
    }

    public void setFragmentName(String fragmentName) {
        this.fragmentName = fragmentName;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
