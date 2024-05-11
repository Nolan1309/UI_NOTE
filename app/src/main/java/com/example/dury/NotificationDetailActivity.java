package com.example.dury;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.dury.Model.FolderModel;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.ComponentActivity;


public class NotificationDetailActivity extends AppCompatActivity {

    Button btnback;
    TextView txtDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thongbao_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addController();
        addEvent();

        Bundle bundle = getIntent().getExtras();
        if(bundle==null){
            return ;
        }
        FolderModel folderModel= (FolderModel) bundle.get("object");
        txtDate = findViewById(R.id.txtDateTime);
        txtDate.setText(folderModel.getFolderName()+" "+folderModel.getYear());

    }

    void addController(){

        btnback = findViewById(R.id.btnBack);
    }
    void addEvent(){

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });

    }


}