package com.logistics.sys.interceptor;

import com.logistics.conf.Constants;
import com.logistics.dao.AdminDao;
import com.logistics.entity.Admin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Mklaus on 15/8/16.
 */
public class AuthorizeInterceptor implements HandlerInterceptor {
    public static final String DELETE   = "DELETE";
    public static final String GET      = "GET";
    public static final String PUT      = "PUT";
    public static final String POST     = "POST";

    public static final int SUPER_TYPE     = 1;

    @Resource
    private AdminDao adminDao;

    public static final String[] super_url = {
        "/admin"
    };

    public static final String[] company_url = {
        "/company"
    };

    public static final String[] address_url = {
        "/address"
    };

    public static final String[] goods_url = {
        "/goods"
    };

    public static final String[] vehicle_url = {
        "/vehicle",
        "/vehicleType"
    };

    public static final String[] staff_url = {
        "/staff"
    };

    public static final String[] oilBuy_url = {
        "/oilBuy"
    };

    public static final String[] carrier_task_url = {
        "/carrier",
        "/transportTask"
    };

    public static final String[] user_url = {
        "/user"
    };

    String request_uri = "";
    String request_method = "";

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object o) throws Exception {
        System.out.println("can intercepter. url = " + req.getRequestURI() + "  method = " + req.getMethod());
        request_uri = new String(req.getRequestURI());
        request_method = req.getMethod();

        boolean adminPass = admin_isPass(goods_url,2,req) && admin_isPass(staff_url,5,req)
                &&admin_isPass(carrier_task_url,3,req) && admin_isPass(vehicle_url,4,req)
                &&admin_isPass(company_url,6,req) && admin_isPass(oilBuy_url,7,req);

        boolean userPass  = user_isPass(user_url,req);

        return (adminPass && userPass);
    }

    private boolean admin_isPass(String[] urls,int type,HttpServletRequest req) throws Exception{
        boolean flag = true;
        Integer id;
        for (String uri : urls){
            System.out.println(uri + "  ===  " + request_uri);
            if (request_uri.equals(uri)){
                if ((id = (Integer)req.getSession().getAttribute(Constants.ADMIN_UID)) != null){
                    if (request_method.equals(GET))
                        return true;

                    int power = adminDao.get(id).getType();
                    if (power == SUPER_TYPE || power == type) {
                        flag = true;
                    }else {
                        flag = false;
                    }
                }else {
                    if (req.getSession().getAttribute(Constants.USER_UID) != null && request_uri.equals("/transportTask")){
                        return true;
                    }else {
                        throw new AdminAuthorizationException("in interceptor");
                    }
                }
            }
        }
        return flag;
    }

    private boolean user_isPass(String[] urls,HttpServletRequest req) throws Exception{
        boolean flag = true;
        for (String uri : urls){
            if (request_uri.equals(uri)){
                if (((Integer)req.getSession().getAttribute(Constants.USER_UID)) != null){
                    return true;
                }else {
                    throw new UserAuthorizationException("in interceptor");
                }
            }
        }
        return flag;
    }

    /**
     * 管理员的类型有：
     * 1、超级管理员，具有所有表CRUD的权限；
     * 2、物资管理员，具有对物资信息表CURD的权限；
     * 3、运输管理员，具有对货物，地址，单车运营记录，承运商业务记录,运输任务CRUD的权限；
     * 4、车辆管理员，具有对车辆信息CRUD的权限；
     * 5、人力资源管理员，具有对雇员信息CRUD的权限（人员信息表还没有做）；
     * 6、公司信息管理员，具有对公司信息CRUD的权限；
     * 7、财务管理员，具有对加油记录CRUD的权限。
     */

    private List<String> admin_authorization;
    private List<String> user_authorization;

    public void setAdmin_authorization(List<String> admin_authorization) {
        this.admin_authorization = admin_authorization;
    }

    public void setUser_authorization(List<String> user_authorization) {
        this.user_authorization = user_authorization;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
