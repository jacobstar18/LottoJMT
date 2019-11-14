package Eunsung.com;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;


/*팝업은 무조건 FragmentActivity 로 extend 를 한다.,*/
public class numsaveActivity extends FragmentActivity {

    TextView textView; //결과를 띄어줄 TextView
    TextView reload; //reload버튼
    Elements contents;
    Document doc = null;
    private EditText et ;
    private ImageView lottoSave1, lottoSave2, lottoSave3, lottoSave4, lottoSave5, lottoSave6, lottoSave7, lottoSave8,
               lottoSave9, lottoSave10, lottoSave11, lottoSave12, lottoSave13, lottoSave14, lottoSave15, lottoSave16, lottoSave17
            , lottoSave18, lottoSave19, lottoSave20, lottoSave21, lottoSave22, lottoSave23, lottoSave24, lottoSave25, lottoSave26,
            lottoSave27, lottoSave28, lottoSave29, lottoSave30, lottoSave31, lottoSave32, lottoSave33, lottoSave34, lottoSave35,
            lottoSave36, lottoSave37, lottoSave38, lottoSave39, lottoSave40, lottoSave41, lottoSave42, lottoSave43, lottoSave44, lottoSave45;
    private Button savebotton, clearbutton, closebutton;

    private TextView tv;

    ArrayList saveList = new ArrayList();
    private InterstitialAd mInterstitialAd;
    String Top10;//결과를 저장할 문자열변수

    //main(String [] args)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_lotto_save);
        savebotton = findViewById(R.id.savebutton);
        clearbutton = findViewById(R.id.clearbutton);
        closebutton = findViewById(R.id.closebutton);
        //Ad-Banner & Interstitial Ad
        AdTogether();

        lottoSave1 = findViewById(R.id.lottoSave1);
        lottoSave2 = findViewById(R.id.lottoSave2);
        lottoSave3 = findViewById(R.id.lottoSave3);
        lottoSave4 = findViewById(R.id.lottoSave4);
        lottoSave5 = findViewById(R.id.lottoSave5);
        lottoSave6 = findViewById(R.id.lottoSave6);
        lottoSave7 = findViewById(R.id.lottoSave7);
        lottoSave8 = findViewById(R.id.lottoSave8);
        lottoSave9 = findViewById(R.id.lottoSave9);
        lottoSave10 = findViewById(R.id.lottoSave10);
        lottoSave11 = findViewById(R.id.lottoSave11);
        lottoSave12 = findViewById(R.id.lottoSave12);
        lottoSave13 = findViewById(R.id.lottoSave13);
        lottoSave14 = findViewById(R.id.lottoSave14);
        lottoSave15 = findViewById(R.id.lottoSave15);
        lottoSave16 = findViewById(R.id.lottoSave16);
        lottoSave17 = findViewById(R.id.lottoSave17);
        lottoSave18 = findViewById(R.id.lottoSave18);
        lottoSave19 = findViewById(R.id.lottoSave19);
        lottoSave20 = findViewById(R.id.lottoSave20);
        lottoSave21 = findViewById(R.id.lottoSave21);
        lottoSave22 = findViewById(R.id.lottoSave22);
        lottoSave23 = findViewById(R.id.lottoSave23);
        lottoSave24 = findViewById(R.id.lottoSave24);
        lottoSave25 = findViewById(R.id.lottoSave25);
        lottoSave26 = findViewById(R.id.lottoSave26);
        lottoSave27 = findViewById(R.id.lottoSave27);
        lottoSave28 = findViewById(R.id.lottoSave28);
        lottoSave29 = findViewById(R.id.lottoSave29);
        lottoSave30 = findViewById(R.id.lottoSave30);
        lottoSave31 = findViewById(R.id.lottoSave31);
        lottoSave32 = findViewById(R.id.lottoSave32);
        lottoSave33 = findViewById(R.id.lottoSave33);
        lottoSave34 = findViewById(R.id.lottoSave34);
        lottoSave35 = findViewById(R.id.lottoSave35);
        lottoSave36 = findViewById(R.id.lottoSave36);
        lottoSave37 = findViewById(R.id.lottoSave37);
        lottoSave38 = findViewById(R.id.lottoSave38);
        lottoSave39 = findViewById(R.id.lottoSave39);
        lottoSave40 = findViewById(R.id.lottoSave40);
        lottoSave41 = findViewById(R.id.lottoSave41);
        lottoSave42 = findViewById(R.id.lottoSave42);
        lottoSave43 = findViewById(R.id.lottoSave43);
        lottoSave44 = findViewById(R.id.lottoSave44);
        lottoSave45 = findViewById(R.id.lottoSave45);

        ballsetting();

        savebotton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(saveList.size()==6){
                    //저장
                    String data = saveList.toString();

                    try {
                        FileOutputStream fos = openFileOutput
                                ("mysaveList.txt", // 파일명 지정
                                        Context.MODE_APPEND);// 저장모드
                        PrintWriter out = new PrintWriter(fos);
                        out.println(data);
                        out.close();
                        final TextView tv = (TextView) findViewById(R.id.saveView);
                        saveList.clear();
                        tv.setText("저장 되었습니다.");
                        resetImage();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }else{
                    Log.d("TAG", " "+saveList.size() +" 여섯개 입력해주세욥");
                }
            }
        });
        closebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(numsaveActivity.this, mynumberActivity.class);
                startActivity(intent);
                finish();
            }
        });

        clearbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final TextView tv = (TextView) findViewById(R.id.saveView);
                saveList.clear();
                tv.setText("나의 번호를 선택해주세요.");
                resetImage();
            }
        });

    }

    public void saveDataSetting(int number){

        if (saveList.size()>5 ){

            if (saveList.contains(number)) {
                saveList.remove(Integer.valueOf(number));
                final TextView tv = (TextView) findViewById(R.id.saveView);
                Collections.sort(saveList);
                tv.setText(saveList.toString());
            }
        }else {
                    if (saveList.contains(number)) {
                        saveList.remove(Integer.valueOf(number));
                    } else {
                        saveList.add(number);
                    }

                     final TextView tv = (TextView) findViewById(R.id.saveView);
                    if (saveList.size()==0){
                        tv.setText("나의 번호를 선택해주세요.");
                    }else{
                        Collections.sort(saveList);
                        String tmp = saveList.toString();
                        tv.setText(tmp);
                    }
        }
    }

        private void AdTogether () {
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
             mInterstitialAd.setAdUnitId("ca-app-pub-1420948259689687~4707199912");
            mInterstitialAd.loadAd(new AdRequest.Builder().build());

            //  <-- Interstitial Ad

        }
    private void ballsetting(){

        lottoSave1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(1);
                if (saveList.contains(1)) {
                    lottoSave1.setImageResource(R.drawable.number1);
                } else {
                    lottoSave1.setImageResource(R.drawable.notselect1);
                }
            }
        });
        lottoSave2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(2);
                if (saveList.contains(2)) {
                    lottoSave2.setImageResource(R.drawable.number2);
                } else {
                    lottoSave2.setImageResource(R.drawable.notselect2);
                }
            }
        });
        lottoSave3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(3);
                if (saveList.contains(3)) {
                    lottoSave3.setImageResource(R.drawable.number3);
                } else {
                    lottoSave3.setImageResource(R.drawable.notselect3);
                }
            }
        });
        lottoSave4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(4);
                if (saveList.contains(4)) {
                    lottoSave4.setImageResource(R.drawable.number4);
                } else {
                    lottoSave4.setImageResource(R.drawable.notselect4);
                }
            }
        });
        lottoSave5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(5);
                if (saveList.contains(5)) {
                    lottoSave5.setImageResource(R.drawable.number5);
                } else {
                    lottoSave5.setImageResource(R.drawable.notselect5);
                }
            }
        });lottoSave6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(6);
                if (saveList.contains(6)) {
                    lottoSave6.setImageResource(R.drawable.number6);
                } else {
                    lottoSave6.setImageResource(R.drawable.notselect6);
                }
            }
        });
        lottoSave7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(7);
                if (saveList.contains(7)) {
                    lottoSave7.setImageResource(R.drawable.number7);
                } else {
                    lottoSave7.setImageResource(R.drawable.notselect7);
                }
            }
        });
        lottoSave8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(8);
                if (saveList.contains(8)) {
                    lottoSave8.setImageResource(R.drawable.number8);
                } else {
                    lottoSave8.setImageResource(R.drawable.notselect8);
                }
            }
        });
        lottoSave9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(9);
                if (saveList.contains(9)) {
                    lottoSave9.setImageResource(R.drawable.number9);
                } else {
                    lottoSave9.setImageResource(R.drawable.notselect9);
                }
            }
        });
        lottoSave10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(10);

                if (saveList.contains(10)) {
                    lottoSave10.setImageResource(R.drawable.number10);
                } else {
                    lottoSave10.setImageResource(R.drawable.notselect10);
                }
            }
        });
        lottoSave11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(11);
                if (saveList.contains(11)) {
                    lottoSave11.setImageResource(R.drawable.number11);
                } else {
                    lottoSave11.setImageResource(R.drawable.notselect11);
                }

            }
        });
        lottoSave12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(12);
                if (saveList.contains(12)) {
                    lottoSave12.setImageResource(R.drawable.number12);
                } else {
                    lottoSave12.setImageResource(R.drawable.notselect12);
                }
            }
        });
        lottoSave13.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(13);
                if (saveList.contains(13)) {
                    lottoSave13.setImageResource(R.drawable.number13);
                } else {
                    lottoSave13.setImageResource(R.drawable.notselect13);
                }
            }
        });
        lottoSave14.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(14);
                if (saveList.contains(14)) {
                    lottoSave14.setImageResource(R.drawable.number14);
                } else {
                    lottoSave14.setImageResource(R.drawable.notselect14);
                }
            }
        });
        lottoSave15.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(15);
                if (saveList.contains(15)) {
                    lottoSave15.setImageResource(R.drawable.number15);
                } else {
                    lottoSave15.setImageResource(R.drawable.notselect15);
                }
            }
        });
        lottoSave16.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(16);
                if (saveList.contains(16)) {
                    lottoSave16.setImageResource(R.drawable.number16);
                } else {
                    lottoSave16.setImageResource(R.drawable.notselect16);
                }
            }
        });
        lottoSave17.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(17);

                if (saveList.contains(17)) {
                    lottoSave17.setImageResource(R.drawable.number17);
                } else {
                    lottoSave17.setImageResource(R.drawable.notselect17);
                }
            }
        });
        lottoSave18.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { saveDataSetting(18);

                if (saveList.contains(18)) {
                    lottoSave18.setImageResource(R.drawable.number18);
                } else {
                    lottoSave18.setImageResource(R.drawable.notselect18);
                }}
        });
        lottoSave19.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(19);

                if (saveList.contains(19)) {
                    lottoSave19.setImageResource(R.drawable.number19);
                } else {
                    lottoSave19.setImageResource(R.drawable.notselect19);
                }
            }
        });
        lottoSave20.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(20);
                if (saveList.contains(20)) {
                    lottoSave20.setImageResource(R.drawable.number20);
                } else {
                    lottoSave20.setImageResource(R.drawable.notselect20);
                }
            }
        });
        lottoSave21.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(21);
                if (saveList.contains(21)) {
                    lottoSave21.setImageResource(R.drawable.number21);
                } else {
                    lottoSave21.setImageResource(R.drawable.notselect21);
                }
            }
        });
        lottoSave22.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(22);
                if (saveList.contains(22)) {
                    lottoSave22.setImageResource(R.drawable.number22);
                } else {
                    lottoSave22.setImageResource(R.drawable.notselect22);
                }
            }
        });
        lottoSave23.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(23);
                if (saveList.contains(23)) {
                    lottoSave23.setImageResource(R.drawable.number23);
                } else {
                    lottoSave23.setImageResource(R.drawable.notselect23);
                }
            }
        });
        lottoSave24.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(24);
                if (saveList.contains(24)) {
                    lottoSave24.setImageResource(R.drawable.number24);
                } else {
                    lottoSave24.setImageResource(R.drawable.notselect24);
                }
            }
        });
        lottoSave25.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(25);
                if (saveList.contains(25)) {
                    lottoSave25.setImageResource(R.drawable.number25);
                } else {
                    lottoSave25.setImageResource(R.drawable.notselect25);
                }
            }
        });
        lottoSave26.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(26);
                if (saveList.contains(26)) {
                    lottoSave26.setImageResource(R.drawable.number26);
                } else {
                    lottoSave26.setImageResource(R.drawable.notselect26);
                }
            }
        });
        lottoSave27.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(27);
                if (saveList.contains(27)) {
                    lottoSave27.setImageResource(R.drawable.number27);
                } else {
                    lottoSave27.setImageResource(R.drawable.notselect27);
                }
            }
        });
        lottoSave28.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(28);
                if (saveList.contains(28)) {
                    lottoSave28.setImageResource(R.drawable.number28);
                } else {
                    lottoSave28.setImageResource(R.drawable.notselect28);
                }
            }
        });
        lottoSave29.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(29);
                if (saveList.contains(29)) {
                    lottoSave29.setImageResource(R.drawable.number29);
                } else {
                    lottoSave29.setImageResource(R.drawable.notselect29);
                }
            }
        });
        lottoSave30.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(30);
                if (saveList.contains(30)) {
                    lottoSave30.setImageResource(R.drawable.number30);
                } else {
                    lottoSave30.setImageResource(R.drawable.notselect30);
                }
            }
        });
        lottoSave31.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(31);
                if (saveList.contains(31)) {
                    lottoSave31.setImageResource(R.drawable.number31);
                } else {
                    lottoSave31.setImageResource(R.drawable.notselect31);
                }
            }
        });
        lottoSave32.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(32);
                if (saveList.contains(32)) {
                    lottoSave32.setImageResource(R.drawable.number32);
                } else {
                    lottoSave32.setImageResource(R.drawable.notselect32);
                }
            }
        });
        lottoSave33.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(33);
                if (saveList.contains(33)) {
                    lottoSave33.setImageResource(R.drawable.number33);
                } else {
                    lottoSave33.setImageResource(R.drawable.notselect33);
                }
            }
        });
        lottoSave34.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(34);
                if (saveList.contains(34)) {
                    lottoSave34.setImageResource(R.drawable.number34);
                } else {
                    lottoSave34.setImageResource(R.drawable.notselect34);
                }
            }
        });
        lottoSave35.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(35);
                if (saveList.contains(35)) {
                    lottoSave35.setImageResource(R.drawable.number35);
                } else {
                    lottoSave35.setImageResource(R.drawable.notselect35);
                }
            }
        });
        lottoSave36.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(36);
                if (saveList.contains(36)) {
                    lottoSave36.setImageResource(R.drawable.number36);
                } else {
                    lottoSave36.setImageResource(R.drawable.notselect36);
                }
            }
        });
        lottoSave37.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(37);
                if (saveList.contains(37)) {
                    lottoSave37.setImageResource(R.drawable.number37);
                } else {
                    lottoSave37.setImageResource(R.drawable.notselect37);
                }
            }
        });
        lottoSave38.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(38);
                if (saveList.contains(38)) {
                    lottoSave38.setImageResource(R.drawable.number38);
                } else {
                    lottoSave38.setImageResource(R.drawable.notselect38);
                }
            }
        });
        lottoSave39.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(39);
                if (saveList.contains(39)) {
                    lottoSave39.setImageResource(R.drawable.number39);
                } else {
                    lottoSave39.setImageResource(R.drawable.notselect39);
                }
            }
        });
        lottoSave40.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(40);
                if (saveList.contains(40)) {
                    lottoSave40.setImageResource(R.drawable.number40);
                } else {
                    lottoSave40.setImageResource(R.drawable.notselect40);
                }
            }
        });
        lottoSave41.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(41);
                if (saveList.contains(41)) {
                    lottoSave41.setImageResource(R.drawable.number41);
                } else {
                    lottoSave41.setImageResource(R.drawable.notselect41);
                }
            }
        });
        lottoSave42.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(42);
                if (saveList.contains(42)) {
                    lottoSave42.setImageResource(R.drawable.number42);
                } else {
                    lottoSave42.setImageResource(R.drawable.notselect42);
                }
            }
        });
        lottoSave43.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(43);
                if (saveList.contains(43)) {
                    lottoSave43.setImageResource(R.drawable.number43);
                } else {
                    lottoSave43.setImageResource(R.drawable.notselect43);
                }
            }
        });
        lottoSave44.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(44);
                if (saveList.contains(44)) {
                    lottoSave44.setImageResource(R.drawable.number44);
                } else {
                    lottoSave44.setImageResource(R.drawable.notselect44);
                }
            }
        });
        lottoSave45.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDataSetting(45);
                if (saveList.contains(45)) {
                    lottoSave45.setImageResource(R.drawable.number45);
                } else {
                    lottoSave45.setImageResource(R.drawable.notselect45);
                }
            }
        });
    }
    private void resetImage(){

        lottoSave1.setImageResource(R.drawable.notselect1);
        lottoSave2.setImageResource(R.drawable.notselect2);
        lottoSave3.setImageResource(R.drawable.notselect3);
        lottoSave4.setImageResource(R.drawable.notselect4);
        lottoSave5.setImageResource(R.drawable.notselect5);
        lottoSave6.setImageResource(R.drawable.notselect6);
        lottoSave7.setImageResource(R.drawable.notselect7);
        lottoSave8.setImageResource(R.drawable.notselect8);
        lottoSave9.setImageResource(R.drawable.notselect9);
        lottoSave10.setImageResource(R.drawable.notselect10);
        lottoSave11.setImageResource(R.drawable.notselect11);
        lottoSave12.setImageResource(R.drawable.notselect12);
        lottoSave13.setImageResource(R.drawable.notselect13);
        lottoSave14.setImageResource(R.drawable.notselect14);
        lottoSave15.setImageResource(R.drawable.notselect15);
        lottoSave16.setImageResource(R.drawable.notselect16);
        lottoSave17.setImageResource(R.drawable.notselect17);
        lottoSave18.setImageResource(R.drawable.notselect18);
        lottoSave19.setImageResource(R.drawable.notselect19);
        lottoSave20.setImageResource(R.drawable.notselect20);
        lottoSave21.setImageResource(R.drawable.notselect21);
        lottoSave22.setImageResource(R.drawable.notselect22);
        lottoSave23.setImageResource(R.drawable.notselect23);
        lottoSave24.setImageResource(R.drawable.notselect24);
        lottoSave25.setImageResource(R.drawable.notselect25);
        lottoSave26.setImageResource(R.drawable.notselect26);
        lottoSave27.setImageResource(R.drawable.notselect27);
        lottoSave28.setImageResource(R.drawable.notselect28);
        lottoSave29.setImageResource(R.drawable.notselect29);
        lottoSave30.setImageResource(R.drawable.notselect30);
        lottoSave31.setImageResource(R.drawable.notselect31);
        lottoSave32.setImageResource(R.drawable.notselect32);
        lottoSave33.setImageResource(R.drawable.notselect33);
        lottoSave34.setImageResource(R.drawable.notselect34);
        lottoSave35.setImageResource(R.drawable.notselect35);
        lottoSave36.setImageResource(R.drawable.notselect36);
        lottoSave37.setImageResource(R.drawable.notselect37);
        lottoSave38.setImageResource(R.drawable.notselect38);
        lottoSave39.setImageResource(R.drawable.notselect39);
        lottoSave40.setImageResource(R.drawable.notselect40);
        lottoSave41.setImageResource(R.drawable.notselect41);
        lottoSave42.setImageResource(R.drawable.notselect42);
        lottoSave43.setImageResource(R.drawable.notselect43);
        lottoSave44.setImageResource(R.drawable.notselect44);
        lottoSave45.setImageResource(R.drawable.notselect45);

    }
}
