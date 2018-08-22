package com.cassone.devmalin.ventilomolo;

import com.cassone.devmalin.ventilomolo.models.Setting;
import com.cassone.devmalin.ventilomolo.models.Temperature;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TemperatureService {
    public static final String URL = "https://api.mlab.com/api/1/databases/arduiland/collections/";
    public static final String API_KEY = "e16CW-yUW4cxtREIjflDYuVWANP6V70W";

    @Headers({
            "Accept: application/json",
            "Content-type: application/json"
    })

    @GET("Temperature")
    Call<List<Temperature>> getAllTemperature(@Query("apiKey") String apiKey);

    @POST("Setting")
    Call<JSONObject> registerUser(@Query("apiKey") String apiKey, @Body Setting max_temp);
}
