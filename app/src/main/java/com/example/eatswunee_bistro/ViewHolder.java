package com.example.eatswunee_bistro;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView order;
    public TextView order_num;
    public TextView menu_name;
    public TextView menu_cnt;
    public TextView date;

    ViewHolder(View itemView) {
        super(itemView);

        order = itemView.findViewById(R.id.order);
        order_num = itemView.findViewById(R.id.order_num);
        menu_name = itemView.findViewById(R.id.menu_name);
        menu_cnt = itemView.findViewById(R.id.menu_cnt);
        date = itemView.findViewById(R.id.date);

//        //클릭 시 이벤트 추가
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int pos = getAdapterPosition();
//                if (pos != RecyclerView.NO_POSITION)
//                {
//                    //click event
//
//                }
//            }
//        });
    }
}
