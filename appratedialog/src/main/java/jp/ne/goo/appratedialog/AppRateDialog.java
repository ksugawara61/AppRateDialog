package jp.ne.goo.appratedialog;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;

import timber.log.Timber;

/**
 * アプリのレビュー誘導用ダイアログライブラリ
 *
 * @author katsuya
 * @since 1.0.o
 */
public class AppRateDialog {

    private static AppRateDialog singleton;

    private final Context context;

    /**
     * 満足ヒアリングダイアログ
     */
    private static String satisfactionTitle = "アプリに満足していますか？";
    private static String satisfactionMessage = "いつもアプリをご利用いただきありがとうございます。アプリの感想をお聞かせください。";

    /**
     * 意見ヒアリングダイアログ
     */
    private static String opinionTitle = "アプリについて";
    private static String opinionMessage = "ご意見・ご要望をお聞かせください";
    private static String opinionText = "要望を書く";
    private static String opinionUri = "https://help.goo.ne.jp/cc/app/m/152800/";

    /**
     * レーティングダイアログ
     */
    private static String reviewTitle = "アプリのレビュー";
    private static String reviewMessage = "アプリをレビューしませんか？";
    private static String reviewText = "レビューする";
    private static String reviewUri = "market://details?id=jp.ne.goo.app.news";

    private static String yes = "はい";
    private static String no  = "いいえ";
    private static String laterText = "後で";
    private static String forbiddenText = "表示しない";

    /**
     * 今後表示しないの表示フラグ
     */
    private static boolean hasForbiddenText = true;

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

    /**
     * 「満足していますか？」ダイアログを表示
     *
     * @param activity アクティビティ
     * @since 1.0.0
     */
    public static void showDialog(final Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(satisfactionTitle)
                .setMessage(satisfactionMessage)
                .setPositiveButton(yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showRateDialog(activity);
                    }
                })
                .setNegativeButton(no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showOpinionDialog(activity);
                    }
                });

        if (hasForbiddenText) {
            builder.setNeutralButton(forbiddenText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // TODO 今後表示しない処理を実装
                }
            });
        }

        builder.create().show();

    }

    /**
     * アプリの要望を促すダイアログを表示
     *
     * @param activity アクティビティ
     * @since 1.0.0
     */
    public static void showOpinionDialog(final Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(opinionTitle)
                .setMessage(opinionMessage)
                .setPositiveButton(opinionText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startBrowse(activity, opinionUri);
                    }
                })
                .setNegativeButton(laterText, null);

        if (hasForbiddenText) {
            builder.setNeutralButton(forbiddenText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // TODO 今後表示しない処理を実装
                }
            });
        }

        builder.create().show();

    }

    /**
     * 「レビューを書く」ダイアログを表示
     *
     * @param activity アクティビティ
     * @since 1.0.0
     */
    public static void showRateDialog(final Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(reviewTitle)
                .setMessage(reviewMessage)
                .setPositiveButton(reviewText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startBrowse(activity, reviewUri);
                    }
                })
                .setNegativeButton(laterText, null);

        if (hasForbiddenText) {
            builder.setNeutralButton(forbiddenText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // TODO 今後表示しない処理を実装
                }
            });
        }

        builder.create().show();

    }

    /**
     * ブラウザを起動する
     *
     * @param activity アクティビティ
     * @param uriText URI
     */
    private static void startBrowse(Activity activity, String uriText) {
        Uri uri = Uri.parse(uriText);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Timber.e(e.getMessage());
        }
    }

}
