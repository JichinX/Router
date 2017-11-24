package me.xujichang.router;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.xujichang.routerannotations.annotation.RouterActivity;
import me.xujichang.routerlib.rules.ActivityPatternRule;
import me.xujichang.routerlib.wapper.RouterWrapper;

@RouterActivity(flag = "main", remoteUrl = "")
public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_NUMBER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RouterWrapper.addPatternLines(ActivityPatternRule.SCHEME_ACTIVITY, TestA.class.getSimpleName(), TestA.class.getName());
    }

    public void start(View view) {

        RouterWrapper
                .createActivityBuilder(ActivityPatternRule.SCHEME_ACTIVITY)
                .withContext(this)
                .withStartType(ActivityPatternRule.START_NORMAL)
                .requestResult(12)
                .invoke(TestA.class.getSimpleName());
    }
}
