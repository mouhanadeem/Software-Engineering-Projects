package com.project.punjabresturant;

import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.punjabresturant.Model.Items;
import com.project.punjabresturant.Model.Order;
import com.project.punjabresturant.SQLiteData.Database;
import com.squareup.picasso.Picasso;

public class Food_Info extends AppCompatActivity {
TextView name_food,price, description;
ImageView img_food;
FloatingActionButton action_button;
CollapsingToolbarLayout tool_layout;
ElegantNumberButton number;

FirebaseDatabase database;
DatabaseReference foods;
Items items;
String itemsId="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food__info);

    foods=FirebaseDatabase.getInstance().getReference("Items");
    number= findViewById(R.id.number);
    tool_layout=findViewById(R.id.tool_layout);
    name_food=findViewById(R.id.name_food);
    price=findViewById(R.id.price);
    description=findViewById(R.id.description);
    img_food=findViewById(R.id.img_food);
    tool_layout.setCollapsedTitleTextAppearance(R.style.ExpandedAppbar);
    tool_layout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);
        action_button=findViewById(R.id.action_button);
        action_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addOrder(new Order(
                        itemsId,
                        items.getName(),
                        number.getNumber(),
                        items.getPrice()
                ));
                Toast.makeText(Food_Info.this,"Order is added",Toast.LENGTH_SHORT).show();
            }
        });
    if(getIntent()!=null)
        itemsId=getIntent().getStringExtra("ItemsId");
    if(!itemsId.isEmpty()){
        getFoodInfo(itemsId);
    }

    }

    private void getFoodInfo(String itemsId) {
    foods.child(itemsId).addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            items=dataSnapshot.getValue(Items.class);

            Picasso.with(getBaseContext()).load(items.getImage()).into(img_food);
            tool_layout.setTitle(items.getName());
            price.setText(items.getPrice());
            name_food.setText(items.getName());
            description.setText(items.getDescription());
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });

    }
}
