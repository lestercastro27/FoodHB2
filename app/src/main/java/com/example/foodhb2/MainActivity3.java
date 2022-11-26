package com.example.foodhb2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Button btnCall = (Button)findViewById(R.id.button);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity4();
            }
        });
        Button btnCall1 = (Button)findViewById(R.id.button5);
        btnCall1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }

    public void openActivity2() {
        Intent i = new Intent(this, MainActivity2.class);
        startActivity(i);
    }

    public void openActivity4(){
        Intent i = new Intent(this, MainActivity4.class);
        startActivity(i);
    }

}