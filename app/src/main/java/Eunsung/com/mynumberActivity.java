package Eunsung.com;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class mynumberActivity extends AppCompatActivity {

    TextView textView; //결과를 띄어줄 TextView
    TextView reload; //reload버튼
    Elements contents;
    Document doc = null;
    private EditText et ;
    private Button  bpop, delbutton, lastList,  resetbtn;
    ArrayList WinNumber = new ArrayList();
    private InterstitialAd mInterstitialAd;
    RadioGroup radiogroup;
    String Top10;//결과를 저장할 문자열변수

    //main(String [] args)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mynumber);
        radiogroup = (RadioGroup) findViewById(R.id.RadioGroup01);
        bpop = (Button) findViewById(R.id.popbutton);
        delbutton = (Button) findViewById(R.id.delbutton);
        lastList = (Button) findViewById(R.id.lastList);
        resetbtn = (Button) findViewById(R.id.resetbtn);
        AdTogether();
        loadList();

        bpop.setOnClickListener(new View.OnClickListener() {
            @Override    // 입력한 데이터를 파일에 추가로 저장하기
            public void onClick(View v) {
                Intent intent = new Intent(mynumberActivity.this, numsaveActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 리셋 버튼
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override    // 리셋 버튼
            public void onClick(View v) {
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(mynumberActivity.this);
                alert_confirm.setMessage("리스트를 모두 삭제 하시겠습니까?").setCancelable(false).setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    FileOutputStream fos = openFileOutput
                                            ("mysaveList.txt", // 파일명 지정
                                                    Context.MODE_PRIVATE);// 저장모드
                                    PrintWriter out = new PrintWriter(fos);

                                    out.println("");
                                    out.close();

                                    loadList();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }).setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 'No'
                                return;
                            }
                        });
                AlertDialog alert = alert_confirm.create();
                alert.show();
            }
        });

        // 제거 버튼
        delbutton.setOnClickListener(new View.OnClickListener() {
            @Override    // 입력한 데이터를 파일에 추가로 저장하기
            public void onClick(View v) {

                int selectedBtn = radiogroup.getCheckedRadioButtonId();
                if (selectedBtn >0) {
                    String dummy = "";
                    try {
                        // 파일에서 읽은 데이터를 저장하기 위해서 만든 변수
                        StringBuffer data = new StringBuffer();
                        FileInputStream fis = openFileInput("mysaveList.txt");//파일명
                        BufferedReader buffer = new BufferedReader
                                (new InputStreamReader(fis));
                        String str = buffer.readLine(); // 파일에서 한줄을 읽어옴
                        int k=0;

                        RadioButton rd = (RadioButton) findViewById(radiogroup.getCheckedRadioButtonId());

                        while (str != null && str !="") {
                            if(str.contains(rd.getText())){
                                Log.d("TAG",  " 여기있는 리스트는 삭제 됩니다.");
                            }else{
                                dummy += (str + "\n" );
                            }
                            str = buffer.readLine().trim();
                        }
                        buffer.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        FileOutputStream fos = openFileOutput
                                ("mysaveList.txt", // 파일명 지정
                                        Context.MODE_PRIVATE);// 저장모드
                        PrintWriter out = new PrintWriter(fos);

                        out.println(dummy);
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    radiogroup.clearCheck();
                    loadList();
                }else{
                    AlertDialog.Builder alert = new AlertDialog.Builder(mynumberActivity.this);
                    alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();     //닫기
                        }
                    });
                    alert.setMessage("번호 선택 후 버튼을 클릭해주세요");
                    alert.show();

                }

            }

        });


        // 이력보기
        lastList.setOnClickListener(new View.OnClickListener() {
            @Override    // 입력한 데이터를 파일에 추가로 저장하기
            public void onClick(View v) {
                int selectedBtn = radiogroup.getCheckedRadioButtonId();
                if (selectedBtn >0) {
                    RadioButton rd = (RadioButton) findViewById(radiogroup.getCheckedRadioButtonId());
                    Intent intent = new Intent(mynumberActivity.this, lottodtlActivity.class);
                    intent.putExtra("Number1", rd.getText());
                    startActivity(intent);
                    radiogroup.clearCheck();
                }else{
                    AlertDialog.Builder alert = new AlertDialog.Builder(mynumberActivity.this);
                    alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();     //닫기
                        }
                    });
                    alert.setMessage("번호 선택 후 버튼을 클릭해주세요");
                    alert.show();
                }
            }
        });

        //Ad-Banner & Interstitial Ad

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
    private void loadList(){
        radiogroup.removeAllViews();

            // 파일의 내용을 읽어서 TextView 에 보여주기
            try {
                // 파일에서 읽은 데이터를 저장하기 위해서 만든 변수
                StringBuffer data = new StringBuffer();
                FileInputStream fis = openFileInput("mysaveList.txt");//파일명
                BufferedReader buffer = new BufferedReader
                (new InputStreamReader(fis));
                String str = ""; // 파일에서 한줄을 읽어옴

                for (int k=0; str != null ;k++){
                    str = buffer.readLine();
                    if (!str.equals(null) && !str.equals("")) {
                        RadioButton rdbtn = new RadioButton(this);
                        rdbtn.setId(k);

                        str = str.replaceAll("\\[", "");
                        str = str.replaceAll("\\]", "");

                        rdbtn.setText(str);
                        radiogroup.addView(rdbtn);
                    }
                }

                buffer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }


}
