package com.anton2319.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

public class TableAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_add);
    }
    public void submit(View view) {
        EditText amountEdit = findViewById(R.id.amountValue);
        EditText nameEdit = findViewById(R.id.nameValue);
        EditText numberEdit = findViewById(R.id.numberValue);
        EditText timeEdit = findViewById(R.id.timeValue);

        //Подходы
        int amount = Integer.parseInt(amountEdit.getText().toString());
        //Название упражнения
        String name = nameEdit.getText().toString();
        //Кол-во раз
        int number = Integer.parseInt(numberEdit.getText().toString());
        //Время в минутах
        int time = Integer.parseInt(timeEdit.getText().toString());

        //Создание или открытие (если есть) базы данных для хранения данных приложения
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        //Если таблица не существует надо создать
        db.execSQL("CREATE TABLE IF NOT EXISTS trainings (amount INTEGER, name TEXT, number INTEGER, time INTEGER)");
        //Отправляем запрос к базе данных на запись данных
        db.execSQL("INSERT INTO trainings VALUES ("+amount+", '"+name+"', "+number+", "+time+");");
        //Переход обратно на главный экран
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
    }
}
