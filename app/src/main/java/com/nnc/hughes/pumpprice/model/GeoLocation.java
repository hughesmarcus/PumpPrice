package com.nnc.hughes.pumpprice.model;

/**
 * Created by marcus on 5/10/17.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeoLocation implements Parcelable {

    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("city_long")
    @Expose
    private String cityLong;
    @SerializedName("region_short")
    @Expose
    private String regionShort;
    @SerializedName("region_long")
    @Expose
    private String regionLong;
    @SerializedName("country_long")
    @Expose
    private String countryLong;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("region_id")
    @Expose
    private String regionId;
    public final static Parcelable.Creator<GeoLocation> CREATOR = new Creator<GeoLocation>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GeoLocation createFromParcel(Parcel in) {
            GeoLocation instance = new GeoLocation();
            instance.cityId = ((String) in.readValue((String.class.getClassLoader())));
            instance.cityLong = ((String) in.readValue((String.class.getClassLoader())));
            instance.regionShort = ((String) in.readValue((String.class.getClassLoader())));
            instance.regionLong = ((String) in.readValue((String.class.getClassLoader())));
            instance.countryLong = ((String) in.readValue((String.class.getClassLoader())));
            instance.countryId = ((String) in.readValue((String.class.getClassLoader())));
            instance.regionId = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public GeoLocation[] newArray(int size) {
            return (new GeoLocation[size]);
        }

    };

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public GeoLocation withCityId(String cityId) {
        this.cityId = cityId;
        return this;
    }

    public String getCityLong() {
        return cityLong;
    }

    public void setCityLong(String cityLong) {
        this.cityLong = cityLong;
    }

    public GeoLocation withCityLong(String cityLong) {
        this.cityLong = cityLong;
        return this;
    }

    public String getRegionShort() {
        return regionShort;
    }

    public void setRegionShort(String regionShort) {
        this.regionShort = regionShort;
    }

    public GeoLocation withRegionShort(String regionShort) {
        this.regionShort = regionShort;
        return this;
    }

    public String getRegionLong() {
        return regionLong;
    }

    public void setRegionLong(String regionLong) {
        this.regionLong = regionLong;
    }

    public GeoLocation withRegionLong(String regionLong) {
        this.regionLong = regionLong;
        return this;
    }

    public String getCountryLong() {
        return countryLong;
    }

    public void setCountryLong(String countryLong) {
        this.countryLong = countryLong;
    }

    public GeoLocation withCountryLong(String countryLong) {
        this.countryLong = countryLong;
        return this;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public GeoLocation withCountryId(String countryId) {
        this.countryId = countryId;
        return this;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public GeoLocation withRegionId(String regionId) {
        this.regionId = regionId;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cityId);
        dest.writeValue(cityLong);
        dest.writeValue(regionShort);
        dest.writeValue(regionLong);
        dest.writeValue(countryLong);
        dest.writeValue(countryId);
        dest.writeValue(regionId);
    }

    public int describeContents() {
        return 0;
    }

}