package com.logistics.controller;

import com.alibaba.fastjson.JSONObject;
import com.logistics.conf.Constants;
import com.logistics.crypto.RSAKeyCache;
import com.logistics.entity.Admin;
import com.logistics.service.AdminService;
import com.logistics.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.List;

import static com.logistics.crypto.CryptoBase.byteToHex;
import static com.logistics.util.Params.needByteArray;
import static com.logistics.util.Params.needString;

/**
 * Created by Mklaus on 15/8/16.
 */
@Controller
@RequestMapping("admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    /**
     * <p>获取公钥</p>
     * <p>POST      /admin/get_rsa_key</p>
     * @param adminName
     * @param resp
     * @throws IOException
     */
    @RequestMapping(value = "get_rsa_key",method = RequestMethod.POST)
    public void get_rsa_key(String adminName,HttpServletResponse resp) throws IOException {
        System.out.println("rsa in " + adminName);
        JSONObject json = new JSONObject();
        int id;
        if ((id = adminService.getIdByAdminName(adminName)) < 0){
            System.out.println("error");
            json.put("status",-1);
        }else {

            RSAPublicKey pk = (RSAPublicKey) RSAKeyCache.getInstance().genKey((long) id);


            byte[] m = pk.getModulus().toByteArray();
            if (m[0] == 0)
                m = Arrays.copyOfRange(m, 1, m.length);
            byte[] e = pk.getPublicExponent().toByteArray();
            if (e[0] == 0)
                e = Arrays.copyOfRange(e, 1, e.length);

            json.put("m", byteToHex(m));
            json.put("e", byteToHex(e));

            System.out.println("OK");
        }

        JsonUtil.write(json,resp);
    }


    /**
     * <p>跳转到管理员添加页面</p>
     * <p>GET       /admin/register</p>
     * @return
     */
    @RequestMapping(value = "register",method = RequestMethod.GET)
    public String register(){
        return "/admin/adminRegister";
    }

    /**
     * <p>管理员注册</p>
     * <p>POST      /admin/doRegister</p>
     * @param req
     * @param resp
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST)
    public void doRegister(Integer type,HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Admin login;
        String password  = needString(req,"password");
        String adminName = needString(req,"adminName");

        if (!adminService.isExists(adminName)) {
           if((login = adminService.register(new Admin(adminName,password.getBytes("UTF-8"),type))) != null) {
               JsonUtil.writeMsg("1", resp);
           }
        }else {
            JsonUtil.writeMsg("user already exists", resp);
        }

    }

    @RequestMapping(method = RequestMethod.GET)
    public void admin_GET(Integer start,Integer size,HttpServletResponse resp) throws IOException{
        start = (start != null) ? start : 0;
        size  = (size != null) ? size : 10;
        List<Admin> l = adminService.get(start,size);
        JSONObject json = new JSONObject();
        json.put("adminLength",adminService.getAll().size());
        JsonUtil.write(l,"admins",json,resp);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void admin_DELETE(Integer adminId,HttpServletResponse resp) throws IOException{
        adminService.delete(adminId);
        JsonUtil.writeMsg("1",resp);
    }

    /**
     *  --------- login and logout ----------
     * */

    /**
     * <p>跳转到登录页面</p>
     * <p>GET       /admin/login</p>
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(){
        return "/admin/adminLogin";
    }

    /**
     * <p>登录处理</p>
     * <p>POST      /admin/doLogin</p>
     * @param session
     * @param req
     * @param resp
     * @throws IOException
     */
    @RequestMapping("doLogin")
    public void doLogin(HttpSession session,HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Admin login;
        JSONObject json = new JSONObject();
        byte[] password  = needByteArray(req,"password");
        String adminName = needString(req,"adminName");

        if ((login = adminService.login(new Admin(adminName,password))) != null){
            System.out.println("success");
            session.setAttribute(Constants.ADMIN_UID,login.getId());
            json.put("status",0);
        }else {
            json.put("status",-1);
        }
        JsonUtil.write(json,resp);
    }

    /**
     * <p>登录成功跳转</p>
     * <p>GET       /admin/loginSuccess</p>
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("loginSuccess")
    public String loginSuccess(HttpSession session,Model model){
        int id = (Integer)session.getAttribute(Constants.ADMIN_UID);
        Admin admin = adminService.get(id);
        model.addAttribute("admin", admin);
        return "/common/start";
    }

    /**
     * <p>获取用户信息</p>
     * <p>GET    /user?json</p>
     *
     * @throws IOException 返回JSON数据给前端时可能会抛出这个异常
     */
    @RequestMapping(method = RequestMethod.GET, params = "json")
    public void user_GET_json(HttpSession session, HttpServletResponse resp) throws IOException {
        Integer id = (Integer) session.getAttribute(Constants.ADMIN_UID);
        Admin admin = adminService.get(id);
        JsonUtil.write(admin, resp);
    }


    /**
     * <p>登出</p>
     * <P>GET       /admin/logout</P>
     * @param req
     * @return
     */
    @RequestMapping("logout")
    public String logout(HttpServletRequest req){
        req.getSession().removeAttribute(Constants.ADMIN_UID);
        return "redirect:/index.jsp";
    }

    @RequestMapping(value = "passwd",method = RequestMethod.POST)
    public void passwd(String oldPass,String newPass,HttpSession session,HttpServletResponse resp) throws IOException{
        Integer id = (Integer)session.getAttribute(Constants.ADMIN_UID);
        Admin admin = adminService.get(id);
        if (adminService.passwd(admin,oldPass,newPass)){
            JsonUtil.writeMsg("1",resp);
        }else {
            JsonUtil.writeMsg("-1",resp);
        }
    }
}
