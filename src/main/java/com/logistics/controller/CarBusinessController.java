package com.logistics.controller;

import com.alibaba.fastjson.JSONObject;
import com.logistics.conf.Configurator;
import com.logistics.entity.CarBusiness;
import com.logistics.service.CarBusinessService;
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

import static com.logistics.util.Params.needLongList;

/**
 * Created by Ranger on 2015/8/18.
 */
@Controller
@RequestMapping("carBusiness")
public class CarBusinessController {
    @Resource
    CarBusinessService carBusinessService;

    /**
     * <p>增加单车运营记录</p>
     * <p>POST    /carBusiness</p>
     *
     * @param carBusiness 需要添加的单车运营记录
     * @param taskId      任务单的ID
     * @param vehicleId   运输车辆ID
     * @throws java.io.IOException 返回JSON数据给前端可能会抛出这个异常
     */
    @RequestMapping(method = RequestMethod.POST)
    public void addRec(CarBusiness carBusiness, Integer taskId, Integer vehicleId, HttpServletResponse response) throws IOException {
        Integer returnValue;
        if ((returnValue = carBusinessService.add(taskId, vehicleId, carBusiness)) != null) {
            JsonUtil.writeMsg(returnValue.toString(), response);
        } else {
            JsonUtil.writeMsg("-1", response);
        }
    }

    /**
     * <p>删除单车运营记录</p>
     * <p>DELETE    /carBusiness</p>
     *
     * @param id 需要删除的单车运营记录的ID数组
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteRec(int id, HttpServletResponse resp) throws IOException {
        CarBusiness c = carBusinessService.get(id);
        c.setTask(null);
        c.setVehicle(null);
        carBusinessService.delete(c);
        JsonUtil.writeMsg("1", resp);
    }

    @RequestMapping(method = RequestMethod.DELETE, params = "many")
    public void deleteMany(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Long> ids = needLongList(req, "ids");
        System.out.println(ids.size());
        for (long id : ids) {
            carBusinessService.delete((int) id);
        }
        JsonUtil.writeMsg("1", resp);
    }

    /**
     * <p>获取单车运营记录</p>
     * <p>GET    /carBusiness</p>
     *
     * @param start 从该条记录开始显示
     * @param size  页面显示的记录条数
     * @throws java.io.IOException 返回JSON数据给前端可能会抛出这个异常
     */
    @RequestMapping(method = RequestMethod.GET)
    public void getJsonRec(Integer start, Integer size, HttpServletResponse response) throws IOException {
        start = (start != null) ? start : 0;
        size = (size != null) ? size : Configurator.getInstance().getInt("defaultPageSize");
        List<CarBusiness> carBussinessList = carBusinessService.get(start, size);
        JSONObject json = new JSONObject();
        json.put("carBussinessLength", carBusinessService.getAll().size());
        JsonUtil.write(carBussinessList, "carBussinessList", json, response);
    }

    /**
     * <p>显示单个单车运营记录</p>
     * <p>GET    /carBusiness</p>
     *
     * @param carBusinessID 需要查找的单车运营记录的ID
     * @throws java.io.IOException 返回JSON数据给前端可能会抛出这个异常
     */
    @RequestMapping(value = "{carBusinessID}", method = RequestMethod.GET)
    public void getJsonRec(@PathVariable("carBusinessID") Integer carBusinessID, HttpServletResponse response) throws IOException {
        CarBusiness carBusiness = carBusinessService.get(carBusinessID);
        JsonUtil.write(carBusiness, response);
    }

    /**
     * <p>修改单车运营记录</p>
     * <p>PUT    /carBusiness</p>
     *  @param taskId      任务单的ID
     * @param vehicleId   运输车辆ID
     * @param carBusiness 修改后的单车运营记录
     * @param resp
     */
    @RequestMapping(method = RequestMethod.PUT)
    public void updateCarBusinessRec(Integer taskId, Integer vehicleId, CarBusiness carBusiness, HttpServletResponse resp) throws IOException {
        carBusinessService.update(taskId, vehicleId, carBusiness);
        JsonUtil.writeMsg("1", resp);
    }

    @RequestMapping(value = "{taskId}", method = RequestMethod.GET, params = "taskId")
    public void getCargoByTaskId(@PathVariable("taskId") int taskId, HttpServletResponse response) throws IOException {
        JsonUtil.write(carBusinessService.getCarBusinessByTaskId(taskId), response);
    }
}
