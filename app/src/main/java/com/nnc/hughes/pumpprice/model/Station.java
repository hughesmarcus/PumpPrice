package com.nnc.hughes.pumpprice.model;

/**
 * Created by marcus on 5/10/17.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Station implements Parcelable {

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("reg_price")
    @Expose
    private String regPrice;
    @SerializedName("mid_price")
    @Expose
    private String midPrice;
    @SerializedName("pre_price")
    @Expose
    private String prePrice;
    @SerializedName("diesel_price")
    @Expose
    private String dieselPrice;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("diesel")
    @Expose
    private String diesel;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("station")
    @Expose
    private String station;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("reg_date")
    @Expose
    private String regDate;
    @SerializedName("mid_date")
    @Expose
    private String midDate;
    @SerializedName("pre_date")
    @Expose
    private String preDate;
    @SerializedName("diesel_date")
    @Expose
    private String dieselDate;
    @SerializedName("distance")
    @Expose
    private String distance;
    public final static Parcelable.Creator<Station> CREATOR = new Creator<Station>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Station createFromParcel(Parcel in) {
            Station instance = new Station();
            instance.country = ((String) in.readValue((String.class.getClassLoader())));
            instance.regPrice = ((String) in.readValue((String.class.getClassLoader())));
            instance.midPrice = ((String) in.readValue((String.class.getClassLoader())));
            instance.prePrice = ((String) in.readValue((String.class.getClassLoader())));
            instance.dieselPrice = ((String) in.readValue((String.class.getClassLoader())));
            instance.address = ((String) in.readValue((String.class.getClassLoader())));
            instance.diesel = ((String) in.readValue((String.class.getClassLoader())));
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.lat = ((String) in.readValue((String.class.getClassLoader())));
            instance.lng = ((String) in.readValue((String.class.getClassLoader())));
            instance.station = ((String) in.readValue((String.class.getClassLoader())));
            instance.region = ((String) in.readValue((String.class.getClassLoader())));
            instance.city = ((String) in.readValue((String.class.getClassLoader())));
            instance.regDate = ((String) in.readValue((String.class.getClassLoader())));
            instance.midDate = ((String) in.readValue((String.class.getClassLoader())));
            instance.preDate = ((String) in.readValue((String.class.getClassLoader())));
            instance.dieselDate = ((String) in.readValue((String.class.getClassLoader())));
            instance.distance = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Station[] newArray(int size) {
            return (new Station[size]);
        }

    };

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Station withCountry(String country) {
        this.country = country;
        return this;
    }

    public String getRegPrice() {
        return regPrice;
    }

    public void setRegPrice(String regPrice) {
        this.regPrice = regPrice;
    }

    public Station withRegPrice(String regPrice) {
        this.regPrice = regPrice;
        return this;
    }

    public String getMidPrice() {
        return midPrice;
    }

    public void setMidPrice(String midPrice) {
        this.midPrice = midPrice;
    }

    public Station withMidPrice(String midPrice) {
        this.midPrice = midPrice;
        return this;
    }

    public String getPrePrice() {
        return prePrice;
    }

    public void setPrePrice(String prePrice) {
        this.prePrice = prePrice;
    }

    public Station withPrePrice(String prePrice) {
        this.prePrice = prePrice;
        return this;
    }

    public String getDieselPrice() {
        return dieselPrice;
    }

    public void setDieselPrice(String dieselPrice) {
        this.dieselPrice = dieselPrice;
    }

    public Station withDieselPrice(String dieselPrice) {
        this.dieselPrice = dieselPrice;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Station withAddress(String address) {
        this.address = address;
        return this;
    }

    public String getDiesel() {
        return diesel;
    }

    public void setDiesel(String diesel) {
        this.diesel = diesel;
    }

    public Station withDiesel(String diesel) {
        this.diesel = diesel;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Station withId(String id) {
        this.id = id;
        return this;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public Station withLat(String lat) {
        this.lat = lat;
        return this;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public Station withLng(String lng) {
        this.lng = lng;
        return this;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Station withStation(String station) {
        this.station = station;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Station withRegion(String region) {
        this.region = region;
        return this;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Station withCity(String city) {
        this.city = city;
        return this;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public Station withRegDate(String regDate) {
        this.regDate = regDate;
        return this;
    }

    public String getMidDate() {
        return midDate;
    }

    public void setMidDate(String midDate) {
        this.midDate = midDate;
    }

    public Station withMidDate(String midDate) {
        this.midDate = midDate;
        return this;
    }

    public String getPreDate() {
        return preDate;
    }

    public void setPreDate(String preDate) {
        this.preDate = preDate;
    }

    public Station withPreDate(String preDate) {
        this.preDate = preDate;
        return this;
    }

    public String getDieselDate() {
        return dieselDate;
    }

    public void setDieselDate(String dieselDate) {
        this.dieselDate = dieselDate;
    }

    public Station withDieselDate(String dieselDate) {
        this.dieselDate = dieselDate;
        return this;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Station withDistance(String distance) {
        this.distance = distance;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(country);
        dest.writeValue(regPrice);
        dest.writeValue(midPrice);
        dest.writeValue(prePrice);
        dest.writeValue(dieselPrice);
        dest.writeValue(address);
        dest.writeValue(diesel);
        dest.writeValue(id);
        dest.writeValue(lat);
        dest.writeValue(lng);
        dest.writeValue(station);
        dest.writeValue(region);
        dest.writeValue(city);
        dest.writeValue(regDate);
        dest.writeValue(midDate);
        dest.writeValue(preDate);
        dest.writeValue(dieselDate);
        dest.writeValue(distance);
    }

    public int describeContents() {
        return 0;
    }

}