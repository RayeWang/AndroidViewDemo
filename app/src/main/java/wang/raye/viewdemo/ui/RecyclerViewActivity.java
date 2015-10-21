package wang.raye.viewdemo.ui;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import wang.raye.preioc.PreIOC;
import wang.raye.preioc.annotation.BindById;
import wang.raye.viewdemo.R;
import wang.raye.viewdemo.bean.DataModel;
import wang.raye.viewdemo.ui.adapter.RecyclerAdapter;
import wang.raye.viewlib.SwipeRefRecyclerView;

/**
 * Created by Raye on 2015/10/20.
 */
public class RecyclerViewActivity extends Activity {

    @BindById(R.id.recycler_view)
    SwipeRefRecyclerView recyclerView;
    private StaggeredGridLayoutManager manager;

    private RecyclerAdapter adapter;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ArrayList<DataModel> datas = new ArrayList<>();
            for(int i = 0;i< 20;i++){
                if(i % 2 == 0){
                    datas.add(new DataModel(R.mipmap.b));
                }else if(i % 3 == 0 ){
                    datas.add(new DataModel(R.drawable.aa));
                }else{
                    datas.add(new DataModel(R.mipmap.c));
                }
            }
            if(msg.what == 2) {
                adapter.addDatas(datas);
            }else{
                adapter.setDatas(datas);
            }
            recyclerView.loadingFinish();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        PreIOC.binder(this);
        //创建默认的线性LayoutManager
        manager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.getRecyclerView().setHasFixedSize(true);
        ArrayList<DataModel> datas = new ArrayList<>();
        for(int i = 0;i< 20;i++){
            if(i % 2 == 0){
                datas.add(new DataModel(R.mipmap.b));
            }else if(i % 3 == 0 ){
                datas.add(new DataModel(R.drawable.aa));
            }else{
                datas.add(new DataModel(R.mipmap.c));
            }
        }
        //创建并设置Adapter
        adapter = new RecyclerAdapter(datas);
        recyclerView.setAdapter(adapter);
        recyclerView.getRecyclerView().addItemDecoration(new RecyclerView.ItemDecoration() {


            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                if (parent.getChildAdapterPosition(view) >= 0) {
                    outRect.top = 15;
                    outRect.right = 7;
                    outRect.left = 7;
                }
            }
        });

        recyclerView.setOnRefreshListener(new SwipeRefRecyclerView.OnSwipeRefRecyclerViewListener() {
            @Override
            public void onRefresh() {

                handler.sendEmptyMessageDelayed(1,1000);
            }

            @Override
            public void onLoadMore() {

                handler.sendEmptyMessageDelayed(2,1000);
            }
        });

    }
}
