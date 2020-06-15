package com.jang.textblackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txt1;
    TextView txt2;
    Button go;
    Button go2;
    Button stop;

    ArrayList<String> arImg1 = new ArrayList<String>();
    ArrayList<String> arNum1 = new ArrayList<String>();
    ArrayList<String> arImg2 = new ArrayList<String>();
    ArrayList<String> arNum2 = new ArrayList<String>();

    String[] img = {"스페이드","하트","다이아","클로버"};
    String[] num = {"1","2","3","4","5","6","7","8","9","10","J","Q","K"};

    //뽑은 횟수
    int drawNum1 = 0;
    int drawNum2 = 0;
    int temp1;
    int temp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt1 = (TextView)findViewById(R.id.txt1);
        txt2 = (TextView)findViewById(R.id.txt2);
        go = (Button)findViewById(R.id.go);
        go2 = (Button)findViewById(R.id.go2);
        stop = (Button)findViewById(R.id.stop);

        RotateAnimation animation = new RotateAnimation(3, 180);
        animation.setDuration(100);
        animation.setFillAfter(true);
        txt1.startAnimation(animation);

        firstDraw();

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //draw(1);
                draw(2);
            }
        });

        go2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                draw(1);
                //draw(2);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setText("");
                txt2.setText("");
                arImg1 = new ArrayList<String>();
                arNum1 = new ArrayList<String>();
                arImg2 = new ArrayList<String>();
                arNum2 = new ArrayList<String>();

                drawNum1 = 0;
                drawNum2 = 0;
                firstDraw();

            }
        });
    }

    public void draw(int z) {
        Random rnd = new Random();
        int ranImg = rnd.nextInt(4);
        int ranNum = rnd.nextInt(13);
        temp1 = 0;
        if (z == 1) {
            for (int i = 0; arNum1.size() > i; i++) {
                Log.i("testset", arNum1.get(i));
                if (arNum1.get(i) == "J" || arNum1.get(i) == "Q" || arNum1.get(i) == "K") {
                    temp1 = temp1 + 10;
                } else if (arNum1.get(i) != "1") {
                    temp1 = temp1 + Integer.parseInt(arNum1.get(i));
                } else if (arNum1.get(i) == "1") {
                    if (Integer.parseInt(arNum1.get(i)) < 7) {
                        temp1 = temp1 + 11;
                        //return;
                    } else {
                        temp1 = temp1 + 1;
                    }
                }
            }
        }

        if (z == 2) {
            for (int i = 0; arNum1.size() > i; i++) {
                Log.i("testset", arNum1.get(i));
                if (arNum1.get(i) == "J" || arNum1.get(i) == "Q" || arNum1.get(i) == "K") {
                    temp2 = temp2 + 10;
                } else if (arNum1.get(i) != "1") {
                    temp2 = temp2 + Integer.parseInt(arNum1.get(i));
                } else if (arNum1.get(i) == "1") {
                    if (Integer.parseInt(arNum1.get(i)) < 7) {
                        temp2 = temp2 + 11;
                    } else {
                        temp2 = temp2 + 1;
                    }
                }
            }
        }



        if (z == 1) {
            //if (temp1 < 18 ) {
                duplicateChk(Integer.toString(ranImg), Integer.toString(ranNum), z);
                drawNum1 = drawNum1 + 1;
                txt1.setText(txt1.getText() + ", " + arImg1.get(drawNum1) + ":" + arNum1.get(drawNum1));
            //}
        } else {
            //if (temp2 < 20 ) {
                duplicateChk(Integer.toString(ranImg), Integer.toString(ranNum), z);
                drawNum2 = drawNum2 + 1;
                txt2.setText(txt2.getText() + ", " + arImg2.get(drawNum2) + ":" + arNum2.get(drawNum2));
            //}
        }

    }

    public void firstDraw() {
        Random rnd = new Random();
        int ranImg = rnd.nextInt(4);
        int ranNum = rnd.nextInt(13);

        arImg1.add(img[ranImg]);
        arNum1.add(num[ranNum]);

        ranImg = rnd.nextInt(4);
        ranNum = rnd.nextInt(13);
        duplicateChk(Integer.toString(ranImg),Integer.toString(ranNum),1);


        ranImg = rnd.nextInt(4);
        ranNum = rnd.nextInt(13);

        arImg2.add(img[ranImg]);
        arNum2.add(num[ranNum]);

        ranImg = rnd.nextInt(4);
        ranNum = rnd.nextInt(13);
        duplicateChk(Integer.toString(ranImg),Integer.toString(ranNum),2);

        drawNum2 = 1;
        drawNum1 = 1;



        txt1.setText(arImg1.get(0) + ":" + arNum1.get(0) + ", " +arImg1.get(1) + ":" + arNum1.get(1));
        txt2.setText(arImg2.get(0) + ":" + arNum2.get(0) + ", " +arImg2.get(1) + ":" + arNum2.get(1));
    }

    public void duplicateChk(String x,String y,int z) {
        if(z == 1) {
            if (arImg1.size() > 0) {
                for (int i = 0;arNum1.size()>i; i++) {
                    if (arImg1.get(i) ==x && arNum1.get(i) == y) {
                        // 다시 드로우
                        draw(1);
                        return;
                    }
                }
            }
            if (arImg2.size() > 0) {
                for (int i = 0;arNum2.size()>i; i++) {
                    if (arImg2.get(i) ==x && arNum2.get(i) == y) {
                        // 다시 드로우
                        draw(1);
                        return;
                    }
                }
            }
            arImg1.add(img[Integer.parseInt(x)]);
            arNum1.add(num[Integer.parseInt(y)]);
        } else if (z == 2) {
            if (arImg1.size() > 0) {
                for (int i = 0;arNum1.size()>i; i++) {
                    if (arImg1.get(i) ==x && arNum1.get(i) == y) {
                        // 다시 드로우
                        draw(1);
                        return;
                    }
                }
            }
            if (arImg2.size() > 0) {
                for (int i = 0;arNum2.size()>i; i++) {
                    if (arImg2.get(i) == x && arNum2.get(i) == y) {
                        // 다시 드로우
                        draw(2);
                        return;
                    }
                }
            }
            arImg2.add(img[Integer.parseInt(x)]);
            arNum2.add(num[Integer.parseInt(y)]);
        }


    }
}
