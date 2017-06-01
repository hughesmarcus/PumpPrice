package com.nnc.hughes.pumpprice.model;

/**
 * Created by marcus on 5/10/17.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class History implements Parcelable {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("via")
    @Expose
    private String via;
    public final static Parcelable.Creator<History> CREATOR = new Creator<History>() {


        @SuppressWarnings({
                "unchecked"
        })
        public History createFromParcel(Parcel in) {
            History instance = new History();
            instance.type = ((String) in.readValue((String.class.getClassLoader())));
            instance.price = ((String) in.readValue((String.class.getClassLoader())));
            instance.date = ((String) in.readValue((String.class.getClassLoader())));
            instance.via = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public History[] newArray(int size) {
            return (new History[size]);
        }

    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public History withType(String type) {
        this.type = type;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public History withPrice(String price) {
        this.price = price;
        return this;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public History withDate(String date) {
        this.date = date;
        return this;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public History withVia(String via) {
        this.via = via;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(type);
        dest.writeValue(price);
        dest.writeValue(date);
        dest.writeValue(via);
    }

    public int describeContents() {
        return 0;
    }

}