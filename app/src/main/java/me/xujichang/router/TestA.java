package me.xujichang.router;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import me.xujichang.routerannotations.annotation.RouterActivity;

/**
 * Des:
 *
 * @author xjc
 *         Created on 2017/11/22 14:20.
 */
@RouterActivity(flag = "testA", remoteUrl = "")
public class TestA extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
    }
}
