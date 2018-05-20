package com.bitm.wr;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mobile App Develop on 5/12/2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.WeatherHolder> {
    private  int total;
    private ForecastWeather forecastWeather;
    private List<ForecastWeather.NList>fList;

    public CustomAdapter(ForecastWeather forecastWeather, List<ForecastWeather.NList>fList) {
        this.total = forecastWeather.getList().size();
        this.forecastWeather = forecastWeather;
        this.fList = fList;
        Log.e("CALLED", "CustomAdapter: "+total );

    }

    @Override
    public WeatherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.weather_row,parent,false);
        WeatherHolder holder = new WeatherHolder(v);
        Log.e("createVH", "created holder: " );
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull WeatherHolder holder, int position) {

        holder.minWeather.setText("Min : "+String.valueOf(fList.get(position).getMain().getTempMin()));
        Log.e("INCOME", "onBindViewHolder: "+fList.get(position).getMain().getTempMin());
        holder.maxWeather.setText("Max : "+String.valueOf(fList.get(position).getMain().getTempMax()));

        String month = String.valueOf(fList.get(position).getDt());

        Date date = new Date(Long.parseLong(String.valueOf(month)+"000"));
        SimpleDateFormat formater1 = new SimpleDateFormat("MMMM dd, yyyy");
        String tym1 = formater1.format(date);

        String days = String.valueOf(fList.get(position).getDt());

        Date date1 = new Date(Long.parseLong(String.valueOf(days)+"000"));
        SimpleDateFormat formater2 = new SimpleDateFormat("EEEE");
        String tym = formater2.format(date1);

//        Target target = new Target() {
//            @Override
//            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                imageView.setImageBitmap(bitmap);
//                Drawable image = imageView.getDrawable();
//            }
//
//            @Override
//            public void onBitmapFailed(Drawable errorDrawable) {}
//
//            @Override
//            public void onPrepareLoad(Drawable placeHolderDrawable) {}
//        };
//
//        Picasso.with(this).load("url").into(target);


        holder.monthlyDate.setText(tym1);
        holder.weeklyDate.setText(tym);
      //  Log.d("bar", "onBindViewHolder: " + fList.get(position).getDt() );
       // holder.weeklyDate.setText(String.valueOf(fList.get(position).getMain().g));
      //  holder.monthlyDate.setText(String.valueOf(fList.get(position).getMain().getTempMax()));
//        holder.weeklyDate.setText(forecastWeather.);
//        holder.monthlyDate.setText(forecastWeather.);
       // holder.iv.s;


    }

    @Override
    public int getItemCount() {
        Log.e("TOTAL", String.valueOf(total) );
        return fList.size();
    }

    public class WeatherHolder extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView maxWeather, minWeather, weeklyDate, monthlyDate;

        public WeatherHolder(View itemView) {
            super(itemView);

            iv = itemView.findViewById(R.id.weathePic);
            maxWeather = itemView.findViewById(R.id.maxTemp);
            minWeather = itemView.findViewById(R.id.minTemp);
            weeklyDate = itemView.findViewById(R.id.weeklyDay);
            monthlyDate = itemView.findViewById(R.id.monthlyDate);


        }
    }
}
