package com.example.myapart;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.Calendar;

public class InfoHome extends AppCompatActivity {

    WebView WebInfoHome;
    private String urlFinished = "";
    private String url1 = "https://100realty.ua/uk/realty_search/apartment/rent";

    private boolean isRedirected;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_home);

        WebInfoHome = (WebView) findViewById(R.id.WebInfoHome);

        final ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle("");
        pd.setMessage("Відкривається!");

        WebSettings webSettings = WebInfoHome.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        Intent intent1 = getIntent();

        String city = intent1.getStringExtra("city");
        String district = intent1.getStringExtra("district");
        String rooms = intent1.getStringExtra("rooms");
        String year = intent1.getStringExtra("year");
        String size = intent1.getStringExtra("size");






        WebInfoHome.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {

                return false;

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (!urlFinished.equals(url1)) {
                    pd.show();
                }

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);


                if (!urlFinished.equals(url1)) {
                    if (city.equals("Київ")) {
                        WebInfoHome.loadUrl("javascript:(function(){document.getElementById('edit-kievcheck-2').click();})()");
                    }
                    WebInfoHome.loadUrl("javascript:(function(){document.getElementById('realty-show-map-link').click();})()");

                    if(district.equals("Печерський район")) {
                        WebInfoHome.loadUrl("javascript:(function(){document.getElementById('1002249').click();})()");
                    }

                    if (district.equals("Голосіївський район")){

                        WebInfoHome.loadUrl("javascript:(function(){document.getElementById('1002248').click();})()");

                    }

                    if (district.equals("Дарницький район")){

                        WebInfoHome.loadUrl("javascript:(function(){document.getElementById('1002242').click();})()");

                    }

                    if (district.equals("Деснянський район")){

                        WebInfoHome.loadUrl("javascript:(function(){document.getElementById('1002241').click();})()");

                    }

                    if (district.equals("Дніпровський район")){

                        WebInfoHome.loadUrl("javascript:(function(){document.getElementById('1002243').click();})()");

                    }

                    if (district.equals("Оболонський район")){

                        WebInfoHome.loadUrl("javascript:(function(){document.getElementById('1002247').click();})()");

                    }

                    if (district.equals("Подільський район")){

                        WebInfoHome.loadUrl("javascript:(function(){document.getElementById('1002250').click();})()");

                    }

                    if (district.equals("Святошинський район")){

                        WebInfoHome.loadUrl("javascript:(function(){document.getElementById('1003198').click();})()");

                    }

                    if (district.equals("Солом'янський район")){

                        WebInfoHome.loadUrl("javascript:(function(){document.getElementById('1002246').click();})()");

                    }

                    if (district.equals("Шевченківський район")){

                        WebInfoHome.loadUrl("javascript:(function(){document.getElementById('1002253').click();})()");

                    }

                    WebInfoHome.loadUrl("javascript:(function(){document.getElementById('realty-confirm-map').click();})()");

                    if(rooms.equals("1")) {
                        WebInfoHome.loadUrl("javascript:(function(){document.getElementById('edit-nrooms-1').click();})()");
                    }

                    if(rooms.equals("2")) {
                        WebInfoHome.loadUrl("javascript:(function(){document.getElementById('edit-nrooms-2').click();})()");
                    }

                    if(rooms.equals("3")) {
                        WebInfoHome.loadUrl("javascript:(function(){document.getElementById('edit-nrooms-3').click();})()");
                    }

                    if(rooms.equals("4")) {
                        WebInfoHome.loadUrl("javascript:(function(){document.getElementById('edit-nrooms-4').click();})()");
                    }

                    if(rooms.equals("5")) {
                        WebInfoHome.loadUrl("javascript:(function(){document.getElementById('edit-nrooms-5').click();})()");
                    }

                    if(rooms.equals("6")) {
                        WebInfoHome.loadUrl("javascript:(function(){document.getElementById('edit-nrooms-5').click();})()");
                    }

                    if(!rooms.equals("7")) {
                        WebInfoHome.loadUrl("javascript:(function(){document.getElementById('edit-nrooms-5').click();})()");
                    }

                    if(rooms.equals("8")) {
                        WebInfoHome.loadUrl("javascript:(function(){document.getElementById('edit-nrooms-5').click();})()");
                    }


                    if(!size.equals("")) {

                        int from = ((Integer.parseInt(size))*75)/100;
                        int to = from + ((Integer.parseInt(size))*50)/50;

                        WebInfoHome.loadUrl("javascript:(function(){document.getElementById('edit-sqrtotal-from').value = '" + from  + "';})()");

                        WebInfoHome.loadUrl("javascript:(function(){document.getElementById('edit-sqrtotal-to').value = '" + to  + "';})()");
                    }

                    if ( !year.equals("")) {

                        int yearhome = Integer.parseInt(year);

                        int today = Calendar.getInstance().get(Calendar.YEAR);

                        int razn = today - yearhome;

                        if (razn < 7) {

                            WebInfoHome.loadUrl("javascript:(function(){document.getElementById('edit-newly').click();})()");

                        }
                    }

                    WebInfoHome.loadUrl("javascript:(setTimeout(function(){document.getElementById('edit-find').click();},500))()");
                }


                urlFinished = url1;

                pd.dismiss();
            }
        });




        WebInfoHome.loadUrl(url1);



    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.WebInfoHome.canGoBack()) {
            this.WebInfoHome.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}