package com.logistics.sys.interceptor;

/**
 * Created by Mklaus on 15/8/16.
 */
public class UserAuthorizationException extends Exception{
    public UserAuthorizationException(){
        super();
    }

    public UserAuthorizationException(String desc){
        super("user need login " + desc);
    }
}
