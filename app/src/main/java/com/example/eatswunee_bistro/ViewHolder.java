package com.example.eatswunee_bistro;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;

    ViewHolder(Context context, View itemView) {
        super(itemView);

        //textView = itemView.findViewById(R.id.te);

        //클릭 시 이벤트 추가
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION)
                {
                    //click event

                }
            }
        });
    }
}
