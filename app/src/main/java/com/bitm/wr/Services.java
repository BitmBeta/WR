package com.bitm.wr;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by jupiter on 5/7/2018.
 */

public interface Services {
    @GET()

    Call<CurrentWeather> getweather(@Url String urlString);

    @GET()
    Call<ForecastWeather> getWeather(@Url String url);
}
