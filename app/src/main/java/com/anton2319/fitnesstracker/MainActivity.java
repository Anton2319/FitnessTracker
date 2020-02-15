package com.anton2319.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readDatabase();
    }
    //Функция выполняет переход в меню добавления тренировки
    public void openTableAdd(View view) {
        //Переход в TableAddActivity
        Intent intent = new Intent(getBaseContext(), TableAddActivity.class);
        startActivity(intent);
    }
    //Функция читает базу данных и выводит данные в Activity
    public void readDatabase() {
        //Создание или открытие (если есть) базы данных для хранения данных приложения
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        //Если таблица не существует надо создать
        db.execSQL("CREATE TABLE IF NOT EXISTS trainings (amount INTEGER, name TEXT,  number INTEGER, time INTEGER)");
        //Отправляем запрос к базе данных и сохраняем ответ в переменной query
        Cursor query = db.rawQuery("SELECT * FROM trainings;", null);
        //Закрываем соединение с базой данных
        db.close();
        //Парсим ответ базы данных
        if(query.moveToFirst()){
            int amount = query.getInt(0);
            String name = query.getString(1);
            int number = query.getInt(2);
            int time = query.getInt(3);
            System.out.println("Значения в базе данных: "+amount+","+name+","+number+","+time);
            query.close();
        }
        else {
            //Сообщение об ошибке
            System.out.println("Сломалос(");
        }
    }
}
