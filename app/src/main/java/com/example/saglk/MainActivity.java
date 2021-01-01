package com.example.saglk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.huawei.hms.ads.AdListener;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.BannerAdSize;
import com.huawei.hms.ads.HwAds;
import com.huawei.hms.ads.InterstitialAd;
import com.huawei.hms.ads.banner.BannerView;

public class MainActivity extends AppCompatActivity {

    InterstitialAd interstitialAd; //GLOBAL TANIMLADIK

    private EditText editText;
    private TextView boy_tv,durum_tv,ideal_tv,kilo_tv;
    private SeekBar seekBar;
    private RadioGroup radioGroup;
    private boolean erkekmi = true;
    private double boy = 0.0;
    private int kilo = 50;
    private RadioGroup.OnCheckedChangeListener radioGroupOlayIsleyicisi= new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId==R.id.bay)
                erkekmi=true;
            else if (checkedId==R.id.bayan)
                erkekmi=false;

            guncelle();
        }
    };
    private SeekBar.OnSeekBarChangeListener seekBarOlayIsleyicisi=new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            kilo=30+progress;
            guncelle();

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
    private TextWatcher editTextOlayIsleyicisi=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            try {
                boy=Double.parseDouble(s.toString())/100.0;

            }catch (NumberFormatException e){
                boy=0.00;
            }
            guncelle();

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HwAds.init(this); //HMS ads servisini tetikledik.
        loadBannerAdd(); //yüklenirken banneri yükledik
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdId("testb4znbuh3n2");//video test id ekledik.
        interstitialAd.setAdListener(adListener);
        AdParam adParam = new AdParam.Builder().build();
        interstitialAd.loadAd(adParam);

        editText=(EditText) findViewById(R.id.editTextNumber);
        boy_tv=(TextView) findViewById(R.id.boy_tv);
        durum_tv=(TextView) findViewById(R.id.durum_tv);
        ideal_tv=(TextView) findViewById(R.id.ideal_tv);
        kilo_tv=(TextView) findViewById(R.id.kilo_tv);
        radioGroup=(RadioGroup) findViewById(R.id.radioGrup);
        seekBar=(SeekBar) findViewById(R.id.seekBar);

        editText.addTextChangedListener(editTextOlayIsleyicisi);
        seekBar.setOnSeekBarChangeListener(seekBarOlayIsleyicisi);
        radioGroup.setOnCheckedChangeListener(radioGroupOlayIsleyicisi);
        
        guncelle();
    }

    private void guncelle() {

        kilo_tv.setText(String.valueOf(kilo)+" kg");
        boy_tv.setText(String.valueOf(boy)+" m");

        int ideal_kiloBay= (int) (50+2.3*(boy*100*0.4-60));
        int ideal_kiloBayan= (int) (45.5+2.3*(boy*100*0.4-60));
        double vki=kilo/(boy*boy);

        if (erkekmi){
            //erkek ise
            ideal_tv.setText(String.valueOf(ideal_kiloBay));
                if (vki<=20.7){
                    durum_tv.setBackgroundResource(R.color.zayif);
                    durum_tv.setText(R.string.zayif);
                }else if (20.7<vki && vki<=26.4){
                    //ideal kilo
                    durum_tv.setBackgroundResource(R.color.ideal);
                    durum_tv.setText(R.string.durum_ideal);
                }else if (26.4<vki && vki<=27.8) {
                    //normal kilodan fazla
                    durum_tv.setBackgroundResource(R.color.normalden_fazla);
                    durum_tv.setText(R.string.durum_idealden_fazla);
                }else if (27.8<vki && vki<=31.1) {
                    //fazla kilolu
                    durum_tv.setBackgroundResource(R.color.fazla_kilolu);
                    durum_tv.setText(R.string.durum_fazla_kilolu);
                }else if (31.1<vki && vki<=34.9) {
                    //obez
                    durum_tv.setBackgroundResource(R.color.obez);
                    durum_tv.setText(R.string.durum_obez);
                }else {
                    //doktor tedavisi
                    durum_tv.setBackgroundResource(R.color.doktora);
                    durum_tv.setText(R.string.durum_doktora);
                }


        }else{
            //bayan ise
            ideal_tv.setText(String.valueOf(ideal_kiloBayan));
                if (vki<=19.1){
                    durum_tv.setBackgroundResource(R.color.zayif);
                    durum_tv.setText(R.string.zayif);
                }else if (19.1<vki && vki<=25.8){
                    //ideal kilo
                    durum_tv.setBackgroundResource(R.color.ideal);
                    durum_tv.setText(R.string.durum_ideal);
                }else if (25.8<vki && vki<=27.3) {
                    //normal kilodan fazla
                    durum_tv.setBackgroundResource(R.color.normalden_fazla);
                    durum_tv.setText(R.string.durum_idealden_fazla);
                }else if (27.3<vki && vki<=32.3) {
                    //fazla kilolu
                    durum_tv.setBackgroundResource(R.color.fazla_kilolu);
                    durum_tv.setText(R.string.durum_fazla_kilolu);
                }else if (32.3<vki && vki<=34.9) {
                    //obez
                    durum_tv.setBackgroundResource(R.color.obez);
                    durum_tv.setText(R.string.durum_obez);
                }else {
                    //doktor tedavisi
                    durum_tv.setBackgroundResource(R.color.doktora);
                    durum_tv.setText(R.string.durum_doktora);
                }
        }

    }
    public void loadBannerAdd() {
        //ad parmeter objesini oluşturduk
        AdParam adParam = new AdParam.Builder().build();
        //banner view oluşturduk.
        BannerView bannerView = new BannerView(this);
        // Reklam ıd miz
        bannerView.setAdId("testw6vs28auh3");
        bannerView.setBannerAdSize(BannerAdSize.BANNER_SIZE_360_57);
        // Add BannerView to the layout.
        //Reklamın gözükeceği layout eriştik.
        RelativeLayout rootView = findViewById(R.id.Bannerüst);
        rootView.addView(bannerView);

        bannerView.loadAd(adParam);
    } //Reklam kodları
    private final AdListener adListener = new AdListener() {
        @Override
        public void onAdLoaded() {
            super.onAdLoaded();
            Toast.makeText(MainActivity.this, "Reklam Yüklendi", Toast.LENGTH_SHORT).show();
            // Display an interstitial ad.
            showInterstitial();
        }

        @Override
        public void onAdFailed(int errorCode) {
            Toast.makeText(MainActivity.this, "Reklam yüklemesi hata koduyla başarısız oldu: " + errorCode,
                    Toast.LENGTH_SHORT).show();
            Log.d("TAG", "Reklam yüklemesi hata koduyla başarısız oldu: " + errorCode);
        }

        @Override
        public void onAdClosed() {
            super.onAdClosed();
            Log.d("TAG", "Kapanan Reklam");
        }

        @Override
        public void onAdClicked() {
            Log.d("TAG", "Tıklanan Reklam");
            super.onAdClicked();
        }

        @Override
        public void onAdOpened() {
            Log.d("TAG", "Açılan Reklam");
            super.onAdOpened();
        }
    };//Reklam kodları

    private void showInterstitial() {

        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            Toast.makeText(this, "Reklam Yüklenmedi", Toast.LENGTH_SHORT).show();
        }
    }//Reklam kodları

}