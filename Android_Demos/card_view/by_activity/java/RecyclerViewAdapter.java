package com.example.a61555.cardviewdemo;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 * Created by 61555 on 2017/7/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CardViewDataHolder>{

    private List<CardViewData> datas;
    private Context context;

    public RecyclerViewAdapter(List<CardViewData> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerViewAdapter.CardViewDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_item, parent, false);
        CardViewDataHolder holder = new CardViewDataHolder(view);
        return holder;
    }

    /**
     * 绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(CardViewDataHolder holder, int position) {
        holder.title.setText(datas.get(position).getTitle());
        holder.subhead.setText(datas.get(position).getSubhead());
        holder.imageView.setBackgroundResource(datas.get(position).getBgColor());
        holder.supText.setText(datas.get(position).getSupText());
    }

    /**
     *  Item 总数
     * @return
     */
    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * 自定义 Holder 类
     */
    static public class CardViewDataHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView title;
        TextView subhead;
        ImageView imageView;
        TextView supText;
        Button action1Btn;
        Button action2Btn;

        public CardViewDataHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            title = (TextView) itemView.findViewById(R.id.title_text);
            subhead = (TextView) itemView.findViewById(R.id.subhead_text);
            imageView = (ImageView) itemView.findViewById(R.id.img);
            supText = (TextView) itemView.findViewById(R.id.sup_text);
            action1Btn = (Button) itemView.findViewById(R.id.btn1);
            action2Btn = (Button) itemView.findViewById(R.id.btn2);
        }
    }
}
