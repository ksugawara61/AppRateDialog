package jp.ne.goo.appratedialog;

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
     * ダイアログの表示フラグ
     */
    private static final String PREF_IS_ALLOWED = PREFIX + "is_allowed";




}
