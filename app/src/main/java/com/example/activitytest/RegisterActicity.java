package com.example.activitytest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class RegisterActicity extends BaseActivity {
    private TextView tv_main_title;//标题
    private TextView tv_back;//返回按钮
    private Button btn_register;//注册按钮
    //用户名，密码，再次输入的密码的控件
    private EditText et_user_name,et_psw,et_psw_again;
    //用户名，密码，再次输入的密码的值
    private String userName,psw,pswAgain;
    //标题布局
    private RelativeLayout r1_title_bar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册页面界面
        setContentView(R.layout.activity_register);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    @SuppressLint("WrongViewCast")
    private void init() {
        tv_main_title = findViewById(R.id.tv_main_title);
        tv_main_title.setText("注册");
        tv_back=findViewById(R.id.tv_back);

        r1_title_bar = findViewById(R.id.title_bar);
        r1_title_bar.setBackgroundColor(Color.TRANSPARENT);

        btn_register = findViewById(R.id.btn_register);

        et_user_name = findViewById(R.id.et_user_name);
        et_psw = findViewById(R.id.et_psw);
        et_psw_again = findViewById(R.id.et_psw_again);

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回键
                RegisterActicity.this.finish();
            }
        });
        //注册按钮
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入的字符串
                getEditString();
                //判断输入框内容  <Android开发中，我们经常使用TextUtils.isEmpty()来判断字符串是否为null或者空字符串>
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(RegisterActicity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
                    return ;
                }else if (TextUtils.isEmpty(psw)){
                    Toast.makeText(RegisterActicity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                    return ;
                }else if(TextUtils.isEmpty(pswAgain)){
                    Toast.makeText(RegisterActicity.this,"请再次输入密码",Toast.LENGTH_SHORT).show();
                    return ;
                    /**
                     * 从sharedpreferences中读取输入的用户名，判断sharepreferences中是否又次用户名
                     */
                }else if(isExistUserName(userName)){
                    Toast.makeText(RegisterActicity.this,"此用户名已存在",Toast.LENGTH_SHORT).show();
                    return ;
                }else{
                    Toast.makeText(RegisterActicity.this,"注册成功",Toast.LENGTH_SHORT).show();

                    saveRegisterInfo(userName,psw);

                    /*//注册成功后把账号传递到LoginActivity.java中
                    // 返回值到loginActivity显示
                    Intent data = new Intent();
                    data.putExtra("userName", userName);
                    setResult(RESULT_OK, data);
                    //RESULT_OK为Activity系统常量，状态码为-1，
                    // 表示此页面下的内容操作成功将data返回到上一页面，如果是用back返回过去的则不存在用setResult传递data值*/
                    RegisterActicity.this.finish();

                }
            }



        });
    }
    /**
     * 获取控件中的字符串
     * ToString()是转化为字符串的方法 Trim()是去两边空格的方法
     */
    private void getEditString(){
        userName = et_user_name.getText().toString().trim();
        psw = et_psw.getText().toString().trim();
        pswAgain = et_psw_again.getText().toString().trim();
    }
    /**
     *从sharedpreferences中读取输入的用户名，判断sharepreferences中是否又次用户名
     * Android 中的 SharedPreference 是轻量级的数据存储方式，能够保存简单的数据类型，比如 String、int、boolean 值等
     * loginInfo是文件名称 存储用户的账号和密码
     * Context.MODE_PRIVATE：为默认操作模式,代表该文件是私有数据,只能被应用本身访问,在该模式下,写入的内容会覆盖原文件的内容
     */
    private  boolean isExistUserName(String userName){
        boolean has_userName=false;

        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        String spPsw = sp.getString(userName,"");//传入用户名获取密码
        if(!TextUtils.isEmpty(spPsw)){
            has_userName=true;
        }
        return has_userName;
    }
    /**
     * 保存账号和密码到SharedPreferences中SharedPreferences
     * getSharedPreferences(name,mode)方法的第一个参数用于指定该文件的名称，名称不用带后缀，后缀会由Android自动加上。
     * 方法的第二个参数指定文件的操作模式，共有四种操作模式 后三个被弃用
     * Context.MODE_PRIVATE = 0  为默认操作模式，代表该文件是私有数据，只能被应用本身访问，在该模式下，写入的内容会覆盖原文件的内容，如果想把新写入的内容追加到原文件中。可以使用Context.MODE_APPEND
     * Context.MODE_APPEND = 32768
     * Context.MODE_WORLD_READABLE = 1
     * Context.MODE_WORLD_WRITEABLE = 2
     */
    private  void saveRegisterInfo(String userName,String psw){
        String md5Psw = MD5Utils.md5(psw);//MD5加密

        SharedPreferences.Editor editor = getSharedPreferences("loginInfo",MODE_PRIVATE).edit();

        editor.putString(userName,md5Psw);
        editor.apply();
    }
}
