package jp.ne.goo.example.appratedialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import jp.ne.goo.appratedialog.AppRateDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppRateDialog.addLaunchCount(this);
        AppRateDialog.showSatisfactionDialog(this);
    }
}
