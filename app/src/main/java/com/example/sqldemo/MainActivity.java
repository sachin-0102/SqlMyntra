package com.example.sqldemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText name,price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        name = (EditText) findViewById(R.id.itemName);
        price = (EditText) findViewById(R.id.price);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemName = name.getText().toString();
                String cost = price.getText().toString();
                if(itemName.length()==0||cost.length()==0){
                    Toast.makeText(MainActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                }
                else{
                    name.setText("");
                    price.setText("");
                    Date c = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                    String formattedDate = df.format(c);
                   // Toast.makeText(MainActivity.this,formattedDate, Toast.LENGTH_SHORT).show();
                    TransactionModel transactionModel = new TransactionModel(itemName,1,Integer.parseInt(cost), formattedDate);
                    transactionModel.setDate(formattedDate);
                    transactionModel.setAmount(Integer.parseInt(cost));
                    transactionModel.setId(1);
                    transactionModel.setItemName(itemName);
                   // Toast.makeText(MainActivity.this,transactionModel.getDate(),Toast.LENGTH_SHORT).show();
                    //Toast.makeText(MainActivity.this,transactionModel.getItemName(),Toast.LENGTH_SHORT).show();
                   // Toast.makeText(MainActivity.this,transactionModel.getId(),Toast.LENGTH_SHORT).show();
                    //Toast.makeText(MainActivity.this,Integer.toString(transactionModel.getAmount()),Toast.LENGTH_SHORT).show();
                    DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this );
                    boolean success = databaseHelper.addOne(transactionModel);
                    int sum30 = databaseHelper.last30days();
                    int sum90 = databaseHelper.last90days();
                    int sum180 = databaseHelper.last180days();
                    int sum365 = databaseHelper.last365days();
                    String message ="";
                    if(sum365>=20000){
                        message = "Congratulations!\nSince your purchase in the last 1 year has been more than 20000, you have been gifted a voucher of Rs.2500/-\nThank you for being an amazing customer!";
                    }
                    else if(sum180>=10000){
                        message = "Congratulations!\nSince your purchase in the last 6 months has been more than 10000, you have been gifted a voucher of Rs.1250/-\nThank you for being an amazing customer!";
                    }
                    else if(sum90>=5000){
                        message = "Congratulations!\nSince your purchase in the last 3 months has been more than 5000, you have been gifted a voucher of Rs.500/-\nThank you for being an amazing customer!";
                    }
                    else if(sum30>=2500){
                        message = "Congratulations!\nSince your purchase in the last 1 month has been more than 2500, you have been gifted a voucher of Rs.250/-\nThank you for being an amazing customer!";
                    }
                    else{
                        message = "Shop more to get amazing deals and vouchers\nThank you for being an amazing customer!";
                    }
                    displayDialog(message);
                    int current = databaseHelper.currentYear();
                    //Toast.makeText(MainActivity.this,Integer.toString(current),Toast.LENGTH_SHORT).show();
                    if(current>=50000){
                        message = "Congratulations!\nYou are now a eligible for a Platinum Membership.\nThank you for being an amazing customer!";
                    }
                    else if(current>=30000){
                        message = "Congratulations!\nYou are now a eligible for a Gold Membership.\nThank you for being an amazing customer!";
                    }
                    else{
                        message = "Congratulations!\nYou are now a eligible for a Silver Membership.\nThank you for being an amazing customer!";
                    }

                    displayDialog(message);
                    //Toast.makeText(MainActivity.this, "Success = "+success, Toast.LENGTH_SHORT).show();

                }



            }
        });
    }

    private void displayDialog(String message) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Congratulations")
                .setMessage(message)

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                    }
                })
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                Intent intent1 = new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(intent1);
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                break;
            case R.id.transaction:
                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                List<TransactionModel> transactions = databaseHelper.getTransactions();
                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                startActivity(intent);
                break;
                //Toast.makeText(this, transactions.toString(), Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}