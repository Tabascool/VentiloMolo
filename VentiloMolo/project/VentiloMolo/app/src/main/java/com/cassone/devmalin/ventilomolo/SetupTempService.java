package com.cassone.devmalin.ventilomolo;

import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

public class SetupTempService {

    private static TemperatureService temperatureService = null;

    public static TemperatureService setupTempService() {
        if (temperatureService == null){
            setup();
        }
        return temperatureService;
    }

    private static void setup(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TemperatureService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Service setup
        temperatureService = retrofit.create(TemperatureService.class);
    }
}

