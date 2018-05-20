package com.bitm.wr;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class Forecast extends Fragment {

    private RecyclerView recyclerView;
    private static final String FORECAST_WEATHER="http://api.openweathermap.org/data/2.5/";
    private Services service;
    private ForecastWeather forecastWeather;
    private  View v;


    public Forecast() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_forecast, container, false);
        recyclerView = v.findViewById(R.id.recycleview);

        Retrofit retrofit=new Retrofit.Builder().baseUrl(FORECAST_WEATHER).addConverterFactory(GsonConverterFactory.create()).build();
        service=retrofit.create(Services.class);
        String appid=getString(R.string.api);
        String url="forecast?q=dhaka&units=metric&cnt=10&"+appid;
        Log.e("URL", url );
          Call<ForecastWeather> weatherCall=service.getWeather(url);
          weatherCall.enqueue(new Callback<ForecastWeather>() {
              @Override
              public void onResponse(Call<ForecastWeather> call, Response<ForecastWeather> response) {
                  if(response.code()==200){
                      forecastWeather = response.body();
                      List<ForecastWeather.NList> fList = forecastWeather.getList();
                      Log.e("income1222 ", "innerResponse "+fList.size());
                      CustomAdapter adapter = new CustomAdapter(forecastWeather,fList);
                      LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                      llm.setOrientation(LinearLayoutManager.VERTICAL);
                      recyclerView.setLayoutManager(llm);
                      recyclerView.setAdapter(adapter);


                  }else{
                      Log.e("ERRORCODE", "onResponse: " + String.valueOf(response.code()) );
                  }
              }
              @Override
              public void onFailure(Call<ForecastWeather> call, Throwable t) {
                  Log.e("income1222 ", "failed "+t.getMessage());
              }
          });
        return v;
    }
    public List<ForecastWeather> generateWeather(){
        List<ForecastWeather> forcast = new ArrayList<>();

        forcast.add(new ForecastWeather());
        return forcast;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
