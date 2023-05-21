package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class WeatherActRetrofit extends AppCompatActivity {

    private TextView tvCoordinate, tvTemperature, tvCondition, tvWindSpeed, tvForecast;
    private ImageView imageCondition, imageForecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        tvCoordinate = findViewById(R.id.text_coordinate);
        tvTemperature = findViewById(R.id.text_temperature);
        tvCondition = findViewById(R.id.text_condition);
        tvWindSpeed = findViewById(R.id.text_windspeed);
        imageCondition = findViewById(R.id.image_condition);


        WeatherApiService weatherApiService = RetrofitClient.getRetrofitInstance().create(WeatherApiService.class);
        Call<ForecastResponse> call = weatherApiService.getForecast("-7.98", "112.63", "weathercode", "true", "auto");

        call.enqueue(new Callback<ForecastResponse>() {
            @Override
            public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                if (response.isSuccessful()) {
                    ForecastResponse forecastResponse = response.body();
                    if (forecastResponse != null) {
                        tvCoordinate.setText(forecastResponse.getLatitude() + ", " + forecastResponse.getLongitude());

                        ForecastResponse.CurrentWeather currentWeather = forecastResponse.getCurrentWeather();
                        if (currentWeather != null) {
                            tvTemperature.setText(String.valueOf(currentWeather.getTemperature()));
                            tvWindSpeed.setText(String.valueOf(currentWeather.getWindspeed()));
                            changeConditionImage(imageCondition, tvCondition, (int) currentWeather.getWeathercode());
//                            for (int i = 1; i <= 6; i++) {
//                                imageForecast = findViewById(R.id.image_forecast+i-1);
//                                changeForecastImage(imageForecast, tvForecast, (int) currentWeather.getWeathercode());
//                            }
                        }
                    }
                } else {

                    Log.e("WeatherActivityRetrofit", "Response not successful");
                }
            }

            @Override
            public void onFailure(Call<ForecastResponse> call, Throwable t) {
                Log.e("WeatherActivityRetrofit", "Request failed: " + t.getMessage());
                tvCoordinate.setText("Rusak");
            }
        });
    }

    public interface WeatherApiService {
        @GET("forecast")
        Call<ForecastResponse> getForecast(
                @Query("latitude") String latitude,
                @Query("longitude") String longitude,
                @Query("daily") String daily,
                @Query("current_weather") String currentWeather,
                @Query("timezone") String timezone
        );

    }
    public class ForecastResponse {
            private double latitude;
            private double longitude;
            private CurrentWeather current_weather;
//            private Daily daily;

            public double getLatitude() {
                return latitude;
            }

            public double getLongitude() {
                return longitude;
            }

            public CurrentWeather getCurrentWeather() {
                return current_weather;
            }

//            public Daily getDaily() {
//                return daily;
//            }
//            public class Daily {
//                private double weatherCode;
//
//                public double getWeatherCode() {
//                    return weatherCode;
//                }
//            }

        public class CurrentWeather {
                private double windspeed;
                private double temperature;
                private double weathercode;

                public double getWindspeed() {
                    return windspeed;
                }
                public double getTemperature() {
                    return temperature;
                }
                public double getWeathercode() {
                    return weathercode;
                }
            }
    }
    public void changeConditionImage(ImageView imageCondition, TextView tvCondition, int conditionCode) {
        switch (conditionCode) {
            case 0 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Clear Sky");
                break;
            }
            case 1 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Mainly clear");
                break;
            }
            case 2 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Partly Cloudy");
                break;
            }
            case 3 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Overcast");
                break;
            }
            case 45 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Fog");
                break;
            }
            case 48 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Depositing Rime Fog");
                break;
            }
            case 51 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Light Drizzle");
                break;
            }
            case 53 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Moderate Drizzle");
                break;
            }
            case 55 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Intense Drizzle");
                break;
            }
            case 56 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Light Freezing Drizzle");
                break;
            }
            case 57 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Moderate Freezing Drizzle");
                break;
            }
            case 61 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Slight Rain");
                break;
            }
            case 63 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Moderate Rain");
                break;
            }
            case 65 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Intense Rain");
                break;
            }
            case 66 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Light Freezing Rain");
                break;
            }
            case 67 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Heavy Freezing Rain");
                break;
            }
            case 71 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Slight Snowfall");
                break;
            }
            case 73 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Moderate Snowfall");
                break;
            }
            case 75 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Intense Snowfall");
                break;
            }
            case 77 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Snow Grains");
                break;
            }
            case 80 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Slight Rain Shower");
                break;
            }
            case 81 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Moderate Rain Shower");
                break;
            }
            case 82 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Violent Rain Shower");
                break;
            }
            case 85 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Slight Snow Shower");
                break;
            }
            case 86 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Heavy Snow Shower");
                break;
            }
            case 95 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Thunderstorm");
                break;
            }
            case 96 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Thunderstorm with Slight Hail");
                break;
            }
            case 99 : {
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText("Thunderstorm with Heavy Hail");
                break;
            }
            default:
                imageCondition.setImageResource(R.drawable.sun);
                tvCondition.setText(String.valueOf(conditionCode));
                break;
        }
    }
//    public void changeForecastImage(ImageView imageForecast, TextView tvForecast, int conditionCode) {
//        switch (conditionCode) {
//            case 0: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Clear Sky");
//                break;
//            }
//            case 1: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Mainly clear");
//                break;
//            }
//            case 2: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Partly Cloudy");
//                break;
//            }
//            case 3: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Overcast");
//                break;
//            }
//            case 45: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Fog");
//                break;
//            }
//            case 48: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Depositing Rime Fog");
//                break;
//            }
//            case 51: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Light Drizzle");
//                break;
//            }
//            case 53: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Moderate Drizzle");
//                break;
//            }
//            case 55: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Intense Drizzle");
//                break;
//            }
//            case 56: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Light Freezing Drizzle");
//                break;
//            }
//            case 57: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Moderate Freezing Drizzle");
//                break;
//            }
//            case 61: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Slight Rain");
//                break;
//            }
//            case 63: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Moderate Rain");
//                break;
//            }
//            case 65: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Intense Rain");
//                break;
//            }
//            case 66: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Light Freezing Rain");
//                break;
//            }
//            case 67: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Heavy Freezing Rain");
//                break;
//            }
//            case 71: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Slight Snowfall");
//                break;
//            }
//            case 73: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Moderate Snowfall");
//                break;
//            }
//            case 75: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Intense Snowfall");
//                break;
//            }
//            case 77: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Snow Grains");
//                break;
//            }
//            case 80: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Slight Rain Shower");
//                break;
//            }
//            case 81: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Moderate Rain Shower");
//                break;
//            }
//            case 82: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Violent Rain Shower");
//                break;
//            }
//            case 85: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Slight Snow Shower");
//                break;
//            }
//            case 86: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Heavy Snow Shower");
//                break;
//            }
//            case 95: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Thunderstorm");
//                break;
//            }
//            case 96: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Thunderstorm with Slight Hail");
//                break;
//            }
//            case 99: {
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText("Thunderstorm with Heavy Hail");
//                break;
//            }
//            default:
//                imageCondition.setImageResource(R.drawable.sun);
//                tvCondition.setText(String.valueOf(conditionCode));
//                break;
//        }
//    }
}
