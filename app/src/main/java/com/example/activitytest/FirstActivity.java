package com.example.activitytest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends BaseActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
                break;
                default:
        }
        return true;
    }

    private List<Nogizaka46> nogizaka46List = new ArrayList<>();
    private IntentFilter intentFilter ;
    private NetworkChangeReceiver networkChangeReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.d(TAG,this.toString());
        setContentView(R.layout.first_layout);

        initNogizaka46();//初始化数据
        nogizaka46Adapter adapter = new nogizaka46Adapter(FirstActivity.this,R.layout.nogizaka46,nogizaka46List);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Nogizaka46 nogizaka46 = nogizaka46List.get(position);
                Toast.makeText(FirstActivity.this,nogizaka46.getName(),Toast.LENGTH_SHORT).show();

                switch(nogizaka46.getPositionId()){
                    case "Nanase":
                        Intent intent = new Intent(FirstActivity.this,Nanase.class);
                        startActivity(intent);
                        break;
                        default:
                            break;
                }

            }
        });

        /*Button button4 = (Button) findViewById(R.id.button_4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nanase.actionStart(FirstActivity.this,"data1","data2");
            }
        });*/
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(FirstActivity.this,android.R.layout.simple_list_item_1,data){};
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
*/
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter);
    }
    private void initNogizaka46(){
        Nogizaka46 Nanase = new Nogizaka46("西野七濑",R.drawable.nanase,"Nanase");
        nogizaka46List.add(Nanase);
        Nogizaka46 Asuka = new Nogizaka46("斋藤飞鸟",R.drawable.asuka,"Asuka");
        nogizaka46List.add(Asuka);
        Nogizaka46 Mai = new Nogizaka46("白石麻衣",R.drawable.mai,"Mai");
        nogizaka46List.add(Mai);
        Nogizaka46 Nanami = new Nogizaka46("桥本奈奈未",R.drawable.nanami,"Nanami");
        nogizaka46List.add(Nanami);
        Nogizaka46 Kazumi = new Nogizaka46("高山一实",R.drawable.kazumi,"Kazumi");
        nogizaka46List.add(Kazumi);
        Nogizaka46 Manatsu =  new Nogizaka46("秋元真夏",R.drawable.mamatus,"Manatsu");
        nogizaka46List.add(Manatsu);
        Nogizaka46 Erika =  new Nogizaka46("生田绘梨花",R.drawable.erika,"Erika");
        nogizaka46List.add(Erika);
        Nogizaka46 Sayuri =  new Nogizaka46("井上小百合",R.drawable.sayuri,"Sayuri");
        nogizaka46List.add(Sayuri);
        Nogizaka46 Kana =  new Nogizaka46("中田花奈",R.drawable.kana,"Kana");
        nogizaka46List.add(Kana);
        Nogizaka46 Rinai=  new Nogizaka46("樋口日奈",R.drawable.rinai,"Rinai");
        nogizaka46List.add(Rinai);
        Nogizaka46  Minami=  new Nogizaka46("星野南",R.drawable.minami,"Minami");
        nogizaka46List.add(Minami);
        Nogizaka46  Kotoko=  new Nogizaka46("佐佐木琴子",R.drawable.kotoko,"Kotoko");
        nogizaka46List.add(Kotoko);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }

    class NetworkChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo != null && networkInfo.isAvailable()){
                Toast.makeText(context,"network is available",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context,"network is unavailable",Toast.LENGTH_SHORT).show();
            }
        }
    }
}

