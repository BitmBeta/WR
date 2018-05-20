package com.bitm.wr;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class Current extends Fragment {

    private static final String CURRENTW="http://api.openweathermap.org/data/2.5/";
    private Services service;
    CurrentWeather currentWeather;
    TextView tv1,tv2,tv3,tv4,tv5, humdidityTv, pressureTv, currentDateTv;
    View v;

    public Current() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(CURRENTW).addConverterFactory(GsonConverterFactory.create()).build();
        service=retrofit.create(Services.class);
        String appid=getString(R.string.api);
        String url="weather?q= dhaka&units=metric&"+appid;

        Call<CurrentWeather> weatherCall=service.getweather(url);
        //Call<CurrentWeather> weatherCall=service.getweather(url);
        
        weatherCall.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                if (response.code()==200){
                    currentWeather=response.body();
                    load();
                }
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {

            }
        });



        v= inflater.inflate(R.layout.fragment_current, container, false);
        return v;

    }
    private void  load(){
        long s,y;
        double tem=currentWeather.getMain().getTemp();
        double max=currentWeather.getMain().getTempMax();
        double min=currentWeather.getMain().getTempMin();
         s=currentWeather.getSys().getSunrise();
         y=currentWeather.getSys().getSunset();


       /* sec=s%60;
        min1= (s/60) %60;
        hour= (s / (60*60)) % 24;

        String time = String.format("%d:%02d:%02d", hour, min1, sec);

        sec1=y%60;
        min2= (y/60) %60;
        hour1= (y / (60*60)) % 24;

        String time1 = String.format("%d:%02d:%02d", hour1, min2, sec1);*/

       //DateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.);



        Date date1 = new Date(Long.parseLong(String.valueOf(s)+"000"));
        SimpleDateFormat formater1 = new SimpleDateFormat("hh:mm:ss a");
        String tym1 = formater1.format(date1);

        Date date2 = new Date(Long.parseLong(String.valueOf(y)+"000"));
        SimpleDateFormat formater2 = new SimpleDateFormat("hh:mm:ss a");
        String tym2 = formater2.format(date2);


        // String s=String.valueOf(tem);
        tv1=  v.findViewById(R.id.textView);
        tv1.setText(""+tem);
        tv2=  v.findViewById(R.id.maxvalue);
        tv2.setText(max+"");
        tv3=  v.findViewById(R.id.Minvalue);
        tv3.setText(min+"");
        tv4=v.findViewById(R.id.textView3);
        tv4.setText("Sunrise"+"\n"+tym1);
        tv5=v.findViewById(R.id.textView4);
        tv5.setText("Sunset"+"\n"+tym2);
        humdidityTv = v.findViewById(R.id.humidityTv);
        humdidityTv.setText(String.valueOf(currentWeather.getMain().getHumidity()));

        pressureTv = v.findViewById(R.id.pressureTv);
        pressureTv.setText(String.valueOf(currentWeather.getMain().getPressure()));


        String dd = String.valueOf(currentWeather.getDt());

        Date date3 = new Date(Long.parseLong(String.valueOf(dd)+"000"));
        SimpleDateFormat formater3 = new SimpleDateFormat("MM/ dd / yyyy");
        String ttt = formater3.format(date3);

        currentDateTv = v.findViewById(R.id.currentDateTv);
        currentDateTv.setText(ttt);

    }

}
