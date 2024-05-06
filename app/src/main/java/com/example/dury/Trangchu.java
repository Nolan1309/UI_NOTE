package com.example.dury;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Trangchu extends AppCompatActivity {

    Button btnGhiChu,btnThongBao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trangchu);


        addcontroll();
        event();
    }
    void addcontroll(){
        btnGhiChu = findViewById(R.id.buttonTaoGhiChu);
        btnThongBao = findViewById(R.id.buttonTaoThongBao);
    }
    void event(){
        btnGhiChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Trangchu.this, NoteActivity.class);
                startActivity(intent);
            }
        });
        btnThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Trangchu.this, NotificationActivity.class);
                startActivity(intent);
            }
        });
    }
}