package com.example.a10021740.asyncdemo;

import android.os.AsyncTask;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {


    String s;
    URL url;
    URLConnection urlConnection;
    InputStream inputStream;
    String JSONInfo="";
    EditText editText;
    Button button;
    JSONObject weatherData;

    ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView image4;
    ImageView image5;

    TextView time1;
    TextView time2;
    TextView time3;
    TextView time4;
    TextView time5;

    TextView tempMin1;
    TextView tempMin2;
    TextView tempMin3;
    TextView tempMin4;
    TextView tempMin5;

    TextView tempMax1;
    TextView tempMax2;
    TextView tempMax3;
    TextView tempMax4;
    TextView tempMax5;






    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        time1 = findViewById(R.id.time1);
        time2 = findViewById(R.id.time2);
        time3 = findViewById(R.id.time3);
        time4 = findViewById(R.id.time4);
        time5 = findViewById(R.id.time5);

        tempMin1 = findViewById(R.id.tempMin1);
        tempMin2 = findViewById(R.id.tempMin2);
        tempMin3 = findViewById(R.id.tempMin3);
        tempMin4 = findViewById(R.id.tempMin4);
        tempMin5 = findViewById(R.id.tempMin5);

        tempMax1 = findViewById(R.id.tempMax1);
        tempMax2 = findViewById(R.id.tempMax2);
        tempMax3 = findViewById(R.id.tempMax3);
        tempMax4 = findViewById(R.id.tempMax4);
        tempMax5 = findViewById(R.id.tempMax5);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = editText.getText().toString();
                if (editText.getText()!= null) {
                        new AsyncThread().execute(s);
                }
            }
        });



        Log.d("TAG","AYY WE MADE IT");
        Log.d("TAG", "ASKJFHJKASFHJKASHFJKHASJFKHJKAFSHKJHASJKFHASJKFHJKASF");
        Log.d("TAG", JSONInfo);


    }

    public class AsyncThread extends AsyncTask<String, Void, Void>{


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d("TAG", JSONInfo);

            //ACCESSING THE WEATHER (WORKS)
            try {
                weatherData = new JSONObject(JSONInfo);
                JSONArray array = weatherData.getJSONArray("list");
                for (int x = 0; x < 5; x++) {
                    JSONObject objectWeather = (JSONObject)(array.get(x));
                    JSONArray arrayWeather = objectWeather.getJSONArray("weather");
                    JSONObject insideObjectWeather = (JSONObject)(arrayWeather.get(0));
                    Log.d("TAG", insideObjectWeather.getString("main") );
                    String weather = insideObjectWeather.getString("main");

                    if (x == 0) {
                        if (weather.equalsIgnoreCase("clear"))
                            image1.setImageResource(R.drawable.clear);
                        if (weather.equalsIgnoreCase("rain"))
                            image1.setImageResource(R.drawable.rain);
                        if (weather.equalsIgnoreCase("clouds"))
                            image1.setImageResource(R.drawable.download);
                    }
                    if (x == 1) {
                        if (weather.equalsIgnoreCase("clear"))
                            image2.setImageResource(R.drawable.clear);
                        if (weather.equalsIgnoreCase("rain"))
                            image2.setImageResource(R.drawable.rain);
                        if (weather.equalsIgnoreCase("clouds"))
                            image2.setImageResource(R.drawable.download);
                    }
                    if (x == 2) {
                        if (weather.equalsIgnoreCase("clear"))
                            image3.setImageResource(R.drawable.clear);
                        if (weather.equalsIgnoreCase("rain"))
                            image3.setImageResource(R.drawable.rain);
                        if (weather.equalsIgnoreCase("clouds"))
                            image3.setImageResource(R.drawable.download);
                    }
                    if (x == 3) {
                        if (weather.equalsIgnoreCase("clear"))
                            image4.setImageResource(R.drawable.clear);
                        if (weather.equalsIgnoreCase("rain"))
                            image4.setImageResource(R.drawable.rain);
                        if (weather.equalsIgnoreCase("clouds"))
                            image4.setImageResource(R.drawable.download);
                    }
                    if (x == 4) {
                        if (weather.equalsIgnoreCase("clear"))
                            image5.setImageResource(R.drawable.clear);
                        if (weather.equalsIgnoreCase("rain"))
                            image5.setImageResource(R.drawable.rain);
                        if (weather.equalsIgnoreCase("clouds"))
                            image5.setImageResource(R.drawable.download);
                    }


                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            //ACCESSING THE TEMPERATURE (WORKS)
            try {
                weatherData = new JSONObject(JSONInfo);
                JSONArray array = weatherData.getJSONArray("list");
                for (int x = 0; x < 5; x++) {
                    JSONObject objectWeather = (JSONObject)(array.get(x));
                    JSONObject objectInside = objectWeather.getJSONObject("main");
                    double tempKelvin = Double.parseDouble(objectInside.getString("temp"));
                    int tempFarenheight = (int)(Math.round(((tempKelvin-273.15)*(9/5)+32)));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            //ACCESSING THE MINIMUM TEMPERATURE
            try {
                weatherData = new JSONObject(JSONInfo);
                JSONArray array = weatherData.getJSONArray("list");
                for (int x = 0; x < 5; x++) {
                    JSONObject objectWeather = (JSONObject)(array.get(x));
                    JSONObject objectInside = objectWeather.getJSONObject("main");
                    double tempKelvin = Double.parseDouble(objectInside.getString("temp_min"));
                    int tempFarenheight = (int)(Math.round(((tempKelvin-273.15)*(9/5)+32)));
                    if (x == 0)
                        tempMin1.setText("MIN TEMP: "+Integer.toString(tempFarenheight));
                    if (x == 1)
                        tempMin2.setText("MIN TEMP: "+Integer.toString(tempFarenheight));
                    if (x == 2)
                        tempMin3.setText("MIN TEMP: "+Integer.toString(tempFarenheight));
                    if (x == 3)
                        tempMin4.setText("MIN TEMP: "+Integer.toString(tempFarenheight));
                    if (x == 4)
                        tempMin5.setText("MIN TEMP: "+Integer.toString(tempFarenheight));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            //ACCESSING THE MAXIMUM TEMPERATURE
            try {
                weatherData = new JSONObject(JSONInfo);
                JSONArray array = weatherData.getJSONArray("list");
                for (int x = 0; x < 5; x++) {
                    JSONObject objectWeather = (JSONObject)(array.get(x));
                    JSONObject objectInside = objectWeather.getJSONObject("main");
                    double tempKelvin = Double.parseDouble(objectInside.getString("temp_max"));
                    int tempFarenheight = (int)(Math.round(((tempKelvin-273.15)*(9/5)+32)));
                    if (x == 0)
                        tempMax1.setText("MAX TEMP: "+Integer.toString(tempFarenheight));
                    if (x == 1)
                        tempMax2.setText("MAX TEMP: "+Integer.toString(tempFarenheight));
                    if (x == 2)
                        tempMax3.setText("MAX TEMP: "+Integer.toString(tempFarenheight));
                    if (x == 3)
                        tempMax4.setText("MAX TEMP: "+Integer.toString(tempFarenheight));
                    if (x == 4)
                        tempMax5.setText("MAX TEMP: "+Integer.toString(tempFarenheight));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            //ACCESSING THE TIME (WORKS) ( DONE )
            try {
                weatherData = new JSONObject(JSONInfo);
                JSONArray array = weatherData.getJSONArray("list");
                for (int x = 0; x < 5; x++) {
                    JSONObject objectWeather = (JSONObject)(array.get(x));
                    String []parts = (objectWeather.getString("dt_txt")).split(" ");
                    Log.d("TAG", parts[1]);
                    parts[1] = Integer.toString(Integer.parseInt( parts[1].substring(0,2) ) - 5);
                    //Log.d("TAG", parts[1]);

                    if (Integer.parseInt(parts[1]) <= 0)
                        parts[1] = Integer.toString(Integer.parseInt(parts[1])+12);

                    if (Integer.parseInt(parts[1]) > 12)
                        parts[1] = Integer.toString(Integer.parseInt(parts[1])%12)+" PM";
                    else
                        parts[1] = Integer.toString(Integer.parseInt(parts[1])+0)+" AM";


                    if (x == 0)
                        time1.setText(parts[1]);
                    if (x == 1)
                        time2.setText(parts[1]);
                    if (x == 2)
                        time3.setText(parts[1]);
                    if (x == 3)
                        time4.setText(parts[1]);
                    if (x == 4)
                        time5.setText(parts[1]);


                    Log.d("TAG", parts[1]);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected Void doInBackground(String... strings) {

            try {
                url = new URL("https://api.openweathermap.org/data/2.5/forecast?zip=" + s + "&appid=efb9fab2294ae47f1cfe5b0af186a4ba");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            {
                try {
                    urlConnection = url.openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            {
                try {
                    inputStream = urlConnection.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            try {
                String st;
                while ( (st = bufferedReader.readLine()) != null) {
                    Log.d("TAG",st);
                    JSONInfo += st;

                }
            }catch(IOException e){}


            return null;
        }
    }



}
