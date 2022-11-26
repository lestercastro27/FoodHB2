package com.example.foodhb2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Button btnCall = (Button)findViewById(R.id.button5);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });
        Button btnCall1 = (Button)findViewById(R.id.button);
        btnCall1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity5();
            }
        });
    }

    public void openActivity5() {
        Intent i = new Intent(this, MainActivity5.class);
        startActivity(i);
    }

    public void openActivity3(){
        Intent i = new Intent(this, MainActivity3.class);
        startActivity(i);
    }
}