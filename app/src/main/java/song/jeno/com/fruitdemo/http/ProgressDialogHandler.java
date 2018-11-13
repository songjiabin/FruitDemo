package song.jeno.com.fruitdemo.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import song.jeno.com.fruitdemo.R;

/**
 * author : 宋佳
 * time   : 2018/11/02
 * desc   :
 * version: 1.0.0
 */

public class ProgressDialogHandler extends Handler {

    /**
     * 显示
     */
    public static final int SHOW_PROGRESS_DIALOG = 1;
    /**
     * 隐藏
     */
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private ProgressDialog pd;


    private Context context;
    private boolean cancelable;
    private ProgressCancelListener mProgressCancelListener;

    public ProgressDialogHandler(Context context, ProgressCancelListener
            mProgressCancelListener, boolean cancelable) {
        this.context = context;
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
    }


    private void initProgressDialog() {
        if (pd == null) {
            pd = new ProgressDialog(context);
            pd.setIcon(R.mipmap.card_default_bg);
            pd.setCancelable(cancelable);
        }
        if (cancelable) {
            pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    mProgressCancelListener.onCancelProgress();
                }
            });
        }

        if (!pd.isShowing()) {
            pd.show();
        }
    }

    private void dismissProgressDialog() {
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }

    }
}
