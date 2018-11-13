package song.jeno.com.fruitdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import song.jeno.com.fruitdemo.bean.QuotationItemBean;
import song.jeno.com.fruitdemo.http.ApiMethods;
import song.jeno.com.fruitdemo.http.MyObserver;
import song.jeno.com.fruitdemo.http.ObserverOnNextListener;
import song.jeno.com.fruitdemo.utils.Constant;

/**
 * author : 宋佳
 * time   : 2018/11/13
 * desc   :
 * version: 1.0.0
 */

public class QuotationItemActivity extends AppCompatActivity {

    public ImageView share_card_top_image;

    public TextView content;

    public TextView share_card_top_content;
    private TextView share_card_top_author;
    private TextView share_card_top_title;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_quotation);
        initView();
        String url = getIntent().getStringExtra(Constant.STRING_PARAMETER);
        ApiMethods.getQuotationsItem(new MyObserver<QuotationItemBean>(this, new ObserverOnNextListener<QuotationItemBean>() {
            @Override
            public void onNext(QuotationItemBean item) {
                content.setText(item.getContent());
                share_card_top_content.setText(item.getTitle());
                Glide.with(QuotationItemActivity.this).load(item.getImg()).into(share_card_top_image);
                share_card_top_author.setText(item.getTime());
                share_card_top_title.setText(item.getCategory());

            }
        }), url);
    }


    private void initView() {
        content= (TextView)findViewById(R.id.content);
        share_card_top_image=(ImageView)findViewById(R.id.share_card_top_image);
        share_card_top_content=(TextView)findViewById(R.id.share_card_top_content);
        share_card_top_author=(TextView)findViewById(R.id.share_card_top_author);
        share_card_top_title=(TextView)findViewById(R.id.share_card_top_title);
    }

}
