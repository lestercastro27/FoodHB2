package com.example.foodhb2;

import android.content.SharedPreferences;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity6 extends AppCompatActivity {
    DatabaseReference dbRef;
    TextView textName, textID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        dbRef = FirebaseDatabase.getInstance("https://foodhb2-2d893-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("FoodHB2");
        textName = findViewById(R.id.textName);
        textID = findViewById(R.id.textID);

        Button btnCall = (Button) findViewById(R.id.button7);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }

    public void InsRec(View v) { //insert record
        String ID = textID.getText().toString();
        String name = textName.getText().toString();
        Student student = new Student(name);
        dbRef.child(ID).setValue(name).addOnSuccessListener(suc -> {
            Toast.makeText(this,"record inserted..", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(err -> {
            Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    public void showData(View v) { //display record
        String ID = textID.getText().toString();
        DatabaseReference dbDoc = dbRef.child(ID);
        ValueEventListener veListener = new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {
                Student student = (ds.getValue(Student.class));
                textName.setText(student.getName());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        dbDoc.addValueEventListener(veListener);
    }

    public void UpRec (View v) {//update record
        String ID = textID.getText().toString();
        String name = textName.getText().toString();
        HashMap<String, Object> studMap = new HashMap<>();
        studMap.put("name", name);
        dbRef.child(ID).updateChildren(studMap).addOnSuccessListener(suc -> {
            Toast.makeText(this,"record updated..", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(err -> {
            Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show();
        });
}

    public void DelRec (View v) { //delete record
        String ID = textID.getText().toString();
        dbRef.child(ID).removeValue().addOnSuccessListener(suc -> {
            Toast.makeText(this,"record deleted..", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(err -> {
            Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }



    public void saveIS(View v) { //working internal storage
        String name = textName.getText().toString();
        String ID = textID.getText().toString();
        String data = name + " " + ID;

        FileOutputStream fos = null;

        try {
            fos = openFileOutput("student.txt", Context.MODE_PRIVATE);
            fos.write(data.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "saved the data...", Toast.LENGTH_SHORT).show();
    }              //internal

    public void saveIC(View v){ //working internal cache
        String name = textName.getText().toString();
        String ID = textID.getText().toString();
        String data = name + " " + ID;

        FileOutputStream fos = null;

        try {
            File folder = getCacheDir();
            File file = new File(folder, "student3.txt");
            fos = new FileOutputStream(file);
            fos.write(data.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "saved the data...", Toast.LENGTH_SHORT).show();
    }
    public void saveES(View v) { //working external storage
        String name = textName.getText().toString();
        String ID = textID.getText().toString();
        String data = name + " " + ID;

        FileOutputStream fos = null;

        try {
            File folder = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(folder, "student2.txt");
            fos = new FileOutputStream(file);
            fos.write(data.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "saved the data...", Toast.LENGTH_SHORT).show();
    }

    public void saveEC(View v){ //working external cache
        String name = textName.getText().toString();
        String ID = textID.getText().toString();
        String data = name + " " + ID;

        FileOutputStream fos = null;

        try {
            File folder = getExternalCacheDir();
            File file = new File(folder, "student4.txt");
            fos = new FileOutputStream(file);
            fos.write(data.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "saved the data...", Toast.LENGTH_SHORT).show();
    }

    public void clearEntries(View v){
        textID.setText("");
        textName.setText("");
    }

    public void openActivity2() {
        Intent i = new Intent(this, MainActivity2.class);
        startActivity(i);
    }
}
