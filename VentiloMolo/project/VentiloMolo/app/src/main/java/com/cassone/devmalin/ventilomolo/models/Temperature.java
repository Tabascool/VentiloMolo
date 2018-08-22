package com.cassone.devmalin.ventilomolo.models;

import com.cassone.devmalin.ventilomolo.models.Id;


public class Temperature {
    private Id _id;
    private Double celcius, humidity;
    private int isMotorActived;

    public Temperature(){
        celcius = humidity = 0.0;
    }

    public Id get_id() {
        return _id;
    }

    public void set_id(Id _id) {
        this._id = _id;
    }

    public Double getCelcius() {
        return celcius;
    }

    public void setCelcius(Double celcius) {
        this.celcius = celcius;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public int isMotorActived() {
        return isMotorActived;
    }

    public void setIsMotorActived(int isMotorActived) {
        this.isMotorActived = isMotorActived;
    }
    /**
     * Constucteur permettant de créer un objet Car à partir d'un Parcel
     * @param in
     */
    /*
    private Temperature(Parcel in){
        this.celcius = in.readDouble();
        this.humidity = in.readDouble();
        this.isMotorActived = in.readInt() == 1;

    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.celcius);
        dest.writeDouble(this.humidity);
        dest.writeInt(this.isMotorActived ?1:0);
    }

    public static final Parcelable.Creator<Temperature> CREATOR = new Parcelable.Creator<Temperature>() {
        public Temperature createFromParcel(Parcel in) {
            return new Temperature(in);
        }

        public Temperature[] newArray(int size) {
            return new Temperature[size];
        }
    };*/
}
