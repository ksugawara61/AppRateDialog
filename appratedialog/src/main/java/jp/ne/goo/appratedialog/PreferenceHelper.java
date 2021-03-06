package jp.ne.goo.appratedialog;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * アプリのレビュー誘導用ダイアログのデータ管理用
 *
 * @author katsuya
 * @since 1.0.0
 */
public class PreferenceHelper {

    /**
     * プレフィックス
     */
    private static final String PREFIX = "app_rate_dialog_";

    /**
     * SharedPreferencesのファイル名
     */
    private static final String PREF_FILE_NAME = PREFIX + "pref_file_name";

    /**
     * アプリの起動回数（ライブラリの起動回数）
     */
    private static final String PREF_LAUNCH_COUNT = PREFIX + "pref_launch_count";

    /**
     * ダイアログを表示するタイミング
     */
    private static final String PREF_SHOW_DIALOG_COUNT = PREFIX + "pref_show_dialog_count";

    /**
     * 「後で」クリック後にダイアログを表示する間隔
     */
    private static final String PREF_REMIND_DIALOG_INTERVAL = PREFIX + "pref_remind_dialog_interval";

    /**
     * 後でクリックフラグ
     */
    private static final String PREF_IS_LATER = PREFIX + "is_later";

    /**
     * ダイアログの表示フラグ
     */
    private static final String PREF_IS_ALLOWED = PREFIX + "is_allowed";

    /**
     * コンストラクタ
     *
     * @since 1.0.0
     */
    private PreferenceHelper() {}

    /**
     * 起動回数のゲッター
     *
     * @param context コンテキスト
     * @return 起動回数
     * @since 1.0.0
     */
    public static int getLaunchCount(Context context) {
        return getPreferences(context).getInt(PREF_LAUNCH_COUNT, 0);
    }

    /**
     * ダイアログを表示する起動回数のゲッター
     *
     * @param context コンテキスト
     * @return ダイアログを表示する起動回数
     * @since 1.0.0
     */
    public static int getShowDialogCount(Context context) {
        return getPreferences(context).getInt(PREF_SHOW_DIALOG_COUNT, 1);
    }

    /**
     * 「後で」クリック後にダイアログを表示する間隔のゲッター
     *
     * @param context コンテキスト
     * @return 表示間隔
     * @since 1.0.0
     */
    public static int getRemindDialogInterval(Context context) {
        return getPreferences(context).getInt(PREF_REMIND_DIALOG_INTERVAL, 1);
    }

    /**
     * 「後で」クリックフラグのゲッター
     *
     * @param context コンテキスト
     * @return 「後で」クリックフラグ
     * @since 1.0.0
     */
    public static boolean getIsLater(Context context) {
        return getPreferences(context).getBoolean(PREF_IS_LATER, false);
    }

    /**
     * ダイアログの表示フラグのゲッター
     *
     * @param context コンテキスト
     * @return ダイアログの表示フラグ
     * @since 1.0.0
     */
    public static boolean getIsAllowed(Context context) {
        return getPreferences(context).getBoolean(PREF_IS_ALLOWED, true);
    }

    /**
     * 起動回数のセッター
     *
     * @param context コンテキスト
     * @param launchCount 起動回数
     * @since 1.0.0
     */
    public static void setLaunchCount(Context context, int launchCount) {
        Editor editor = getPreferencesEditor(context);
        editor.putInt(PREF_LAUNCH_COUNT, launchCount);
        editor.apply();
    }

    /**
     * ダイアログを表示する起動回数のセッター
     *
     * @param context コンテキスト
     * @param showDialogCount ダイアログを表示する起動回数
     * @since 1.0.0
     */
    public static void setShowDialogCount(Context context, int showDialogCount) {
        if (showDialogCount <= 0) {
            // 0未満の数値の場合 1にする
            showDialogCount = 1;
        }

        Editor editor = getPreferencesEditor(context);
        editor.putInt(PREF_SHOW_DIALOG_COUNT, showDialogCount);
        editor.apply();
    }

    /**
     * 「後で」クリック後にダイアログを表示する間隔のセッター
     *
     * @param context コンテキスト
     * @param remindDialogInterval 表示間隔
     * @since 1.0.0
     */
    public static void setRemindDialogInterval(Context context, int remindDialogInterval) {
        if (remindDialogInterval <= 0) {
            // 0未満の数値の場合 1にする
            remindDialogInterval = 1;
        }

        Editor editor = getPreferencesEditor(context);
        editor.putInt(PREF_REMIND_DIALOG_INTERVAL, remindDialogInterval);
        editor.apply();
    }

    /**
     * 「後で」クリックフラグのセッター
     *
     * @param context コンテキスト
     * @param isLater 「後で」クリックフラグ
     * @since 1.0.0
     */
    public static void setIsLater(Context context, boolean isLater) {
        Editor editor = getPreferencesEditor(context);
        editor.putBoolean(PREF_IS_LATER, isLater);
        editor.apply();
    }

    /**
     * ダイアログの表示フラグのセッター
     *
     * @param context コンテキスト
     * @param isAllowed ダイアログの表示フラグ
     * @since 1.0.0
     */
    public static void setIsAllowed(Context context, boolean isAllowed) {
        Editor editor = getPreferencesEditor(context);
        editor.putBoolean(PREF_IS_ALLOWED, isAllowed);
        editor.apply();
    }

    /**
     * SharedPreferences のゲッター
     *
     * @param context コンテキスト
     * @return SharedPreferences
     */
    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * PreferencesEditor のゲッター
     *
     * @param context コンテキスト
     * @return PreferencesEditor
     */
    private static Editor getPreferencesEditor(Context context) {
        return getPreferences(context).edit();
    }

}
