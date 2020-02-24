package com.project.punjabresturant.SQLiteData;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import com.project.punjabresturant.Model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "OrderDatabase.db";
    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public List<Order> getOrder(){
        SQLiteDatabase data= getReadableDatabase();
        SQLiteQueryBuilder builder= new SQLiteQueryBuilder();
        String[] sql={"ItemName", "ItemId","Quantity","Price"};
        String table="OrderTable";
        builder.setTables(table);
        Cursor cursor=builder.query(data,sql,null,null,null,null,null);
        final List<Order> rs= new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                rs.add(new Order(cursor.getString(cursor.getColumnIndex("ItemId")),
                        cursor.getString(cursor.getColumnIndex("ItemName")),
                        cursor.getString(cursor.getColumnIndex("Quantity")),
                        cursor.getString(cursor.getColumnIndex("Price"))
                ));
            }while (cursor.moveToNext());
        }

        return rs;
    }

    public void addOrder(Order order){
        SQLiteDatabase data=getWritableDatabase();
        String query= String.format("INSERT INTO OrderTable(ItemName,ItemId,Quantity,Price) VALUES('%s','%s','%s','%s');",
                order.getItemId(),
                order.getItemName(),
                order.getQuantity(),
                order.getPrice()
        );
        data.execSQL(query);
    }
}
