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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class lottodtlActivity extends AppCompatActivity {
    TextView Textlotto_get1,Textlotto_get2,Textlotto_get3,Textlotto_get4,Textlotto_get5 ;

    Button past_list1, past_list2, past_list3, past_list4, past_list5;
    TextView textView; //결과를 띄어줄 TextView
    TextView reload; //reload버튼
    Elements contents;
    Document doc = null;
    ArrayList WinNumber = new ArrayList();
    private InterstitialAd mInterstitialAd;
    public String getNumber;
    String Top10;//결과를 저장할 문자열변수

    TextView result1 ,result2 ,result3 ,result4 ,result5 ;


    //main(String [] args)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lotto_dtl);

        //Ad-Banner & Interstitial Ad
        AdTogether();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

       getNumber = bundle.getString("Number1");

        getNumber = getNumber.replaceAll("\\[", "");
        getNumber = getNumber.replaceAll("\\]", "");
        ballSetting(getNumber);
        result1 = (TextView)findViewById(R.id.result1);
        result2 = (TextView)findViewById(R.id.result2);
        result3 = (TextView)findViewById(R.id.result3);
        result4 = (TextView)findViewById(R.id.result4);
        result5 = (TextView)findViewById(R.id.result5);

        // 하드코딩으로 기존 데이터 셋팅
        WinNumberSetting();

        checkWin(getNumber);
        // 1. 값을 가져온다
        // 2. 클릭을 감지한다.
        //3. 1번의 값을 다음 액티비티로 넘긴다


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
        mInterstitialAd.setAdUnitId("ca-app-pub-1420948259689687/4531620262");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        //  <-- Interstitial Ad

    }


public void checkWin(String MyNumber){
    int WinFirst =0;
    int WinSecond =0;
    int WinThird =0;
    int WinFourth =0;
    int WinFifth =0;
    MyNumber = MyNumber.replaceAll(" ","");

        for (int cnt=0; cnt < WinNumber.size() ; cnt++){


            int wincount =0;
            String MagicNumber = (String) WinNumber.get(cnt);
            String[] MagicNumberSplit = MagicNumber.split(",");
            String[] MyNumberSplit = MyNumber.split(",");

            for (int i=0; i < 6; i++){
                for(int j=0;j<6;j++){
                    if(MagicNumberSplit[i].equals(MyNumberSplit[j])){
                        wincount++;
                    }

                }
            }
            if (wincount==3)  WinFifth++;
            if (wincount==4)  WinFourth++;
            if (wincount==5) {
                if (MyNumber.contains(MagicNumberSplit[6])) {
                    WinSecond++;
                } else {
                    WinThird++;
                }
            }
            if (wincount==6)  WinFirst++;
        }
        result1.setText("1 등 당첨 이력 : "+ WinFirst);
        result2.setText("2 등 당첨 이력 : "+ WinSecond);
        result3.setText("3 등 당첨 이력 : "+ WinThird);
        result4.setText("4 등 당첨 이력 : "+ WinFourth);
        result5.setText("5 등 당첨 이력 : "+ WinFifth);
}

    private void ballSetting(String words){

        String[] part = words.split(",");

        for (int k =0; k < part.length; k++){
            ImageView imageview = null;

            if       (k==0){ imageview = (ImageView)findViewById(R.id.lottoNumber1); }
            else if (k==1){ imageview = (ImageView)findViewById(R.id.lottoNumber2); }
            else if (k==2){ imageview = (ImageView)findViewById(R.id.lottoNumber3); }
            else if (k==3){ imageview = (ImageView)findViewById(R.id.lottoNumber4); }
            else if (k==4){ imageview = (ImageView)findViewById(R.id.lottoNumber5); }
            else if (k==5){ imageview = (ImageView)findViewById(R.id.lottoNumber6); }


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

    public void WinNumberSetting(){
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
                    WinNumber.add(str);

                }
            }

            buffer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
