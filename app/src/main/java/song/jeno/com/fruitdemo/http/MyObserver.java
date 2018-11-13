package song.jeno.com.fruitdemo.http;

import android.content.Context;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * author : 宋佳
 * time   : 2018/11/02
 * desc   :
 * version: 1.0.0
 */

public class MyObserver<T> implements Observer<T>, ProgressCancelListener {

    private static String TAG = ">>>>";

    private Context context;
    private ObserverOnNextListener listener;
    private ProgressDialogHandler mProgressDialogHandler;
    private Disposable d;

    public MyObserver(Context context, ObserverOnNextListener listener) {
        this.context = context;
        this.listener = listener;
        this.mProgressDialogHandler = new ProgressDialogHandler(context, this, false);

    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG)
                    .sendToTarget();
            mProgressDialogHandler = null;
        }
    }


    @Override
    public void onSubscribe(Disposable d) {
        //当连接的时候
        Log.d(TAG, "onSubscribe: ");
        this.d = d;
        showProgressDialog();
    }

    @Override
    public void onNext(T t) {
        listener.onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: ", e);
        //添加业务处理
        dismissProgressDialog();
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete: ");
        //添加业务处理
        dismissProgressDialog();
    }

    @Override
    public void onCancelProgress() {
        //当关闭loading的时候
        //如果处于订阅状态，则取消订阅
        // 如果没有断开 就断开
        if (!d.isDisposed()) {
            d.dispose();
        }
    }
}
