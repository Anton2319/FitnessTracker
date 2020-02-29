package com.anton2319.fitnesstracker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;


public class TableAddActivity extends MainActivity {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_add);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void submit(View view) {
        EditText amountEdit = findViewById(R.id.amountValue);
        EditText nameEdit = findViewById(R.id.nameValue);
        EditText numberEdit = findViewById(R.id.numberValue);
        EditText timeEdit = findViewById(R.id.timeValue);

        int amount = Integer.parseInt(String.valueOf(amountEdit.getText()));
        String name = String.valueOf(nameEdit.getText());
        int number = Integer.parseInt(String.valueOf(numberEdit.getText()));
        int time = Integer.parseInt(String.valueOf(timeEdit.getText()));
        int id = 1;
        Database.writeDatabase(amount, name, number, time, id);
        //Переход обратно на главный экран
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
    }
}
