package song.jeno.com.fruitdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import song.jeno.com.fruitdemo.adapter.QuotationsAdapter;
import song.jeno.com.fruitdemo.bean.QuotationsBean;
import song.jeno.com.fruitdemo.http.ApiMethods;
import song.jeno.com.fruitdemo.http.MyObserver;
import song.jeno.com.fruitdemo.http.ObserverOnNextListener;
import song.jeno.com.fruitdemo.utils.Constant;

public class MainActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private int page = 1;
    private QuotationsAdapter mAdapter;
    private List<QuotationsBean.QuotationsBeanItem> mList;
    private RefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        mList = new ArrayList<>();
        mAdapter = new QuotationsAdapter(this, mList);
        mAdapter.bindToRecyclerView(recyclerView);
        recyclerView.setAdapter(mAdapter);
        getData();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                getData();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page += 1;
                getData();
            }
        });


        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(MainActivity.this, QuotationItemActivity.class);
                intent.putExtra(Constant.STRING_PARAMETER, mList.get(position).getLink());
                startActivity(intent);
            }
        });



    }


    private void getData() {
        ApiMethods.getQuotations(new MyObserver<QuotationsBean>(MainActivity.this, new ObserverOnNextListener<QuotationsBean>() {
            @Override
            public void onNext(QuotationsBean quotationsBean) {
                List<QuotationsBean.QuotationsBeanItem> listData = quotationsBean.getQuotationsBeanItemList();
                if (page == 1) {
                    mList.clear();
                    mList.addAll(listData);
                    refreshLayout.finishRefresh(1000);
                } else if (page > 0) {
                    mList.addAll(listData);
                    refreshLayout.finishLoadMore(1000/*,false*/);
                }
                mAdapter.notifyDataSetChanged();
            }
        }), Constant.QUOTATIONS_URL + String.valueOf(page));
    }


}
