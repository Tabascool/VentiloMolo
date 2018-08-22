package com.cassone.devmalin.ventilomolo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cassone.devmalin.ventilomolo.models.Id;
import com.cassone.devmalin.ventilomolo.models.Setting;
import com.cassone.devmalin.ventilomolo.models.Temperature;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity {

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);

        findViewById(R.id.edit_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int max_temp = Integer.valueOf(((EditText) findViewById(R.id.edit_max_temp)).getText().toString());
                if(max_temp > 60 || max_temp < -10){
                    Toast.makeText(getApplicationContext(), "please enter value between -10 and 60", Toast.LENGTH_SHORT).show();
                } else{
                    TemperatureService temperatureService = SetupTempService.setupTempService();
                    Id id = new Id();
                    id.set$oid("5723140afca0fd7cf0f2736c");
                    Setting setting = new Setting();
                    setting.set_id(id);
                    setting.setMax_temp(max_temp);
                    Call<JSONObject> call = temperatureService.registerUser(TemperatureService.API_KEY, setting);
                    call.enqueue(new Callback<JSONObject>() {
                        @Override
                        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                            if (!response.isSuccess()) {
                                Log.d("Register", "error");
                            } else {
                                Log.d("Register", "success");
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            }
                        }

                        @Override
                        public void onFailure(Call<JSONObject> call, Throwable t) {
                            Log.d("Register", "Failure" + t.toString());
                        }
                    });
                }
            }
        });
    }
}
