package com.logistics.controller;

import com.alibaba.fastjson.JSONObject;
import com.logistics.conf.Constants;
import com.logistics.entity.User;
import com.logistics.service.UserService;
import com.logistics.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Ranger on 2015/8/17.
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Resource
    UserService userService;


    @RequestMapping("home")
    public String home(){
        return "/home/mainPage";
    }

    /**
     * <p>客户登录</p>
     * <p>GET  /user/login</p>
     *
     * @param user 需要进行登录的用户
     * @throws java.io.IOException 返回JSON数据给前端时可能会抛出这个异常
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public void login(User user, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        User userRec = userService.login(user);
        JSONObject json = new JSONObject();
        if (userRec != null) {
            System.out.println("success");
            model.addAttribute("user", user);
            System.out.println("ID in login = " + userRec.getId());
            session.setAttribute(Constants.USER_UID, userRec.getId());
            json.put("status", 0);
        } else {
            System.out.println("error");
            json.put("status", -1);
        }
        JsonUtil.write(json, response);
    }

    /**
     * <p>用户注册</p>
     * <p>GET  /user/register</p>
     *
     * @param user 要进行注册的用户
     * @throws java.io.IOException 返回JSON数据给前端时可能会抛出这个异常
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public void register(User user, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        User userRec = userService.register(user);
        if (userRec != null) {
            model.addAttribute("user", user);
            session.setAttribute(Constants.USER_UID, userRec.getId());
            JsonUtil.writeMsg("1", response);
        } else {
            JsonUtil.writeMsg("-1", response);
        }
    }

    /**
     * <p>用户注销</p>
     * <p>GET    /user/logout</p>
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute(Constants.USER_UID);
        return "/home/mainPage";
    }

    /**
     * <p>删除用户</p>
     * <p>DELETE    /user</p>
     *
     * @param idArray 需要删除的用户的ID数组
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(int[] idArray) {
        for (int id : idArray) {
            userService.delete(id);
        }
    }

    /**
     * <p>获取用户信息</p>
     * <p>GET    /user?json</p>
     *
     * @throws IOException 返回JSON数据给前端时可能会抛出这个异常
     */
    @RequestMapping(method = RequestMethod.GET, params = "json")
    public void user_GET_json(HttpSession session, HttpServletResponse resp) throws IOException {
        Integer id = (Integer) session.getAttribute(Constants.USER_UID);
        User user = userService.get(id);
        JsonUtil.write(user, resp);
}

    /**
     * <p>更新用户记录</p>
     * <p>PUT    /user</p>
     *
     * @param user 修改后的用户记录
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.PUT)
    public void user_Update(User user, HttpSession session, HttpServletResponse resp) throws IOException {
        Integer id = (Integer) session.getAttribute(Constants.USER_UID);
        User login = userService.get(id);
        user.setId(login.getId());
        user.setPassword(login.getPassword());
        userService.update(user);
        JsonUtil.writeMsg("1", resp);
    }

    /**
     * <p>修改用户密码</p>
     * <p>POST    /user/passwd?oldPass={oldPass}&newPass={newPass}</p>
     *
     * @param oldPass 旧密码
     * @param newPass 新密码
     * @throws IOException 返回JSON数据给前端时可能会抛出这个异常
     */
    @RequestMapping(value = "passwd", method = RequestMethod.POST)
    public void user_passwd(String oldPass, String newPass, HttpSession session,
                            HttpServletResponse resp) throws IOException {
        Integer id = (Integer) session.getAttribute(Constants.USER_UID);
        if (userService.passwd(id, oldPass, newPass)) {
            JsonUtil.writeMsg("1", resp);
        } else {
            JsonUtil.writeMsg("-1", resp);
        }
    }

    /**
     * <p>根据Id获取用户信息</p>
     * <p>GET    /user/{userId}</p>
     *
     * @param userId 需要获取的用户的ID
     * @throws IOException 返回JSON数据给前端时可能会抛出这个异常
     */
    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    public void getJsonRec(@PathVariable("userId") Integer userId, HttpServletResponse response) throws IOException {
        JsonUtil.write(userService.get(userId), response);
    }
}
