package com.example.eatswunee_bistro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private ArrayList<String> mData = null;

    //아이템 뷰를 저장하는 뷰 홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;

        ViewHolder(View itemView) {
            super(itemView);

            //뷰 객체에 대한 참조
            textView1 = itemView.findViewById(R.id.order);
            textView2 = itemView.findViewById(R.id.order_num);
            textView3 = itemView.findViewById(R.id.date);
            textView4 = itemView.findViewById(R.id.menu_name);
            textView5 = itemView.findViewById(R.id.menu_num);

            //추가내용
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION)
                    {
                        mListener.onItemClick(view, pos);
                    }
                }
            });
        }
    }

    //생성자에게 데이터 리스트 객체를 전달받음
    CustomAdapter(ArrayList<String> list) {
        mData = list;
    }

    //onCreateViewHolder() - 아이템 뷰를 위한 뷰 홀더 객체 생성하여 리턴
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup  parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);
        CustomAdapter.ViewHolder vh = new CustomAdapter.ViewHolder(view);

        return vh;
    }

    //onBindViewHolder - position에 해당하는 데이터를 뷰 홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(CustomAdapter.ViewHolder holder, int position) {
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
    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    //ViewHolder 화면에 표시될 아이템뷰 저장
}

