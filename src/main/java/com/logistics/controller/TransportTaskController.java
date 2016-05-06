package com.logistics.controller;

import com.alibaba.fastjson.JSONObject;
import com.logistics.conf.Configurator;
import com.logistics.entity.TransportTask;
import com.logistics.service.TransportTaskService;
import com.logistics.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Ranger on 2015/8/21.
 */
@Controller
@RequestMapping("transportTask")
public class TransportTaskController {
    @Resource
    private TransportTaskService transportTaskService;

    /**
     * <p>增加运输单数据</p>
     * <p>POST    /transportTask?json</p>
     *
     * @param transportTask 需要保存的运输任务单数据
     * @throws IOException 传输数据的时候可能会抛出这个异常
     */
    @RequestMapping(method = RequestMethod.POST, params = "json")
    public void add(TransportTask transportTask,
                    Integer customerId, HttpServletResponse response) throws IOException {
        Integer id = transportTaskService.add(transportTask, customerId);
        if (id != null) {
            JsonUtil.writeMsg(id.toString(), response);
        } else {
            JsonUtil.writeMsg("-1", response);
        }
    }

    /**
     * <p>删除运输任务单</p>
     * <p>DELETE    /transportTask</p>
     *
     * @param request 需要在该参数中添加一个类型为int[]名为idArray的属性，
     *                方法会取出所有在idArray里面的数据并以此作为id删除相应的记录
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(HttpServletRequest request) {
        int[] idArray = (int[]) request.getAttribute("idArray");
        for (int id : idArray) {
            transportTaskService.delete(id);
        }
    }

    /**
     * <p>查询运输任务单</p>
     * <p>GET    /transportTask?json</p>
     *
     * @param start 从该条记录开始显示
     * @param size  页面显示的记录条数
     * @throws IOException 传输数据的时候可能会抛出这个异常
     */
    @RequestMapping(method = RequestMethod.GET, params = "json")
    public void get(Integer start, Integer size, HttpServletResponse response) throws IOException {
        start = (start != null) ? start : 0;
        size = (size != null) ? size : Configurator.getInstance().getInt("defaultPageSize");
        List<TransportTask> tasks = transportTaskService.get(start, size);

        JSONObject json = new JSONObject();
        json.put("taskLength", transportTaskService.getAll().size());
        JsonUtil.write(tasks, "tasks", json, response);
    }

    /**
     * <p>查询单个任务单数据</p>
     * <p>GET    /transportTask/{transportTaskId}?json</p>
     *
     * @param transportTaskId 需要获取的记录的ID
     * @throws IOException 传输数据的时候可能会抛出这个异常
     */
    @RequestMapping(value = "{transportTaskId}", method = RequestMethod.GET, params = "json")
    public void getJson(@PathVariable("transportTaskId") Integer transportTaskId, HttpServletResponse response) throws IOException {
        JsonUtil.write(transportTaskService.get(transportTaskId), response);
    }

    /**
     * <p>修改运输任务单</p>
     * <p>PUT    /transportTask</p>
     *
     * @param transportTask 修改后的运输记录数据
     * @param customerId    发布运输任务的用户的ID
     */
    @RequestMapping(method = RequestMethod.PUT)
    public void update(TransportTask transportTask, Integer customerId, HttpServletResponse resp) throws IOException {
        transportTaskService.update(transportTask, customerId);
        JsonUtil.writeMsg("1", resp);
    }

    @RequestMapping(value = "{cusId}", method = RequestMethod.GET, params = "getByCusId")
    public void getTransportTaskByCusId(@PathVariable("cusId") Integer cusId, HttpServletResponse response) throws IOException {
        JsonUtil.write(transportTaskService.getTransportTaskByCusId(cusId), response);
    }
}
