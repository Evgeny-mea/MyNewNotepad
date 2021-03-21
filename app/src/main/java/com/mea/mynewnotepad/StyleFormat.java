package com.mea.mynewnotepad;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Calendar;


public class StyleFormat implements Parcelable {
    public static final Creator<StyleFormat> CREATOR = new Creator<StyleFormat>() {
        @Override
        public com.mea.mynewnotepad.StyleFormat createFromParcel(Parcel in) {
            return new com.mea.mynewnotepad.StyleFormat(in);
        }

        @Override
        public com.mea.mynewnotepad.StyleFormat[] newArray(int size) {
            return new com.mea.mynewnotepad.StyleFormat[size];
        }
    };
    private String title;
    private String content;
    private Calendar creationDate;

    public StyleFormat (String title, String content, Calendar creationDate) {
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
    }

    protected StyleFormat (Parcel in) {
        title = in.readString();
        content = in.readString();
        creationDate = (Calendar) in.readSerializable();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
        dest.writeSerializable(creationDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }
}