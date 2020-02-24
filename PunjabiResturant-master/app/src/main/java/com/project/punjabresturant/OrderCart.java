package com.project.punjabresturant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.punjabresturant.Adopter.OrderAdapter;
import com.project.punjabresturant.Model.Order;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.project.punjabresturant.SQLiteData.Database;

public class OrderCart extends AppCompatActivity {
RecyclerView rw;
RecyclerView.LayoutManager lm;
FirebaseDatabase data;
DatabaseReference df;
TextView txtTotal;
Button btnSubmit;
OrderAdapter adapter;
List<Order> orders=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_cart);df=FirebaseDatabase.getInstance().getReference("Requests");
           txtTotal=findViewById(R.id.txtTotal);
           btnSubmit=findViewById(R.id.btnSubmit);
            rw=findViewById(R.id.list_order);
            rw.setHasFixedSize(true);
            lm= new LinearLayoutManager(this);
            rw.setLayoutManager(lm);

            loadOrderList();
    }

    private void loadOrderList() {
        orders= new Database(this).getOrder();
        adapter=new OrderAdapter(orders,this);
        rw.setAdapter(adapter);

        int total=0;
        for (Order order:orders)
            total+=Integer.parseInt(order.getPrice())*(Integer.parseInt(order.getQuantity()));
        Locale lc=new Locale("danish","Denmark");
        NumberFormat nf= NumberFormat.getCurrencyInstance(lc);
        txtTotal.setText(nf.format(total));

    }
}
