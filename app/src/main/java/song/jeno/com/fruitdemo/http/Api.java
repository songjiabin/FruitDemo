package song.jeno.com.fruitdemo.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import song.jeno.com.fruitdemo.utils.Constant;

/**
 * author : 宋佳
 * time   : 2018/11/02
 * desc   :
 * version: 1.0.0
 */

public class Api {


    private static ApiService apiService = null;

    public static ApiService getApiService() {
        if (apiService == null) {
            synchronized (Api.class) {
                if (apiService == null) {
                    new Api();
                }
            }
        }
        return apiService;
    }


    private Api() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//将请求的结果转为实体
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //适配RxJava2.0, RxJava1.x 则为RxJavaCallAdapterFactory.create()
                .build();
        apiService = retrofit.create(ApiService.class);
    }

}
