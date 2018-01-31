package jp.ne.goo.appratedialog;

import android.content.Context;

import timber.log.Timber;

/**
 * アプリ評価の誘導ダイアログのライブラリ
 *
 * @author katsuya
 * @since 1.0.o
 */
public class AppRateDialog {

    private static AppRateDialog singleton;

    private final Context context;

    /**
     * コンストラクタ
     *
     * @param context コンテキスト
     */
    private AppRateDialog(Context context) {
        this.context = context;
    }

    public static AppRateDialog with(Context context) {

        // 後で削除
        Timber.plant(new Timber.DebugTree());

        if (singleton == null) {
            synchronized (AppRateDialog.class) {
                if (singleton == null) {
                    singleton = new AppRateDialog(context);
                }
            }
        }

        return singleton;
    }

}
