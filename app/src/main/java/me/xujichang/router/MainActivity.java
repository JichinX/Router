package me.xujichang.router;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import junit.framework.Test;

import me.xujichang.routerlib.rules.ActivityPatternRule;
import me.xujichang.routerlib.wapper.RouterWrapper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RouterWrapper.addPatternLines(ActivityPatternRule.SCHEME_ACTIVITY, TestA.class.getSimpleName(), TestA.class.getName());
    }

    public void start(View view) {
        Intent intent = new Intent(this, TestA.class);
        startActivity(intent);
        RouterWrapper
                .createBuilder(ActivityPatternRule.SCHEME_ACTIVITY)
                .withContext(this)
                .withFlag()
                .invoke();
    }
}
