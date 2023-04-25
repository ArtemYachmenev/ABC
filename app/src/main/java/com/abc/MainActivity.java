package com.abc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.abc.sql_ms.ConSQL;

import java.sql.Connection;
import java.sql.ResultSet;

public class MainActivity extends AppCompatActivity  {

    //кнопки авторизации восст доступа и чекбокса
    private Button authorization;
    private Button recovery;
    private CheckBox checkBox;

    //поля логина и пароля
    private EditText login;
    private EditText pass;

    //строки которые берут вводимый логин и пароль
    private String yesLogin;
    private String yesPass;

    //подключение
    Connection connection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        authorization= (Button) findViewById(R.id.authorization);
        recovery=(Button) findViewById(R.id.recovery);
        checkBox=(CheckBox) findViewById(R.id.checkBoxAut);


        login=(EditText) findViewById(R.id.login);
        pass=(EditText) findViewById(R.id.password);


        //общий слушатель для всех
        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    //todo сделать сохранение логина пароля при нажатии чекбокса, сохранять его выбор
                    case R.id.checkBoxAut:
                        break;
                    case R.id.authorization:
                        //чтение ввода в строк
                        //проверка введенных полей
                        if ((login.getText().toString()==null||login.getText().toString().equals("")||login.getText().toString().equals(" "))
                                ||(pass.getText().toString()==null||pass.getText().toString().equals("")||pass.getText().toString().equals(" "))){
                            Toast notification=Toast.makeText(MainActivity.this,"Не введен логин или пароль",Toast.LENGTH_SHORT);
                            notification.show();
                        }
                        else {
                            yesLogin=login.getText().toString();
                            yesPass=pass.getText().toString();

                           if( loginAndPasswordVerification(yesLogin, yesPass)==true) {
                               goGeneralWindow();
                           }
                           else {
                               Toast notification=Toast.makeText(MainActivity.this,"Не верен логин или пароль",Toast.LENGTH_SHORT);
                               notification.show();
                           }
                        }

                        break;
                    //todo сделать восстановление пароля
                    case R.id.recovery:
                        setContentView(R.layout.recovery);
                        break;

                }
            }
        };
        //присваиваем общий слушатель всем
        authorization.setOnClickListener(onClickListener);
        recovery.setOnClickListener(onClickListener);
        checkBox.setOnClickListener(onClickListener);




    }

    //проверяем логин пароль в sql
    private boolean loginAndPasswordVerification(String yesLogin, String yesPass) {
        //создаем подключение к ms sql
        ConSQL con=new ConSQL();
        connection=con.conclass();
        User user=new User();
        user.setLogin(yesLogin);
        user.setPassword(yesPass);
        boolean exists=false;
        if (con!=null){

            try {
                ResultSet set=con.getUser(user);
                System.out.println(set==null);
                if (set.next()){
                    System.out.println(set==null);
                    //записываем в статич перем логин юзера
                    AllStaticData.setUserLoginAut(user.getLogin());
               //     AllStaticData.setCustomer_id(set.getInt("id_costumer"));
                //    System.out.println(AllStaticData.getCustomer_id());
                    exists=true;

                }

                connection.close();
            }
            catch (Exception e){
                Log.e("Error: ",e.getMessage());
            }

        }
        return exists;
    }

    //открываем главное окно после авторизации
    public void goGeneralWindow(){
        Intent intent=new Intent(this,GeneralActivity.class);
        startActivity(intent);
    }


    //создаем верхнее меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //todo написать отработку нажатий в верхнем меню
    //
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
//            case R.id.action_logOutOfProfile:
//                //отработка
//
//                break;
            case R.id.action_contactUs:
                //отработка

                break;
            case R.id.action_erorr:
                //отработка

                break;
        }

                return super.onOptionsItemSelected(item);

    }


}
