package jp.ne.goo.example.appratedialog;

import android.app.Application;

import jp.ne.goo.appratedialog.AppRateDialog;

/**
 * アプリケーションクラス
 *
 * @author katsuya
 * @since 1.0.0
 */
public class MainApplication extends Application {

    /**
     * アプリケーションの最初に呼ばれる
     *
     * @since 1.0.0
     */
    @Override
    public void onCreate() {
        super.onCreate();

        // 初期設定（Applicationクラス内で設定）
        AppRateDialog.with(this)
                .setShowDialogCount(10)
                .setRemindDialogInterval(5)
                .setOpinionUri("https://help.goo.ne.jp/cc/app/m/152800/")
                .setReviewUri("market://details?id=jp.ne.goo.app.news");
    }

}
