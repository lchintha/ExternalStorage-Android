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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {
    EditText uname;
    Button internal, external, eprivate, epublic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        uname = (EditText)findViewById(R.id.username);
        internal = (Button)findViewById(R.id.icache);
        external = (Button)findViewById(R.id.ecache);
        eprivate = (Button)findViewById(R.id.eprivate);
        epublic = (Button)findViewById(R.id.epublic);
    }
    public void onIcache(View v){
        File folder = getCacheDir();
        File file = new File(folder,"Name1.txt");
        String txt = onRead(file);
        uname.setText(txt);
    }
    public void onEcache(View v){
        File folder = getExternalCacheDir();
        File file = new File(folder,"Name2.txt");
        String txt = onRead(file);
        uname.setText(txt);
    }
    public void onEprivate(View v){
        File folder = getExternalFilesDir("ExternalStorage");
        File file = new File(folder,"Name3.txt");
        String txt = onRead(file);
        uname.setText(txt);
    }
    public void onEpublic(View v){
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder,"Name4.txt");
        String txt = onRead(file);
        uname.setText(txt);
    }
    public void onPrevious(View v){
        Intent i= new Intent(v.getContext(), MainActivity.class);
        startActivity(i);
    }
    public String onRead(File myfile){
        FileInputStream fis = null;

        try {
            fis=new FileInputStream(myfile);
            int read = -1;
            StringBuffer sb = new StringBuffer();
            while((read = fis.read())!=-1){
                sb.append((char)read);
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
