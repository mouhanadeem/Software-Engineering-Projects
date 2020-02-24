package com.project.punjabresturant.Adopter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.project.punjabresturant.Interface.ItemClickListener;
import com.project.punjabresturant.Model.Order;
import com.project.punjabresturant.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CardHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView txt_order_name,txt_price;
    ImageView img_order_item;
    ItemClickListener listener;

    public CardHolder (@NonNull View itemView) {
        super(itemView);
        txt_order_name=itemView.findViewById(R.id.txt_order_name);
        txt_price=itemView.findViewById(R.id.txtPrice);
        img_order_item=itemView.findViewById(R.id.img_order_item);
    }

    @Override
    public void onClick(View v) {

    }
}
public class OrderAdapter extends RecyclerView.Adapter<CardHolder>{
List<Order> list= new ArrayList<>();
Context context;

    public OrderAdapter(List<Order> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.order_list,viewGroup,false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardHolder cardHolder, int i) {
        TextDrawable drawable= TextDrawable.builder().buildRound(""+list.get(i).getQuantity(), Color.RED);
    cardHolder.img_order_item.setImageDrawable(drawable);

        Locale local = new Locale("danish","Denmark");
        NumberFormat format=NumberFormat.getCurrencyInstance(local);
        int price=((Integer.parseInt(list.get(i).getPrice())*Integer.parseInt( list.get(i).getQuantity())));
        cardHolder.txt_price.setText(format.format(price));
        cardHolder.txt_order_name.setText(list.get(i).getItemName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
