package com.abc;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.abc.sql_ms.ConSQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//implements View.OnClickListener
public class GeneralActivity extends AppCompatActivity  {


    //делаем список обращений
    ListView listView;
    List list=new ArrayList();
    ArrayAdapter adapter;

    Connection connection;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);


        listView=(ListView) findViewById(R.id.appeals);



        //создаем подключение к ms sql и выводим данные
        ConSQL con=new ConSQL();
        connection=con.conclass();
        if (con!=null){
            try {
                String sqlstatement="SELECT Appeals.appeals_id, Appeals.date, Appeals.text, Staff.name, Staff.surname, Staff.middle_name,  Appeals.done\n" +
                        "FROM Staff INNER JOIN Appeals\n" +
                        "ON Staff.stuff_id = Appeals.stuff_id;";
                Statement smt=connection.createStatement();
                ResultSet set=smt.executeQuery(sqlstatement);
                while (set.next()){
                    String line=set.getString(1)+" "+set.getString(2)+" "+
                            set.getString(3)+" "+set.getString(4)+" "+
                            set.getString(5)+" "+set.getString(6);
                    list.add(line);
                }
                connection.close();
            }
            catch (Exception e){
                Log.e("Error: ",e.getMessage());
            }

        }

        adapter=new ArrayAdapter(GeneralActivity.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
           //    goContentOfTheApplication(position);
                System.out.println(position);
            }
        });

    }

    //todo при нажатии открываем суть заявки

    public void goContentOfTheApplication(int position){
        Intent intent = new Intent(getParent(), ContentOfTheApplication.class);
        intent.putExtra("positionList", position);
        System.out.println(position);
        this.startActivity(intent);
    }


}