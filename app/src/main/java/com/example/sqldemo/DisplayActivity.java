package com.example.sqldemo;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        DatabaseHelper databaseHelper = new DatabaseHelper(DisplayActivity.this);
        ArrayList<TransactionModel> transactions = (ArrayList<TransactionModel>) databaseHelper.getTransactions();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        ListView listView1 = findViewById(R.id.listView1);
        ListView listView2 = findViewById(R.id.listView2);
        ListView listView3 = findViewById(R.id.listView3);
        int i;
        for(i=0;i<transactions.size();i++){
            TransactionModel cur = transactions.get(i);
            //Toast.makeText(DisplayActivity.this, cur.toString(), Toast.LENGTH_SHORT).show();
           // Toast.makeText(DisplayActivity.this, Integer.toString(cur.getId()), Toast.LENGTH_SHORT).show();
            list1.add(cur.getItemName());
            int am = cur.getAmount();
            String x = Integer.toString(am);
            list2.add(x);
            list3.add(cur.getDate());
        }

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list3);
        //Toast.makeText(this, transactions.toString(), Toast.LENGTH_SHORT).show();
        listView1.setAdapter(adapter1);
        listView2.setAdapter(adapter2);
        listView3.setAdapter(adapter3);

    }


}