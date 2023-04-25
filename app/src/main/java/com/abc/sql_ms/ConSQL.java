package com.abc.sql_ms;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import com.abc.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConSQL {
    Connection con;
    @SuppressLint("NewAPI")
    public Connection conclass() {

        String ip="192.168.21.61", port="61538", db="abc", username="artem", password="1";
        StrictMode.ThreadPolicy a=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(a);
        String ConnectURL=null;
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectURL="jdbc:jtds:sqlserver://"+ip+":"+port+";"+"databasename="+db+
                ";user="+username+";"+"password="+password+";";
            con= DriverManager.getConnection(ConnectURL);

        }
        catch (Exception e){
            Log.e("Error is ", e.getMessage());
        }
        return con;

    }

    //получаем пользователя из sql
    public ResultSet getUser(User user){
        ResultSet resultSet=null;
        String select = "select * from "+ StuffConst.DB+" where "+ StuffConst.USER_LOGIN+"=? and "+ StuffConst.USER_PASS+"=?";

        try {
            PreparedStatement prSt=conclass().prepareStatement(select);
            prSt.setString(1,user.getLogin());
            prSt.setString(2,user.getPassword());
            resultSet=prSt.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return resultSet;

    }

    //присваивание данных юзеру
    public ResultSet getIdCuns(User user){
        ResultSet resultSet=null;
        String select = "select customer_id from "+ StuffConst.DB+" where "+ StuffConst.ID_STUFF+"=? ";

        try {
            PreparedStatement prSt=conclass().prepareStatement(select);
            prSt.setString(1,user.getLogin());
            resultSet=prSt.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return resultSet;

    }

}
