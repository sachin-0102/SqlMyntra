package com.example.sqldemo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView sub = (TextView)findViewById(R.id.textView2);

        DatabaseHelper databaseHelper = new DatabaseHelper(ProfileActivity.this);
        List<TransactionModel> transactions = databaseHelper.getTransactions();
        int sum=0;
        for(int i=0;i<transactions.size();i++){
            sum+=transactions.get(i).getAmount();
        }
        if(sum>10000){
             sub.setText("Subscription : Silver");
        }
        if(sum>30000){
            sub.setText("Subscription : Gold");
        }
        if(sum>50000){
            sub.setText("Subscription : Platinum");
        }
    }
}