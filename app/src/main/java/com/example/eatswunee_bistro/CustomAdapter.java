package com.example.eatswunee_bistro;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.eatswunee_bistro.api.menus;
import com.example.eatswunee_bistro.api.orders;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private List<orders> ordersList;

    public CustomAdapter(List<orders> ordersList) {this.ordersList = ordersList;}



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
        //여기서 다른 텍스트 뷰도 값 수정하면 됨..
        orders item = ordersList.get(position);
        holder.setItem(item);

        holder.serviceItemClickListener = new ServiceItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                Intent intent = new Intent(v.getContext(), BistroOrderActivity.class);
                v.getContext().startActivity(intent);
            }
        };

    }

    //getItemCount 전체 데이터 개수 리턴
    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    //아이템 뷰를 저장하는 뷰 홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder{
        //api 통신
        private TextView order_num, menu_name, menu_cnt;
        ServiceItemClickListener serviceItemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);

            //뷰 객체에 대한 참조
            //api통신
            order_num = itemView.findViewById(R.id.order_num);
            menu_name = itemView.findViewById(R.id.menu_name);
            menu_cnt = itemView.findViewById(R.id.menu_cnt);

            itemView.setOnClickListener((View.OnClickListener) this);

            //추가내용
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        mListener.onItemClick(view, pos);
                    }
                }
            });
        }
        //api 통신
        public void setItem(orders item){
            order_num.setText(item.getOrderNum());
            menu_name.setText(item.getMenuName());
            menu_cnt.setText(item.getMenuCnt());
        }
    }

    //생성자에게 데이터 리스트 객체를 전달받음
    //리스트 말고도 또 들어가야하는 리스트?? 메뉴이름??같은 것들도 같이~~

    //data 모델의 객체들을 list에 저장
    public void setorderList(ArrayList<orders> list) {
        this.ordersList = list;
        notifyDataSetChanged();
    }

    //커스텀 리스너 인터페이스
    public interface OnItemClickListener
    {
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

        public void setItem(orders item) {
        }
    }


    //ViewHolder에서 클릭 시 동작 호출

