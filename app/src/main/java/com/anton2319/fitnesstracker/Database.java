package com.anton2319.fitnesstracker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class Database extends MainActivity {
    public static final String DB_NAME = "app.db";
    public static final String DB_TABLE = "trainings";
    private MainActivity mainActivity = new MainActivity();
    static Context context;
    public Database(Context context) {
        Database.context = context;
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    //Функция читает базу данных и выводит данные в Activity
    public void readDatabase(int id) {
        try(SQLiteDatabase db = context.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null)) {
            //Если таблица не существует надо создать
            db.execSQL("CREATE TABLE IF NOT EXISTS trainings (amount INTEGER, name TEXT,  number INTEGER, time INTEGER, id INTEGER)");
            //Отправляем запрос к базе данных и сохраняем ответ в переменной query
            Cursor query = db.rawQuery("SELECT * FROM trainings WHERE id=" + id + ";", null);
            //Парсим ответ базы данных
            if (query.moveToFirst()) {
                int amount = query.getInt(0);
                String name = query.getString(1);
                int number = query.getInt(2);
                int time = query.getInt(3);
                //Оставлено для дебага
                System.out.println("Значения в базе данных: " + amount + "," + name + "," + number + "," + time);
                //Закрываем соединение с базой данных
                query.close();
                // Формируем из полученных данных блок в ленте событий
                createLentaBlock(name, time, number, amount);
            } else {
                //Сообщение об ошибке
                System.out.println("Сломалос(");
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void writeDatabase(int amount, String text, int number, int time, int id) {
        //Создание или открытие (если есть) базы данных для хранения данных приложения
        try (SQLiteDatabase db = context.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null)) {
            //Если таблица не существует надо создать
            db.execSQL("CREATE TABLE IF NOT EXISTS " + DB_TABLE + " (amount INTEGER, name TEXT,  number INTEGER, time INTEGER, id INTEGER)");
            db.execSQL("INSERT INTO " + DB_TABLE + " (amount, name, number, time, id) VALUES ("+amount+",'"+text+"',"+number+","+time+","+id+")");
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public int getDatabaseLength() {
        //Создание или открытие (если есть) базы данных для хранения данных приложения
        try (SQLiteDatabase db = context.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null)) {
            //Если таблица не существует надо создать
            db.execSQL("CREATE TABLE IF NOT EXISTS " + DB_TABLE + " (amount INTEGER, name TEXT,  number INTEGER, time INTEGER, id INTEGER)");
            //Отправляем запрос к базе данных и сохраняем ответ в переменной query
            Cursor query = db.rawQuery("SELECT COUNT(*) FROM " + DB_TABLE + ";", null);
            //Парсим ответ базы данных
            if (query.moveToFirst()) {
                return query.getInt(0);
            } else {
                return 0;
            }
        }
    }
}
