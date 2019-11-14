package Eunsung.com;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class lottoActivity extends AppCompatActivity {
    TextView Textlotto_get1,Textlotto_get2,Textlotto_get3,Textlotto_get4,Textlotto_get5 ;

    Button past_list1, past_list2, past_list3, past_list4, past_list5, button_again;
    TextView reload; //reload버튼
    Elements contents;
    Document doc = null;
    ArrayList WinNumber = new ArrayList();
    private InterstitialAd mInterstitialAd;
    String Top10;//결과를 저장할 문자열변수

    final int[] lotto1 = lotto();
    final int[] lotto2 = lotto();
    final int[] lotto3 = lotto();
    final int[] lotto4 = lotto();
    final int[] lotto5 = lotto();

    //main(String [] args)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lotto);

        //Ad-Banner & Interstitial Ad
        AdTogether();

        Arrays.sort(lotto1);
        wordParsing(Arrays.toString(lotto1),1);

        Arrays.sort(lotto2);
        wordParsing(Arrays.toString(lotto2),2);

        Arrays.sort(lotto3);
        wordParsing(Arrays.toString(lotto3),3);

        Arrays.sort(lotto4);
        wordParsing(Arrays.toString(lotto4),4);

        Arrays.sort(lotto5);
        wordParsing(Arrays.toString(lotto5),5);

        // 1. 값을 가져온다
        // 2. 클릭을 감지한다.
        //3. 1번의 값을 다음 액티비티로 넘긴다



        past_list1 = findViewById(R.id.past_list1);
        past_list1.setClickable(true);
        past_list1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                InterstitialAdSetting(1);
            }


        });


        past_list2 = findViewById(R.id.past_list2);
        past_list2.setClickable(true);
        past_list2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                InterstitialAdSetting(2);


            }


        });
        past_list3 = findViewById(R.id.past_list3);
        past_list3.setClickable(true);
        past_list3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                InterstitialAdSetting(3);

            }


        });
        past_list4 = findViewById(R.id.past_list4);
        past_list4.setClickable(true);
        past_list4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                InterstitialAdSetting(4);


            }

        });

        past_list5 = findViewById(R.id.past_list5);
        past_list5.setClickable(true);
        past_list5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                InterstitialAdSetting(5);

            }


        });


        button_again = findViewById(R.id.button_again);
        button_again.setClickable(true);
        button_again.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Interstitial Ad
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
                //Interstitial Ad
                Intent intent = new Intent(lottoActivity.this, lottoActivity.class);
                startActivity(intent);

                finish();
            }

        });


    }
 public void InterstitialAdSetting(int Number){
     String lottoTmp = "";
     if (Number==1){           lottoTmp = Arrays.toString(lotto1);      }
     if (Number==2){           lottoTmp = Arrays.toString(lotto2);      }
     if (Number==3){           lottoTmp = Arrays.toString(lotto3);      }
     if (Number==4){           lottoTmp = Arrays.toString(lotto4);      }
     if (Number==5){           lottoTmp = Arrays.toString(lotto5);      }

     final String finalLottoTmp = lottoTmp;
    //Interstitial Ad
    if (mInterstitialAd.isLoaded()) {
        mInterstitialAd.show();
    } else {
        Log.d("TAG", "The interstitial wasn't loaded yet.");

        Intent intent = new Intent(lottoActivity.this, lottodtlActivity.class);
        intent.putExtra("Number1", finalLottoTmp);
        startActivity(intent);
    }

     mInterstitialAd.setAdListener(new AdListener() {
        @Override
        public void onAdClosed() {
            Intent intent = new Intent(lottoActivity.this, lottodtlActivity.class);
            intent.putExtra("Number1", finalLottoTmp);
            startActivity(intent);
        }
    });
    //Interstitial Ad

}
    // Lotto_SP
    private static int[] lotto_SP() {
        int[] lottoT = new int[45];
        int[] lotto = new int[6];
        for (int i = 0; i < lottoT.length; i++) {
            lottoT[i] = i + 1;
        }
        int tmp = 0;
        for (int i = 0; i < lottoT.length; i++) {
            int j = (int) (Math.random() * 45);
            tmp = lottoT[i];
            lottoT[i] = lottoT[j];
            lottoT[j] = tmp;
        }
        System.arraycopy(lottoT, 0, lotto, 0, 6);
        return lotto;
    }

    // Lotto_CP
    private static int[] lotto_CP() {
        int[] lotto = new int[6];
        int[] lottoT = new int[45];
        for (int i = 0; i < lottoT.length; i++) {
            lottoT[i] = i + 1;
        }
        int length = lottoT.length;
        for (int i = 0; i < lotto.length; i++) {
            int j = (int) (Math.random() * length);
            lotto[i] = lottoT[j];
            lottoT[j] = lottoT[length - 1];
            length--;
        }
        return lotto;
    }

    // Lotto 이중 for문
    private static int[] lotto() {
        int[] Lotto = new int[6];
        for (int i = 0; i < Lotto.length; i++) {
            Lotto[i] = (int) (Math.random() * 45) + 1;
            for (int j = 0; j < i; j++) {
                if (Lotto[i] == Lotto[j])
                    i--;
            }
        }
        return Lotto;
    }
    private void AdTogether(){

        // banner -->
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
       // adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");  // 테스트
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
        mInterstitialAd.setAdUnitId("ca-app-pub-1420948259689687/4531620262"); //리얼
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        //  <-- Interstitial Ad

    }

    public void wordParsing(String words, int count){

        words = words.replaceAll("\\[", "");
        words = words.replaceAll("\\]", "");
        String[] part = words.split(",");

        for (int k =0; k < part.length; k++){
            ImageView imageview = null;


            if (count==1){
                if       (k==0){ imageview = (ImageView)findViewById(R.id.lottoNumber11); }
                else if (k==1){ imageview = (ImageView)findViewById(R.id.lottoNumber12); }
                else if (k==2){ imageview = (ImageView)findViewById(R.id.lottoNumber13); }
                else if (k==3){ imageview = (ImageView)findViewById(R.id.lottoNumber14); }
                else if (k==4){ imageview = (ImageView)findViewById(R.id.lottoNumber15); }
                else if (k==5){ imageview = (ImageView)findViewById(R.id.lottoNumber16); }

            }
            if (count==2){
                if       (k==0){ imageview = (ImageView)findViewById(R.id.lottoNumber21); }
                else if (k==1){ imageview = (ImageView)findViewById(R.id.lottoNumber22); }
                else if (k==2){ imageview = (ImageView)findViewById(R.id.lottoNumber23); }
                else if (k==3){ imageview = (ImageView)findViewById(R.id.lottoNumber24); }
                else if (k==4){ imageview = (ImageView)findViewById(R.id.lottoNumber25); }
                else if (k==5){ imageview = (ImageView)findViewById(R.id.lottoNumber26); }
            }
            if (count==3){
                if       (k==0){ imageview = (ImageView)findViewById(R.id.lottoNumber31); }
                else if (k==1){ imageview = (ImageView)findViewById(R.id.lottoNumber32); }
                else if (k==2){ imageview = (ImageView)findViewById(R.id.lottoNumber33); }
                else if (k==3){ imageview = (ImageView)findViewById(R.id.lottoNumber34); }
                else if (k==4){ imageview = (ImageView)findViewById(R.id.lottoNumber35); }
                else if (k==5){ imageview = (ImageView)findViewById(R.id.lottoNumber36); }
            }
            if (count==4){
                if       (k==0){ imageview = (ImageView)findViewById(R.id.lottoNumber41); }
                else if (k==1){ imageview = (ImageView)findViewById(R.id.lottoNumber42); }
                else if (k==2){ imageview = (ImageView)findViewById(R.id.lottoNumber43); }
                else if (k==3){ imageview = (ImageView)findViewById(R.id.lottoNumber44); }
                else if (k==4){ imageview = (ImageView)findViewById(R.id.lottoNumber45); }
                else if (k==5){ imageview = (ImageView)findViewById(R.id.lottoNumber46); }
            }
            if (count==5){
                if       (k==0){ imageview = (ImageView)findViewById(R.id.lottoNumber51); }
                else if (k==1){ imageview = (ImageView)findViewById(R.id.lottoNumber52); }
                else if (k==2){ imageview = (ImageView)findViewById(R.id.lottoNumber53); }
                else if (k==3){ imageview = (ImageView)findViewById(R.id.lottoNumber54); }
                else if (k==4){ imageview = (ImageView)findViewById(R.id.lottoNumber55); }
                else if (k==5){ imageview = (ImageView)findViewById(R.id.lottoNumber56); }
            }
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
