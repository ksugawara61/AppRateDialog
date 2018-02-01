package jp.ne.goo.example.appratedialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import jp.ne.goo.appratedialog.AppRateDialog;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppRateDialog.with(this)
                .setOpinionUri("https://help.goo.ne.jp/cc/app/m/152800/")
                .setReviewUri("market://details?id=jp.ne.goo.app.news");
        AppRateDialog.showSatisfactionDialog(this);
//        AppRateDialog.showRateDialog(this);
    }
}
