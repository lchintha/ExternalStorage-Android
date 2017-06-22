package com.example.chint.externalstorage;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText uname;
    Button internal, external, eprivate, epublic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname = (EditText)findViewById(R.id.username);
        internal = (Button)findViewById(R.id.icache);
        external = (Button)findViewById(R.id.ecache);
        eprivate = (Button)findViewById(R.id.eprivate);
        epublic = (Button)findViewById(R.id.epublic);

    }
    public void onIcache(View v){
        String txt = uname.getText().toString();
        File folder = getCacheDir();
        File file = new File(folder,"Name1.txt");
        onWrite(file, txt);
        Toast.makeText(this, "Saved to ICACHE", Toast.LENGTH_LONG).show();
    }
    public void onEcache(View v){
        String txt = uname.getText().toString();
        File folder = getExternalCacheDir();
        File file = new File(folder,"Name2.txt");
        onWrite(file, txt);
        Toast.makeText(this, "Saved to ECACHE", Toast.LENGTH_LONG).show();
    }
    public void onEprivate(View v){
        String txt = uname.getText().toString();
        File folder = getExternalFilesDir("ExternalStorage");
        File file = new File(folder,"Name3.txt");
        onWrite(file, txt);
        Toast.makeText(this, "Saved to EPRIVATE", Toast.LENGTH_LONG).show();
    }
    public void onEpublic(View v){
        String txt = uname.getText().toString();
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder,"Name4.txt");
        onWrite(file, txt);
        Toast.makeText(this, "Saved to EPUBLIC", Toast.LENGTH_LONG).show();
    }
    public void onNext(View v){
        Intent i= new Intent(v.getContext(), SecondActivity.class);
        startActivity(i);
    }

    public static void onWrite(File myfile, String text){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myfile);
            fos.write(text.getBytes());
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
    }

}
