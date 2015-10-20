package wang.raye.viewdemo.ui;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.ArrayList;

import wang.raye.preioc.PreIOC;
import wang.raye.preioc.annotation.BindById;
import wang.raye.viewdemo.R;
import wang.raye.viewdemo.ui.adapter.RecyclerAdapter;

/**
 * Created by Raye on 2015/10/20.
 */
public class RecyclerViewActivity extends Activity {

    @BindById(R.id.recycler_view)
    RecyclerView recyclerView;
    private StaggeredGridLayoutManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        PreIOC.binder(this);
        //创建默认的线性LayoutManager
        manager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        ArrayList<Integer> datas = new ArrayList<>();
        for(int i = 0;i< 300;i++){
            if(i % 2 == 0){
                datas.add(R.mipmap.b);
            }else if(i % 3 == 0 ){
                datas.add(R.drawable.aa);
            }else{
                datas.add(R.mipmap.c);
            }
        }
        //创建并设置Adapter
        recyclerView.setAdapter(new RecyclerAdapter(datas));
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {


            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                if(parent.getChildAdapterPosition(view) >= 0){
                    outRect.top = 15;
                    outRect.right = 7;
                    outRect.left = 7;
                }
            }
        });
    }
}
