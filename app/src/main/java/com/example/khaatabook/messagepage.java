package com.example.khaatabook;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class messagepage extends AppCompatActivity {


    TextView customername,space;
    Button gave,got;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagepage);

        customername = findViewById(R.id.customername);
        gave = findViewById(R.id.yougave);
        got = findViewById(R.id.yougot);
        space = findViewById(R.id.space);


        Intent intent = getIntent();
//        customername.setText(intent.getStringExtra("customer"));
        String data = getIntent().getExtras().getString("customer");
        customername.setText(data);




            ObjectAnimator animation = ObjectAnimator.ofFloat(space, "translationY", 50f);
            animation.setDuration(2000);
            animation.start();





//            ObjectAnimator animation1 = ObjectAnimator.ofFloat(space, "translationY", 100f);
//
//            animation1.setDuration(2000);
//            animation1.start();



        gave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          Intent intent1=new Intent(messagepage.this,CalculateAmount.class);

                intent1.putExtra("customer", data);  // pass your values and retrieve them in the other Activity using AnyKeyName
                startActivity(intent1);







            }
        });







    }




}