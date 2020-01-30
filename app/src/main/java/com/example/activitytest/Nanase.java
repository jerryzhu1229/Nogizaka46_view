package com.example.activitytest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Nanase extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        Button button2 = (Button) findViewById(R.id.button_2);
        final EditText editText = (EditText) findViewById(R.id.edit_text);
        final ImageView imageView = (ImageView) findViewById(R.id.image_view_1);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.button_2:

                        AlertDialog.Builder dialog = new AlertDialog.Builder(Nanase.this);
                        dialog.setTitle("This is Dialog");
                        dialog.setMessage("Something important");
                        dialog.setCancelable(false);
                        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String inputText =editText.getText().toString();
                                save(inputText);
                                imageView.setImageResource(R.drawable.nanase_2);
                                Toast.makeText(Nanase.this,load(),Toast.LENGTH_SHORT).show();
                            }

                            /**
                             * 文件存inputText信息到data文件中去
                             * @param inputText
                             */
                            private void save(String inputText){
                                FileOutputStream out = null;
                                BufferedWriter writer = null;
                                try{
                                    out = openFileOutput("data",Context.MODE_PRIVATE);
                                    writer = new BufferedWriter(new OutputStreamWriter(out));
                                    writer.write(inputText);
                                }catch (IOException e){
                                    e.printStackTrace();
                                }finally {
                                    try {
                                        if(writer !=null){
                                            writer.close();
                                        }
                                    }catch (IOException e){
                                        e.printStackTrace();
                                    }
                                }

                            }

                            /**
                             * 文件读信息冲data中
                             * @return
                             */
                            private String load(){
                                FileInputStream in =null;
                                BufferedReader reader = null;
                                StringBuilder content = new StringBuilder();
                                try {
                                    in = openFileInput("data");
                                    reader = new BufferedReader(new InputStreamReader(in));
                                    String line = "";
                                    while((line = reader.readLine()) !=null){
                                        content.append(line);
                                    }
                                }catch (IOException e){
                                    e.printStackTrace();
                                }finally {
                                    if(reader !=null){
                                        try {
                                            reader.close();
                                        }catch (IOException e){
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                return content.toString();
                            }
                        });
                        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        dialog.show();
                        break;
                        default:
                            break;

                }
            }
        });
    }
    //启动最佳方法 ， 可以传参
    public static void actionStart(Context context, String data1, String data2){
        Intent intent = new Intent(context, Nanase.class);
        intent.putExtra("param_1",data1);
        intent.putExtra("param_2",data2);
        context.startActivity(intent);
    };

}
