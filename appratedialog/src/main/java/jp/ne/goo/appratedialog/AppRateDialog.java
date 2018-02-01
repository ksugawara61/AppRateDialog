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
     * 満足ヒアリングダイアログ用テキスト
     */
    private static String satisfactionTitle = "アプリに満足していますか？";
    private static String satisfactionMessage = "いつもアプリをご利用いただきありがとうございます。アプリの感想をお聞かせください。";
    private static String satisfactionPositiveText = "はい";
    private static String satisfactionNegativeText = "いいえ";

    /**
     * 意見ヒアリングダイアログ用テキスト
     */
    private static String opinionTitle = "アプリについて";
    private static String opinionMessage = "ご意見・ご要望をお聞かせください";
    private static String opinionText = "要望を書く";
    private static String opinionUri = "https://help.goo.ne.jp/cc/app/m/152800/";

    /**
     * レーティングダイアログ用テキスト
     */
    private static String reviewTitle = "アプリのレビュー";
    private static String reviewMessage = "アプリをレビューしませんか？";
    private static String reviewText = "レビューする";
    private static String reviewUri = "market://details?id=jp.ne.goo.app.news";

    /**
     * 共通テキスト
     */
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
     * @since 1.0.0
     */
    private AppRateDialog(Context context) {
        this.context = context;
    }

    public static synchronized AppRateDialog with(Context context) {

        // 後で削除
        Timber.plant(new Timber.DebugTree());

        if (singleton == null) {
            singleton = new AppRateDialog(context);
        }

        return singleton;
    }

    /**
     * 「満足していますか？」ダイアログを表示
     *
     * @param activity アクティビティ
     * @since 1.0.0
     */
    public static void showSatisfactionDialog(final Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(satisfactionTitle)
                .setMessage(satisfactionMessage)
                .setPositiveButton(satisfactionPositiveText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showRateDialog(activity);
                    }
                })
                .setNegativeButton(satisfactionNegativeText, new DialogInterface.OnClickListener() {
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
     * 「満足していますか？」ダイアログタイトルのセッター
     *
     * @param title タイトル
     * @return this
     * @since 1.0.0
     */
    public AppRateDialog setSatisfactionTitle(String title) {
        this.satisfactionTitle = title;
        return this;
    }

    /**
     * 「満足していますか？」ダイアログメッセージのセッター
     *
     * @param message メッセージ
     * @return this
     * @since 1.0.0
     */
    public AppRateDialog setSatisfactionMessage(String message) {
        this.satisfactionMessage = message;
        return this;
    }

    /**
     * 「満足していますか？」ダイアログPositiveButtonテキストのセッター
     *
     * @param text テキスト
     * @return this
     * @since 1.0.0
     */
    public AppRateDialog setSatisfactionPositiveText(String text) {
        this.satisfactionPositiveText = text;
        return this;
    }

    /**
     * 「満足していますか？」ダイアログNegativeButtonテキストのセッター
     *
     * @param text テキスト
     * @return this
     * @since 1.0.0
     */
    public AppRateDialog setSatisfactionNegativeText(String text) {
        this.satisfactionNegativeText = text;
        return this;
    }

    /**
     * 意見ヒアリングダイアログタイトルのセッター
     *
     * @param title タイトル
     * @return this
     * @since 1.0.0
     */
    public AppRateDialog setOpinionTitle(String title) {
        this.opinionTitle = title;
        return this;
    }

    /**
     * 意見ヒアリングダイアログメッセージのセッター
     *
     * @param message メッセージ
     * @return this
     * @since 1.0.0
     */
    public AppRateDialog setOpinionMessage(String message) {
        this.opinionMessage = message;
        return this;
    }

    /**
     * 意見ヒアリングダイアログPositiveButtonテキストのセッター
     *
     * @param text PositiveButtonテキスト
     * @return this
     * @since 1.0.0
     */
    public AppRateDialog setOpinionText(String text) {
        this.opinionText = text;
        return this;
    }

    /**
     * 意見ヒアリングダイアログ遷移先URIのセッター
     *
     * @param uri 遷移先URI
     * @return this
     * @since 1.0.0
     */
    public AppRateDialog setOpinionUri(String uri) {
        this.opinionUri = uri;
        return this;
    }

    /**
     * レビューダイアログタイトルのセッター
     *
     * @param title タイトル
     * @return this
     * @since 1.0.0
     */
    public AppRateDialog setReviewTitle(String title) {
        this.reviewTitle = title;
        return this;
    }

    /**
     * レビューダイアログメッセージのセッター
     *
     * @param message メッセージ
     * @return this
     * @since 1.0.0
     */
    public AppRateDialog setReviewMessage(String message) {
        this.reviewMessage = message;
        return this;
    }

    /**
     * レビューダイアログPositiveButtonテキストのセッター
     *
     * @param text PositiveButtonテキスト
     * @return this
     * @since 1.0.0
     */
    public AppRateDialog setReviewText(String text) {
        this.reviewText = text;
        return this;
    }

    /**
     * レビューダイアログ遷移先URIのセッター
     *
     * @param uri 遷移先URI
     * @return this
     * @since 1.0.0
     */
    public AppRateDialog setReviewUri(String uri) {
        this.reviewUri = uri;
        return this;
    }

    /**
     * 「後で」テキストのセッター
     *
     * @param text テキスト
     * @return this
     * @since 1.0.0
     */
    public AppRateDialog setLaterText(String text) {
        this.laterText = text;
        return this;
    }

    /**
     * 「表示しない」テキストのセッター
     *
     * @param text テキスト
     * @return this
     * @since 1.0.0
     */
    public AppRateDialog setForbiddenText(String text) {
        this.forbiddenText = text;
        return this;
    }

    /**
     * 「表示しない」ボタンの表示フラグ
     *
     * @param hasForbiddenText 表示フラグ
     * @return this
     * @since 1.0.0
     */
    public AppRateDialog setHasForbiddenText(boolean hasForbiddenText) {
        this.hasForbiddenText = hasForbiddenText;
        return this;
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
