package com.example.eatswunee_bistro;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.example.eatswunee_bistro.api.Notification;

import java.util.ArrayList;
import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolder> {
    private List<Notification> items;
    private ArrayList<String> list;

    public AlarmAdapter(List<Notification> items) {this.items = items;}

    //아이템 뷰를 저장하는 뷰 홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView order_num, order_title_menu, order_etc_menu_cnt;
        AlarmAdapter.ServiceItemClickListener serviceItemClickListener;

        ViewHolder(View itemView) {
            super(itemView);

            //뷰 객체에 대한 참조
            order_num = itemView.findViewById(R.id.bistro_num);
            order_title_menu = itemView.findViewById(R.id.menu_name);
            order_etc_menu_cnt = itemView.findViewById(R.id.menu_num);

            //itemView.setOnClickListener((View.OnClickListener) this);
        }
        //api 통신
        public void setItem(Notification item) {
            order_num.setText(item.getOrderNum());
            order_title_menu.setText(item.getOrderTitleMenu());
            order_etc_menu_cnt.setText(item.getOrderEtcMenuCnt());
        }
    }

    //onCreateViewHolder() - 아이템 뷰를 위한 뷰 홀더 객체 생성하여 리턴
    @Override
    public AlarmAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.recyclerview_alarm, parent, false);
        AlarmAdapter.ViewHolder vh = new AlarmAdapter.ViewHolder(view);

        return vh;
    }

    //onBindViewHolder - position에 해당하는 데이터를 뷰 홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(AlarmAdapter.ViewHolder holder, int position) {
        Notification item = items.get(position);
        holder.setItem(item);

       holder.serviceItemClickListener = new ServiceItemClickListener() {
           @Override
           public void onItemClickListener(View v, int position) {
               Intent intent = new Intent(v.getContext(), BistroAlarmActivity.class);
               v.getContext().startActivity(intent);
           }
       };
    }

    //getItemCount 전체 데이터 개수 리턴
    @Override
    public int getItemCount() {
        return items.size();
    }

    //커스텀 리스너 인터페이스
    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    //serviceitemClickListener 인터페이스
    public interface ServiceItemClickListener{
        void onItemClickListener(View v, int position);
    }

    //리스너 객체 참조를 저장하는 변수
    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


    //ViewHolder에서 클릭 시 동작 호출

}


