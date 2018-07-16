package com.tiangong.plugin.nosdklib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.qihoo360.replugin.RePlugin;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean startActivity = RePlugin.startActivity(getApplicationContext(),
                        RePlugin.createIntent("com.tiangong.android.plugin.demo",
                                "com.tiangong.android.plugin.demo.MainActivity"));
                if (startActivity) {
//                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "进入插件失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
