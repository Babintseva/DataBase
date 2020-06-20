package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView Text;
    Database DB;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB = new Database(this);
        Text = findViewById(R.id.Text);
        Button AddBTN =findViewById(R.id.AddBTN);
        Button Delete = findViewById(R.id.DeleteBTN);
        listView = findViewById(R.id.List);
        GetData();

        AddBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Text.getText().toString().length() != 0){
                    DB.AddData(Text.getText().toString());
                    GetData();
                }
                else{
                    Toast.makeText(MainActivity.this,"Напишите что-то в строку!",Toast.LENGTH_LONG).show();
                }
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.DeleteAllData();
                Intent intent1 = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });
    }
    public void GetData() {
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = DB.getListContents();

        if(data.getCount()==0){
            Toast.makeText(MainActivity.this,"Список пуст",Toast.LENGTH_LONG).show();
        }
        else{
            while (data.moveToNext()){
                theList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);
            }
        }

    }
}
