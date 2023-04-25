package com.abc;

//хранятся статики
public class AllStaticData {

    //используется для запомиания логина в авторизации
    private static String userLoginAut;
    private static int customer_id;


    public static int getCustomer_id() {
        return customer_id;
    }

    public static void setCustomer_id(int customer_id) {
        AllStaticData.customer_id = customer_id;
    }

    public static String getUserLoginAut() {
        return userLoginAut;
    }

    public static void setUserLoginAut(String userLoginAut) {
        AllStaticData.userLoginAut = userLoginAut;
    }
}
