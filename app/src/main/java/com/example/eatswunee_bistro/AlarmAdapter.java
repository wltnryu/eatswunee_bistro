package com.example.eatswunee_bistro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolder>{
    private ArrayList<String> mData = null;

    //아이템 뷰를 저장하는 뷰 홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView4;
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        ImageView imageView3;

        ViewHolder(View itemView) {
            super(itemView);

            //뷰 객체에 대한 참조
            imageView4 = itemView.findViewById(R.id.imageView4);
            textView1 = itemView.findViewById(R.id.order);
            textView2 = itemView.findViewById(R.id.order_num);
            textView3 = itemView.findViewById(R.id.date);
            textView4 = itemView.findViewById(R.id.menu_name);
            textView5 = itemView.findViewById(R.id.menu_num);
            imageView3 = imageView3.findViewById(R.id.imageView3);

        }
        public TextView getTextView1() {
            return textView1;
        }
    }

    //생성자에게 데이터 리스트 객체를 전달받음
    public AlarmAdapter(ArrayList<String> list) {
        mData = list;
    }

    //onCreateViewHolder() - 아이템 뷰를 위한 뷰 홀더 객체 생성하여 리턴
    @Override
    public AlarmAdapter.ViewHolder onCreateViewHolder(ViewGroup  parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_alarm, parent, false);
        AlarmAdapter.ViewHolder vh = new AlarmAdapter.ViewHolder(view);

        return vh;
    }

    //onBindViewHolder - position에 해당하는 데이터를 뷰 홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String text = mData.get(position);
        holder.textView1.setText(text);
    }

    //getItemCount 전체 데이터 개수 리턴
    @Override
    public int getItemCount() {
        return mData.size();
    }

    //커스텀 리스너 인터페이스
    public interface OnItemClickListener
    {
        void onItemClick(View v, int pos);
    }

    //리스너 객체 참조를 저장하는 변수
    private CustomAdapter.OnItemClickListener mListener = null;

    public void setOnItemClickListener(CustomAdapter.OnItemClickListener listener) {
        this.mListener = listener;
    }


    //ViewHolder에서 클릭 시 동작 호출
}
