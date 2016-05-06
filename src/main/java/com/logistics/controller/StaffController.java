package com.logistics.controller;

import com.alibaba.fastjson.JSONObject;
import com.logistics.conf.Configurator;
import com.logistics.entity.Staff;
import com.logistics.service.StaffService;
import com.logistics.util.JsonUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import static com.logistics.util.Params.needLongList;

/**
 * Created by Ranger on 2015/8/18.
 */
@Controller
@RequestMapping("staff")
public class StaffController {
    @Resource
    StaffService staffService;

    /**
     * <p>添加职员信息</p>
     * <p>api     = /staff</p>
     * <p>method  = POST</p>
     *
     * @param staff    需要添加的Staff对象
     * @param response HttpServletResponse的一个实例
     * @throws java.io.IOException 返回JSON数据给前端可能会抛出这个异常
     */
    @RequestMapping(method = RequestMethod.POST, params = "json")
    public void addStaffRec(Staff staff, HttpServletResponse response) throws IOException {
        Integer staffId = staffService.add(staff);
        if (staffId != null) {
            JsonUtil.writeMsg(staffId.toString(), response);
        } else {
            JsonUtil.writeMsg("-1", response);
        }
    }


    /**
     * <p>删除职员信息</p>
     * <p>API</p>
     * <p>DELETE        /staff</p>
     *
     * @param staffId 要删除的职员记录的ID（必需）
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public void staff_DELETE(Integer staffId, HttpServletResponse resp) throws IOException {
        staffService.delete(staffId);
        JsonUtil.writeMsg("1", resp);
    }

    /**
     * <p>多项删除</p>
     * <p>API</p>
     * <p>DELETE        /staff?many</p>
     * <p>参数</p>
     * <p>ids=(1,2,3)</p>
     *
     * @throws IOException 返回JSON数据给前端可能会抛出这个异常
     */
    @RequestMapping(method = RequestMethod.DELETE, params = "many")
    public void staff_DELETE_MANY(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Long> ids = needLongList(req, "ids");
        System.out.println(ids.size());
        for (long id : ids) {
            staffService.delete((int) id);
        }
        JsonUtil.writeMsg("1", resp);
    }

    /**
     * <p>获取职员信息</p>
     * <p>api     = /staff</p>
     * <p>method  = GET</p>
     *
     * @param start    从第几条记录开始显示
     * @param size     页面显示的数据条数
     * @param response HttpServletResponse的一个实例
     * @throws java.io.IOException 返回JSON数据给前端可能会抛出这个异常
     */
    @RequestMapping(method = RequestMethod.GET, params = "json")
    public void getJsonRecs(Integer start, Integer size, HttpServletResponse response) throws IOException {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
        start = (start != null) ? start : 0;
        size = (size != null) ? size : Configurator.getInstance().getInt("defaultPageSize");
        List<Staff> staffs = staffService.get(detachedCriteria, start, size);

        JSONObject json = new JSONObject();
        json.put("staffLength", staffService.getAll().size());
        JsonUtil.write(staffs, "staffs", json, response);
    }

    /**
     * <p>获取单个职员的信息</p>
     * <p>api     = /staff/staffId</p>
     * <p>method  = GET</p>
     *
     * @param staffId  需要获取的职员的记录的ID
     * @param response HttpServletResponse的一个实例
     * @throws java.io.IOException 返回JSON数据给前端可能会抛出这个异常
     */
    @RequestMapping(value = "{staffId}", method = RequestMethod.GET, params = "json")
    public void getJsonRec(@PathVariable("staffId") Integer staffId, HttpServletResponse response) throws IOException {
        JsonUtil.write(staffService.get(staffId), response);
    }

    @RequestMapping(value = "{staffId}", method = RequestMethod.GET)
    public String get_staff(@PathVariable("staffId") Integer staffId, Model model) throws IOException {
        model.addAttribute("staff", staffService.get(staffId));
        return "/staff/staffShow";
    }

    /**
     * <p>获取可以调用的司机</p>
     * <p>GET    /staff?availableDriver</p>
     *
     * @throws IOException 返回JSON数据给前端可能会抛出这个异常
     */
    @RequestMapping(method = RequestMethod.GET, params = "availableDriver")
    public void getAvailableDriver(HttpServletResponse response) throws IOException {
        JsonUtil.write(staffService.getAvailableDriver(), response);
    }

    /**
     * <p>根据职位获取人员列表</p>
     * <p>GET    /staff?job={job}&getByJob</p>
     *
     * @param job 人员职位
     * @throws IOException 返回JSON数据给前端可能会抛出这个异常
     */
    @RequestMapping(method = RequestMethod.GET, params = "getByJob")
    public void getStaffByJob(String job, HttpServletResponse response) throws IOException {
        job = URLDecoder.decode(job, "UTF-8");
        JsonUtil.write(staffService.getStaffList(job), response);
    }

    /**
     * <p>修改职员记录</p>
     * <p>api      = /staff</p>
     * <p>method   = PUT</p>
     *
     * @param staff 修改后的对象
     */
    @RequestMapping(method = RequestMethod.PUT, params = "json")
    public void updateStaff(Staff staff, HttpServletResponse resp) throws IOException {
        staffService.update(staff);
        JsonUtil.writeMsg("1", resp);
    }
}
