package song.jeno.com.fruitdemo.api;


import io.reactivex.Observable;
import me.ghui.retrofit.converter.annotations.Html;
import retrofit2.http.GET;
import retrofit2.http.Url;
import song.jeno.com.fruitdemo.bean.QuotationItemBean;
import song.jeno.com.fruitdemo.bean.QuotationsBean;

/**
 * author : 宋佳
 * time   : 2018/11/12
 * desc   :
 * version: 1.0.0
 */

public interface QuotaionsApis {


    @GET
    @Html
    Observable<QuotationsBean> getQuotationsBean(@Url String url);



    @GET
    @Html
    Observable<QuotationItemBean> getQuotationsuItemBean(@Url String url);


}
