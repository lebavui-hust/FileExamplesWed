package com.example.fileexamples;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private final String PREF_NAME = "mydata";
    private final String PARAM1 = "param1";
    private final String PARAM2 = "param2";
    private final String PARAM3 = "param3";

    private final String PREF_USERNAME = "username";
    private final String PREF_PASSWORD = "password";

    EditText editContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Save data
//        SharedPreferences prefs = getSharedPreferences("mydata", 0);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putString("param1", "value1");
//        editor.putInt("param2", 2);
//        editor.putFloat("param3", 3f);
//        editor.apply();


        // Load data
//        SharedPreferences prefs = getSharedPreferences(PREF_NAME, 0);
//        String param1 = prefs.getString(PARAM1, "");
//        int param2 = prefs.getInt(PARAM2, 0);
//        float param3 = prefs.getFloat(PARAM3, 0f);
//        String param4 = prefs.getString("param4", "NO VALUE");
//        Log.v("TAG", param1 + "-" + param2 + "-" + param3 + "-" + param4);

//        TextView textView = findViewById(R.id.text_view);
//
//        try {
//            InputStream is = getResources().openRawResource(R.raw.test);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//            String data = "";
//            String line = "";
//            while ((line = reader.readLine()) != null) {
//                data += line + "\n";
//            }
//            reader.close();
//            is.close();
//
//            textView.setText(data);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }

        editContent = findViewById(R.id.edit_content);

        findViewById(R.id.btn_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // File tu bo nho trong
                    // String filePath = getFilesDir() + "/mydata.txt";

                    // File tu bo nho ngoai
                    String filePath = Environment.getExternalStorageDirectory() + "/mydata.txt";
                    File file = new File(filePath);
                    if (file.exists()) {
                        // File tu bo nho trong
                        // InputStream is = openFileInput("mydata.txt");

                        // File tu bo nho ngoai
                        InputStream is = new FileInputStream(file);

                        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                        String data = "";
                        String line = "";
                        while ((line = reader.readLine()) != null) {
                            data = data + line + "\n";
                        }
                        reader.close();
                        is.close();

                        editContent.setText(data);
                    } else
                        Toast.makeText(MainActivity.this, "File khong ton tai", Toast.LENGTH_LONG).show();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // File tu bo nho trong
//                    OutputStream os = openFileOutput("mydata.txt", 0);

                    // File tu bo nho ngoai
                    String filePath = Environment.getExternalStorageDirectory() + "/mydata.txt";
                    File file = new File(filePath);
                    OutputStream os = new FileOutputStream(file);

                    OutputStreamWriter writer = new OutputStreamWriter(os);
                    writer.write(editContent.getText().toString());
                    writer.flush();
                    writer.close();
                    os.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // File tu bo nho trong
//                String filePath = getFilesDir() + "/mydata.txt";

                // File tu bo nho ngoai
                String filePath = Environment.getExternalStorageDirectory() + "/mydata.txt";

                File file = new File(filePath);

                if (file.exists())
                    file.delete();
            }
        });

        Log.v("TAG", Environment.getExternalStorageDirectory().getAbsolutePath());

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(MainActivity.this, "Permission denied", Toast.LENGTH_LONG).show();
                requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 123);
            } else
                Toast.makeText(MainActivity.this, "Permission granted", Toast.LENGTH_LONG).show();
        }

        File[] files = Environment.getExternalStorageDirectory().listFiles();
        for (int i = 0; i < files.length; i++)
            Log.v("TAG", files[i].getAbsolutePath());

        // Copy file
//        try {
//            InputStream is; // Khoi tao input stream
//            OutputStream os; // Khoi tao output stream
//
//            byte[] buffer = new byte[1024];
//            int len;
//
//            while ((len = is.read(buffer)) > 0) {
//                os.write(buffer, 0, len);
//            }
//
//            os.close();
//            is.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123)
            if (grantResults[0] == PackageManager.PERMISSION_DENIED)
                Toast.makeText(MainActivity.this, "Permission denied", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "Permission granted", Toast.LENGTH_LONG).show();
    }
}