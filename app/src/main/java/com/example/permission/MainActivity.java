package com.example.permission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
 private  static  final int REQUEST_PERMISSION_CODE = 10;
 Button btn_requestPermission , btn_openSetting;
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
        btn_openSetting = findViewById(R.id.btn_open_settings_permission);
        btn_requestPermission = findViewById(R.id.btn_request_permission);
        btn_requestPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickRequestPermission();
            }
        });

    }
    private void ClickRequestPermission() {
       if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
           return ;
       }
       // version > 6
        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }else{
            String permissions = (Manifest.permission.ACCESS_FINE_LOCATION);
            requestPermissions(new String[]{permissions}, REQUEST_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
       if(requestCode == REQUEST_PERMISSION_CODE){
           if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
               Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
           }else{
               Toast.makeText(this, "Perrmission Denied", Toast.LENGTH_SHORT).show();
           }
       }
    }
}