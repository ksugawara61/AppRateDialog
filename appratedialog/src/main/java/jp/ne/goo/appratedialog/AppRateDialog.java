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
 * @since 1.0.0
 */
public class AppRateDialog {

    private static AppRateDialog singleton;

    private final Context context;

    /**
     * 満足ヒアリングダイアログ用テキスト
     */
    private static String satisfactionTitle = null;
    private static String satisfactionMessage = null;
    private static String satisfactionPositiveText = null;
    private static String satisfactionNegativeText = null;

    /**
     * 意見ヒアリングダイアログ用テキスト
     */
    private static String opinionTitle = null;
    private static String opinionMessage = null;
    private static String opinionText = null;
    private static String opinionUri = null;

    /**
     * レーティングダイアログ用テキスト
     */
    private static String reviewTitle = null;
    private static String reviewMessage = null;
    private static String reviewText = null;
    private static String reviewUri = null;

    /**
     * 共通テキスト
     */
    private static String laterText = null;
    private static String forbiddenText = null;

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

        // テキストの初期化
        satisfactionTitle = context.getString(R.string.satisfaction_title);
        satisfactionMessage = context.getString(R.string.satisfaction_message);
        satisfactionPositiveText = context.getString(R.string.satisfaction_positive_text);
        satisfactionNegativeText = context.getString(R.string.satisfaction_negative_text);

        opinionTitle = context.getString(R.string.opinion_title);
        opinionMessage = context.getString(R.string.opinion_message);
        opinionText = context.getString(R.string.opinion_text);

        reviewTitle = context.getString(R.string.review_title);
        reviewMessage = context.getString(R.string.review_message);
        reviewText = context.getString(R.string.review_text);

        laterText = context.getString(R.string.later_text);
        forbiddenText = context.getString(R.string.forbidden_text);
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
     * アプリ起動回数の追加
     *
     * @param context コンテキスト
     * @since 1.0.0
     */
    public static void addLaunchCount(Context context) {
        int launchCount = PreferenceHelper.getLaunchCount(context);
        PreferenceHelper.setLaunchCount(context, launchCount + 1);
    }

    /**
     * 「満足していますか？」ダイアログを表示
     *
     * @param activity アクティビティ
     * @since 1.0.0
     */
    public static void showSatisfactionDialog(final Activity activity) {

        if (isShowDialog(activity)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AppAlertDialogStyle);
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

            builder.create().show();
        }
    }

    /**
     * アプリの要望を促すダイアログを表示
     *
     * @param activity アクティビティ
     * @since 1.0.0
     */
    public static void showOpinionDialog(final Activity activity) {

        if (isShowDialog(activity)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AppAlertDialogStyle);
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

    }

    /**
     * 「レビューを書く」ダイアログを表示
     *
     * @param activity アクティビティ
     * @since 1.0.0
     */
    public static void showRateDialog(final Activity activity) {

        if (isShowDialog(activity)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AppAlertDialogStyle);
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
     * ダイアログを表示するかの判定フラグ
     *
     * @param context コンテキスト
     * @return 判定結果
     * @since 1.0.0
     */
    private static boolean isShowDialog(Context context) {

        int launchCount = PreferenceHelper.getLaunchCount(context);
        int remindDialogInterval = PreferenceHelper.getRemindDialogInterval(context);
        int showDialogCount = PreferenceHelper.getShowDialogCount(context);

        Timber.d("hogehoge1: " + launchCount);
        Timber.d("hogehoge2: " + remindDialogInterval);
        Timber.d("hogehoge2: " + showDialogCount);

        if (PreferenceHelper.getIsAllowed(context)) {
            int intervalCount = (launchCount - showDialogCount) % remindDialogInterval;
            if (PreferenceHelper.getIsLater(context) && (intervalCount == 0)) {
                // 後でフラグ true でかつ起動回数が表示間隔と一致する場合
                return true;
            } else if (launchCount >= showDialogCount) {
                // 起動回数が起動回数の閾値以上の場合
                return true;
            } else {
                return false;
            }
        } else {
            // 表示フラグが falseの時（ストアへ誘導済み or 表示しないを選択）
            // ダイアログを表示しない
            return false;
        }

    }

    /**
     * ブラウザを起動する
     *
     * @param activity アクティビティ
     * @param uriText URI
     * @since 1.0.0
     */
    private static void startBrowse(Activity activity, String uriText) {
        if (uriText == null) return;
        Uri uri = Uri.parse(uriText);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Timber.e(e.getMessage());
        }
    }

}
