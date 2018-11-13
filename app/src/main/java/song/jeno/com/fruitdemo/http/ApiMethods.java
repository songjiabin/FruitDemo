package song.jeno.com.fruitdemo.http;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import song.jeno.com.fruitdemo.bean.QuotationItemBean;
import song.jeno.com.fruitdemo.bean.QuotationsBean;

/**
 * author : 宋佳
 * time   : 2018/11/02
 * desc   :
 * version: 1.0.0
 */

public class ApiMethods {


    public static void ApiSubscribe(Observable observable, Observer observer) {
        //上流
        observable.
                subscribeOn(Schedulers.io())//上流的线程
                .unsubscribeOn(Schedulers.io())//取消上流的线程
                .observeOn(AndroidSchedulers.mainThread())//下流的线程
                .subscribe(observer);
    }


    /**
     * 获取  微语录
     * url : http://www.wyl.cc/tag/xinlingjitang/page/1
     *
     * @param observer
     * @param page
     */
    public static void getQuotations(Observer<QuotationsBean> observer, String page) {
        ApiSubscribe(ApiStrategy.getApiService().getQuotationsBean(page), observer);
    }






    public static void getQuotationsItem(Observer<QuotationItemBean> observer, String url) {
        ApiSubscribe(ApiStrategy.getApiService().getQuotationsuItemBean(url), observer);
    }



}
