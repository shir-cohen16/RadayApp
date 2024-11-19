package com.example.radayapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SharedPreferences sharedPreferences = getSharedPreferences(name:"TestApp",MODE_PRIVATE);
        TextView tv = findViewById(R.id.textView);
        tv.setText(sharedPreferences.getString("userinput","no input yet"));
        Button button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et = findViewById(R.id.yourName);
                string name = et.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("are you sure")
                                .setMessage("just doing an example")
                                        .setPositiveButton("ok",null)
                                                .setNegativeButton("i regret", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Toast.makeText(MainActivity.this, "not lisening", Toast)
                                                    }
                                                })

                //put data into SharedPreferences
                editor.putString("userinput",name);
                editor.apply();
            }
        });
    }
}