package wang.raye.viewdemo.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import wang.raye.preioc.annotation.OnClick;
import wang.raye.viewdemo.R;
import wang.raye.viewdemo.bean.DataModel;

/**
 * Created by Administrator on 2015/10/20.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements
        View.OnClickListener{

    private ArrayList<DataModel> datas;
    public RecyclerAdapter(ArrayList<DataModel> datas){
        this.datas = datas;
    }
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_img,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder viewHolder, int i) {
        viewHolder.img.setImageResource(datas.get(i).getResId());
        viewHolder.img.setOnClickListener(this);
        viewHolder.img.setTag(datas.get(i));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onClick(View v) {
        removeItem((DataModel) v.getTag());
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        public ViewHolder(View view){
            super(view);
            img = (ImageView) view.findViewById(R.id.img);
        }
    }

    public void removeItem(DataModel obejct) {
        int position = datas.indexOf(obejct);
        Log.i("Raye","this position:"+position);
        datas.remove(obejct);
        notifyItemRemoved(position);//Attention!
    }

    public void setDatas(ArrayList<DataModel> datas){
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void addDatas(ArrayList<DataModel> datas){
        int count = getItemCount();
        this.datas.addAll(datas);
        for(int i = count;i < getItemCount();i++){
            notifyItemInserted(i);
        }

    }
}
