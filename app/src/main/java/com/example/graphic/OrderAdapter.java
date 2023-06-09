package com.example.graphic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private ArrayList<Order> orders;

    public OrderAdapter(ArrayList<Order> orders){
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View orderView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new OrderViewHolder(orderView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order currentOrder = orders.get(position);
        holder.tvProductname.setText(currentOrder.getProductName());
        holder.tvAmount.setText(currentOrder.getAmount()+"");
        holder.tvPrice.setText(currentOrder.getPrice()+"");
        holder.tvText.setText(currentOrder.getText());
        holder.tvClientName.setText(currentOrder.getClientName());
        holder.tvClientPhone.setText(currentOrder.getClientPhone());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder{

        public TextView tvProductname;
        public TextView tvAmount;
        public TextView tvPrice;
        public TextView tvText;
        public TextView tvClientName;
        public TextView tvClientPhone;


        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            tvProductname = itemView.findViewById(R.id.tvProductName);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvPrice = itemView.findViewById(R.id.tvRecyclerPrice);
            tvText = itemView.findViewById(R.id.tvText);
            tvClientName = itemView.findViewById(R.id.tvClientName);
            tvClientPhone = itemView.findViewById(R.id.tvPhone);

        }
    }

}
