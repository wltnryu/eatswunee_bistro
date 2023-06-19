package com.example.eatswunee_bistro;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatswunee_bistro.api.menus;
import com.example.eatswunee_bistro.api.orders;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class BistroDoneAdapter extends RecyclerView.Adapter<BistroDoneAdapter.ViewHolder> {
    private List<orders> ordersList;

    public BistroDoneAdapter(List<orders> items) {this.ordersList = items;}

    //아이템 뷰를 저장하는 뷰 홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView order_num, menu_name, menu_cnt;
        CustomAdapter.ServiceItemClickListener serviceItemClickListener;

        ViewHolder(View itemView) {
            super(itemView);

            //뷰 객체에 대한 참조
            order_num = itemView.findViewById(R.id.order_num);
            menu_name = itemView.findViewById(R.id.menu_name);
            menu_cnt = itemView.findViewById(R.id.menu_cnt);

            //itemView.setOnClickListener((View.OnClickListener) this);
        }
        //api 통신
        public void setItem(orders item) {
            order_num.setText(item.getOrderNum());
            menu_name.setText(item.getmenusList().get(0).getMenuName());
            //0+1
            int total_cnt = 0;
            for( int i = 0; i<item.getmenusList().size();i++){
                total_cnt += Integer.parseInt(item.getmenusList().get(i).getMenuCnt());
            }
            menu_cnt.setText(Integer.toString(total_cnt));
        }
    }

        //onCreateViewHolder() - 아이템 뷰를 위한 뷰 홀더 객체 생성하여 리턴
        @Override
        public BistroDoneAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.recyclerview_item, parent, false);
            BistroDoneAdapter.ViewHolder vh = new BistroDoneAdapter.ViewHolder(view);

            return vh;
        }

        //onBindViewHolder - position에 해당하는 데이터를 뷰 홀더의 아이템뷰에 표시
        @Override
        public void onBindViewHolder(BistroDoneAdapter.ViewHolder holder, int position) {
            orders item = ordersList.get(position);
            holder.setItem(item);

            holder.serviceItemClickListener = new CustomAdapter.ServiceItemClickListener() {
                @Override
                public void onItemClickListener(View v, int position) {
                    Intent intent = new Intent(v.getContext(), BistroDoneActivity.class);
                    v.getContext().startActivity(intent);
                }
            };
        }

        //getItemCount 전체 데이터 개수 리턴
        @Override
        public int getItemCount() {

                return ordersList.size();


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


