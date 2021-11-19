package com.shunli.bookingmeeting.utils;


/**
 * Created by HanShunLi on 2020-07-16
 * Description：Android Toast的简单封装
 */

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.StringRes;

import com.shunli.bookingmeeting.App;


public class ToastUtil {
    private static Toast iToast;

    public static void showShortToast(CharSequence msg) {
        showOnUi(msg, Toast.LENGTH_SHORT);
    }

    public static void showLongToast(CharSequence msg) {
        showOnUi(msg, Toast.LENGTH_LONG);
    }


    public static void showShortToast(@StringRes final int resId) {
        showResId(resId, Toast.LENGTH_SHORT, (Object) null);
    }

    public static void showShortToast(@StringRes final int resId, final Object... args) {
        showResId(resId, Toast.LENGTH_SHORT, args);
    }


    private static void showResId(final int resId, final int duration, final Object... args) {
        try {
            CharSequence text = App.getApplication().getResources().getText(resId);
            if (args != null && args.length > 0) {
                text = String.format(text.toString(), args);
            }
            showOnUi(text, duration);
        } catch (Exception ignore) {
            showOnUi(String.valueOf(resId), duration);
        }
    }

    /**
     * 显示自定义 toast
     * TODO 这里之后再修改文字
     *
     * @param layout 资源 id
     */
    public static void showCustomToast(@LayoutRes int layout) {
        Context context = App.getApplication();
        Toast toast = new Toast(context);
        View view = View.inflate(context, layout, null);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        if (Looper.myLooper() == Looper.getMainLooper()) {
            toast.show();
        } else {
            new Handler(Looper.getMainLooper()).post(() -> toast.show());
        }
    }
//
//    public static void showCommonToast(String msg) {
//        showCommonToast(msg, Toast.LENGTH_SHORT);
//    }
//
//    public static void showCommonToast(String msg, int duration) {
//        Context context = CyclopsAndroidApplication.context;
//        Toast toast = new Toast(context);
//        View view = View.inflate(context, R.layout.layout_toast_common, null);
//        TextView tv = view.findViewById(R.id.tvMsg);
//        tv.setText(msg);
//        toast.setView(view);
//        toast.setGravity(Gravity.CENTER, 0, 0);
//        toast.setDuration(duration);
//        if (Looper.myLooper() == Looper.getMainLooper()) {
//            toast.show();
//        } else {
//            new Handler(Looper.getMainLooper()).post(() -> toast.show());
//        }
//    }


    private static void showOnUi(final CharSequence msg, final int duration) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            showToast(msg, duration);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    showToast(msg, duration);
                }
            });
        }
    }

    private static void showToast(CharSequence msg, int duration) {
        if (msg == null) {
            return;
        }

        cancel();
        iToast = Toast.makeText(App.getApplication(), msg, duration);
        iToast.setGravity(Gravity.CENTER, 0, 0);
        iToast.show();
//            if (sToast == null) {
//                sToast = new Toast(context);
//                LayoutInflater inflater = LayoutInflater.from(context);
//                View layout = inflater.inflate(R.layout.unil_toast, null);
//                sContentTv = (TextView) layout.findViewById(R.id.tv_toast_content);
//                sContentTv.setText(msg);
//
////                sToast.setGravity(Gravity.CENTER, 0, CyclopsAndroidApplication.context / 4);
//                sToast.setDuration(duration);
//                sToast.setView(layout);
//
//            }else {
//                sContentTv.setText(msg);
//            }
//
//            sToast.showOnUi();
    }


    private static void cancel() {
        if (iToast != null) {
            iToast.cancel();
        }
    }

}