package com.anton2319.fitnesstracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class MainActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    //Функция выполняемая при старте приложения
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Database database = new Database(this);
//        int databaseLength = database.getDatabaseLength();
//        for (int i = 0; i < databaseLength; i++) {
//            database.readDatabase(1);
//        }
        createLentaBlock("1", 1, 1, 1);
    }

    //Функция выполняет переход в меню добавления тренировки
    public void openTableAdd(View view) {
        //Переход в TableAddActivity
        Intent intent = new Intent(getBaseContext(), TableAddActivity.class);
        startActivity(intent);
        Button addbutton = findViewById(R.id.addbutton);
        addbutton.bringToFront();
    }

    //Функция формирует блок в ленте событий
    public void createLentaBlock(String name, int time, int number, int amount) {
        LinearLayout lenta = (LinearLayout) findViewById(R.id.lenta);
        TextView header = new TextView(MainActivity.this);
        header.setText(name);
        header.setTextSize(40);
        header.setPadding(10, 10, 0, 0);
        TextView timeP = new TextView(MainActivity.this);
        timeP.setText("Время: " + time + " минут");
        timeP.setTextSize(20);
        timeP.setPadding(10, 10, 0, 0);
        TextView numberP = new TextView(MainActivity.this);
        numberP.setText("Кол-во повторений: " + number);
        numberP.setTextSize(20);
        numberP.setPadding(10, 10, 0, 0);
        TextView amountP = new TextView(MainActivity.this);
        amountP.setText("Кол-во подходов: " + amount);
        amountP.setTextSize(20);
        amountP.setPadding(10, 10, 0, 0);
        LinearLayout linearLayout = new LinearLayout(MainActivity.this);
        CardView lentablock = new CardView(MainActivity.this);
        CardView.LayoutParams lentablockParams = new CardView.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        lentablock.setLayoutParams(lentablockParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(header);
        linearLayout.addView(timeP);
        linearLayout.addView(amountP);
        lentablock.setPadding(0, 2, 0, 1);
        lentablock.addView(linearLayout);
        lenta.addView(lentablock);
    }
}
