package song.jeno.com.fruitdemo.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import song.jeno.com.fruitdemo.R;
import song.jeno.com.fruitdemo.bean.QuotationsBean;

/**
 * author : 宋佳
 * time   : 2018/11/12
 * desc   :
 * version: 1.0.0
 */

public class QuotationsAdapter extends BaseQuickAdapter<QuotationsBean.QuotationsBeanItem, BaseViewHolder> {

    private Context context;

    public QuotationsAdapter(Context context, @Nullable List<QuotationsBean.QuotationsBeanItem> data) {
        super(R.layout.item_quotation, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, QuotationsBean.QuotationsBeanItem item) {

        helper.setText(R.id.content, item.getContent());
        helper.setText(R.id.share_card_top_content, item.getTitle());
        helper.setText(R.id.share_card_top_author, item.getTime());
        helper.setText(R.id.share_card_top_title, item.getTag());
        ImageView share_card_top_image = (ImageView) helper.getView(R.id.share_card_top_image);
        Glide.with(context).load(item.getImg()).into(share_card_top_image);


    }
}
