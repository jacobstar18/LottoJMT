package Eunsung.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.EditText;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    EditText EditTextEmail,EditPassword ;
    Button TextButton, LottoButton, MyNumberButton, num_history;
    private TextView hoicha, hoicha2;
    private InterstitialAd mInterstitialAd;
    private Button scaleBtn ;
    ImageView iv;
    AdView adView;
    Elements contents;
    Document doc = null;
    private String rlt1, rlt2  ="";
    private AdView adBanner;
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Ad-Banner & Interstitial Ad
        AdTogether();

        /*Animaion Effect*/
       iv = (ImageView) findViewById(R.id.ballimg2);
        Animation animation=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_animation);

       animation.setRepeatCount(Animation.INFINITE);
        iv.startAnimation(animation);
        /*Animaion Effect*/


        // LOG EXAMPLE
        Log.d(this.getClass().getName(),"실행");
        TextButton = findViewById(R.id.Button_login);
        pastLottery(870);
        // 1. 값을 가져온다
        // 2. 클릭을 감지한다.
        //3. 1번의 값을 다음 액티비티로 넘긴다



        TextButton.setClickable(true);
        TextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Interstitial Ad
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent intent = new Intent(MainActivity.this, lottoActivity.class);
                    startActivity(intent);
                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        Intent intent = new Intent(MainActivity.this, lottoActivity.class);
                        startActivity(intent);
                    }
                });
                //Interstitial Ad

            }


        });



        MyNumberButton = findViewById(R.id.my_Number);
        MyNumberButton.setClickable(true);
        MyNumberButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Interstitial Ad
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent intent = new Intent(MainActivity.this, mynumberActivity.class);
                    startActivity(intent);
                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        Intent intent = new Intent(MainActivity.this, mynumberActivity.class);
                        startActivity(intent);
                    }
                });
                //Interstitial Ad

            }


        });


        num_history = findViewById(R.id.num_history);
        num_history.setClickable(true);
        num_history.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Interstitial Ad
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent intent = new Intent(MainActivity.this, lottohistActivity.class);
                    startActivity(intent);
                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        Intent intent = new Intent(MainActivity.this, lottohistActivity.class);
                        startActivity(intent);
                    }
                });
                //Interstitial Ad

            }


        });



    }
    private void AdTogether(){

        // banner -->
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        //adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");  // 테스트
        adView.setAdUnitId("ca-app-pub-1420948259689687/2819403171");  // 리얼
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        // <-- banner

        //Interstitial Ad -->

        mInterstitialAd = new InterstitialAd(this);
        //mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712"); // 테스트
        mInterstitialAd.setAdUnitId("ca-app-pub-1420948259689687/4531620262");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        //  <-- Interstitial Ad

    }
    public void pastLottery(int startNumber){

    // 파일의 내용을 읽어서 TextView 에 보여주기
        try {
            // 파일에서 읽은 데이터를 저장하기 위해서 만든 변수
            StringBuffer data = new StringBuffer();
            FileInputStream fis = openFileInput("numList.txt");//파일명
            BufferedReader buffer = new BufferedReader
                    (new InputStreamReader(fis));
            String str = ""; // 파일에서 한줄을 읽어옴

            for (int k=1; str != null ;k++){
                str = buffer.readLine();
                if (!str.equals(null) && !str.equals("")) {
                    ballSetting(str);

                    hoicha = (TextView)findViewById(R.id.hoicha);
                    hoicha.setText(""+k);

                    hoicha2 = (TextView)findViewById(R.id.hoicha2);
                    hoicha2.setText("회 당첨번호");
                }
            }

            buffer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void ballSetting(String words){

        String[] part = words.split(",");

        for (int k =0; k < part.length; k++){
            imageview = null;

            if      (k==0){ imageview = (ImageView)findViewById(R.id.luckyNum1); }
            else if (k==1){ imageview = (ImageView)findViewById(R.id.luckyNum2); }
            else if (k==2){ imageview = (ImageView)findViewById(R.id.luckyNum3); }
            else if (k==3){ imageview = (ImageView)findViewById(R.id.luckyNum4); }
            else if (k==4){ imageview = (ImageView)findViewById(R.id.luckyNum5); }
            else if (k==5){ imageview = (ImageView)findViewById(R.id.luckyNum6); }
            else if (k==6){ imageview = (ImageView)findViewById(R.id.bnsNum); }


            //imageview.setVisibility(View.VISIBLE);

            if (part[k].trim().equals("1")){imageview.setImageResource(R.drawable.number1);}
            if (part[k].trim().equals("2")){imageview.setImageResource(R.drawable.number2);}
            if (part[k].trim().equals("3")){imageview.setImageResource(R.drawable.number3);}
            if (part[k].trim().equals("4")){imageview.setImageResource(R.drawable.number4);}
            if (part[k].trim().equals("5")){imageview.setImageResource(R.drawable.number5);}
            if (part[k].trim().equals("6")){imageview.setImageResource(R.drawable.number6);}
            if (part[k].trim().equals("7")){imageview.setImageResource(R.drawable.number7);}
            if (part[k].trim().equals("8")){imageview.setImageResource(R.drawable.number8);}
            if (part[k].trim().equals("9")){imageview.setImageResource(R.drawable.number9);}
            if (part[k].trim().equals("10")){imageview.setImageResource(R.drawable.number10);}
            if (part[k].trim().equals("11")){imageview.setImageResource(R.drawable.number11);}
            if (part[k].trim().equals("12")){imageview.setImageResource(R.drawable.number12);}
            if (part[k].trim().equals("13")){imageview.setImageResource(R.drawable.number13);}
            if (part[k].trim().equals("14")){imageview.setImageResource(R.drawable.number14);}
            if (part[k].trim().equals("15")){imageview.setImageResource(R.drawable.number15);}
            if (part[k].trim().equals("16")){imageview.setImageResource(R.drawable.number16);}
            if (part[k].trim().equals("17")){imageview.setImageResource(R.drawable.number17);}
            if (part[k].trim().equals("18")){imageview.setImageResource(R.drawable.number18);}
            if (part[k].trim().equals("19")){imageview.setImageResource(R.drawable.number19);}
            if (part[k].trim().equals("20")){imageview.setImageResource(R.drawable.number20);}
            if (part[k].trim().equals("21")){imageview.setImageResource(R.drawable.number21);}
            if (part[k].trim().equals("22")){imageview.setImageResource(R.drawable.number22);}
            if (part[k].trim().equals("23")){imageview.setImageResource(R.drawable.number23);}
            if (part[k].trim().equals("24")){imageview.setImageResource(R.drawable.number24);}
            if (part[k].trim().equals("25")){imageview.setImageResource(R.drawable.number25);}
            if (part[k].trim().equals("26")){imageview.setImageResource(R.drawable.number26);}
            if (part[k].trim().equals("27")){imageview.setImageResource(R.drawable.number27);}
            if (part[k].trim().equals("28")){imageview.setImageResource(R.drawable.number28);}
            if (part[k].trim().equals("29")){imageview.setImageResource(R.drawable.number29);}
            if (part[k].trim().equals("30")){imageview.setImageResource(R.drawable.number30);}
            if (part[k].trim().equals("31")){imageview.setImageResource(R.drawable.number31);}
            if (part[k].trim().equals("32")){imageview.setImageResource(R.drawable.number32);}
            if (part[k].trim().equals("33")){imageview.setImageResource(R.drawable.number33);}
            if (part[k].trim().equals("34")){imageview.setImageResource(R.drawable.number34);}
            if (part[k].trim().equals("35")){imageview.setImageResource(R.drawable.number35);}
            if (part[k].trim().equals("36")){imageview.setImageResource(R.drawable.number36);}
            if (part[k].trim().equals("37")){imageview.setImageResource(R.drawable.number37);}
            if (part[k].trim().equals("38")){imageview.setImageResource(R.drawable.number38);}
            if (part[k].trim().equals("39")){imageview.setImageResource(R.drawable.number39);}
            if (part[k].trim().equals("40")){imageview.setImageResource(R.drawable.number40);}
            if (part[k].trim().equals("41")){imageview.setImageResource(R.drawable.number41);}
            if (part[k].trim().equals("42")){imageview.setImageResource(R.drawable.number42);}
            if (part[k].trim().equals("43")){imageview.setImageResource(R.drawable.number43);}
            if (part[k].trim().equals("44")){imageview.setImageResource(R.drawable.number44);}
            if (part[k].trim().equals("45")){imageview.setImageResource(R.drawable.number45);}
        }
    }

}
