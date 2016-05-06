package com.logistics.sys.interceptor;

/**
 * Created by Mklaus on 15/9/3.
 */
public class AdminAuthorizationException extends Exception{
    public AdminAuthorizationException(){
        super();
    }

    public AdminAuthorizationException(String msg){
        super("admin need login "+msg);
    }
}
