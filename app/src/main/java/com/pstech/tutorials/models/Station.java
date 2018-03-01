package com.pstech.tutorials.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.pstech.tutorials.utils.AppUtils;


/**
 * Created by pagrawal on 25-11-2017.
 */

public class Station implements Parcelable {

    private int id;
    private String shortCode;
    private String shortName;
    private String station;
    private boolean underground;
    private boolean interchange;
    private boolean parkingAvailable;
    private boolean mmts;
    private LineType lineType;
    private double lattitude;
    private double longitude;
    private double distanceFareFromBase;
    private boolean smartBikeAvailable;

    public Station(int id, String shortCode, String shortName,
                   String stationName, boolean isUG, boolean isInterchange,
                   boolean isParkingAvl, boolean isMMTS, LineType lineType, double lattitude,
                   double longitude, double distanceFareFromBase, boolean smartBikeAvailable) {
        this.id = id;
        this.shortCode = shortCode;
        this.shortName = shortName;
        this.station = stationName;
        this.underground = isUG;
        this.interchange = isInterchange;
        this.parkingAvailable = isParkingAvl;
        this.mmts = isMMTS;
        this.lineType = lineType;
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.distanceFareFromBase = distanceFareFromBase;
        this.smartBikeAvailable = smartBikeAvailable;
    }

    @Override
    public String toString() {
        return shortName;
    }

    public int getId() {
        return id;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getStation() {
        return station;
    }

    public boolean isUnderground() {
        return underground;
    }

    public boolean isInterchange() {
        return interchange;
    }

    public boolean isParkingAvailable() {
        return parkingAvailable;
    }

    public boolean isMmts() {
        return mmts;
    }

    public double getLattitude() {
        return lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public LineType getLineType() {
        return lineType;
    }

    public double getDistanceFareFromBase() {
        return distanceFareFromBase;
    }

    public boolean isSmartBikeAvailable() {
        return smartBikeAvailable;
    }

    public static class Builder {
        private int id;
        private String shortCode;
        private String shortName;
        private String station;
        private boolean underground;
        private boolean interchange;
        private boolean parkingAvailable;
        private boolean mmts;
        private LineType lineType;
        private double lattitude;
        private double longitude;
        private double distanceFareFromBase;
        private boolean smartBikeAvailable;

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public void setLattitude(double lattitude) {
            this.lattitude = lattitude;
        }

        public void setLineType(String lineType) {
            this.lineType = LineType.getLineType(lineType);
        }

        public void setMmts(boolean mmts) {
            this.mmts = mmts;
        }

        public void setParkingAvailable(boolean parkingAvailable) {
            this.parkingAvailable = parkingAvailable;
        }

        public void setInterchange(boolean interchange) {
            this.interchange = interchange;
        }

        public void setUnderground(boolean underground) {
            this.underground = underground;
        }

        public void setStation(String station) {
            this.station = station;
        }

        public void setShortCode(String shortCode) {
            this.shortCode = shortCode;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }
        public Station build(String stationName) {
            this.station = stationName;
            return new Station(this.id, this.shortCode, this.shortName, this.station,
                    this.underground, this.interchange, this.parkingAvailable, this.mmts,
                    this.lineType, this.lattitude, this.longitude, this.distanceFareFromBase,
                    this.smartBikeAvailable);
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public void setDistanceFareFromBase(double distanceFareFromBase) {
            this.distanceFareFromBase = distanceFareFromBase;
        }

        public void setSmartBikeAvailable(boolean smartBikeAvailable) {
            this.smartBikeAvailable = smartBikeAvailable;
        }
    }

    protected Station(Parcel in) {
    }

    public static final Creator<Station> CREATOR = new Creator<Station>() {
        @Override
        public Station createFromParcel(Parcel in) {
            return new Station(in);
        }

        @Override
        public Station[] newArray(int size) {
            return new Station[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(shortCode);
        parcel.writeString(shortName);
        parcel.writeString(station);
        parcel.writeInt(AppUtils.getIntFromBoolean(underground));
        parcel.writeInt(AppUtils.getIntFromBoolean(interchange));
        parcel.writeInt(AppUtils.getIntFromBoolean(parkingAvailable));
        parcel.writeInt(AppUtils.getIntFromBoolean(mmts));

        // private LineType lineType;
        // private double lattitude;
        // private double longitude;
    }
}
