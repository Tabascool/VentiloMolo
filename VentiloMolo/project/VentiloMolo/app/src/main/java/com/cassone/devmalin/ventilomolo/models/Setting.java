package com.cassone.devmalin.ventilomolo.models;

public class Setting {
    private Id _id;
    private int max_temp;

    public Id get_id() {
        return _id;
    }

    public void set_id(Id _id) {
        this._id = _id;
    }

    public int getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(int max_temp) {
        this.max_temp = max_temp;
    }
}
