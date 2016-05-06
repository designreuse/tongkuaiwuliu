package com.logistics.controller;

import com.alibaba.fastjson.JSONObject;
import com.logistics.conf.Configurator;
import com.logistics.entity.Vehicle;
import com.logistics.service.VehicleService;
import com.logistics.util.JsonUtil;
import org.hibernate.criterion.DetachedCriteria;
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
 * Created by Ranger on 2015/8/19.
 */
@Controller
@RequestMapping("vehicle")
public class VehicleController {
    @Resource
    private VehicleService vehicleService;

    /**
     * <p>添加车辆记录</p>
     * <p>POST    /vehicle</p>
     *
     * @param vehicle       需要添加的车辆记录
     * @param driverId      匹配的驾驶员记录ID
     * @param vehicleTypeId 匹配的车型记录ID
     * @throws IOException 传数据给前端时可能会抛出的异常
     */
    @RequestMapping(method = RequestMethod.POST)
    public void addRec(Vehicle vehicle, Integer driverId, Integer vehicleTypeId, HttpServletResponse response) throws IOException {
        Integer id = vehicleService.add(vehicle, driverId, vehicleTypeId);
        if (id != null) {
            JsonUtil.writeMsg(id.toString(), response);
        } else {
            JsonUtil.writeMsg("-1", response);
        }
    }

    /**
     * <p>删除多条车辆记录</p>
     * <p>DELETE    /vehicle?many</p>
     *
     * @param req 需要在该参数里面添加一个名称为ids的长整型数组
     * @throws IOException 传数据给前端时可能会抛出的异常
     */
    @RequestMapping(method = RequestMethod.DELETE, params = "many")
    public void vehicle_DELETE_MANY(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Long> ids = needLongList(req, "ids");
        for (long id : ids) {
            vehicleService.delete((int) id);
        }
        JsonUtil.writeMsg("1", resp);
    }

    /**
     * <p>删除单条车辆记录</p>
     * <p>DELETE    /vehicle?json</p>
     *
     * @param vehicleId 需要删除的车辆记录的ID
     * @throws IOException 传数据给前端时可能会抛出的异常
     */
    @RequestMapping(method = RequestMethod.DELETE, params = "json")
    public void vehicle_DELETE_json(Integer vehicleId, HttpServletResponse resp) throws IOException {
        vehicleService.delete(vehicleId);
        JsonUtil.writeMsg("1", resp);
    }

    /**
     * <p>获取车辆记录</p>
     * <p>GET    /vehicle?json</p>
     *
     * @param start 从该条记录开始显示
     * @param size  页面显示记录的条数
     * @throws IOException 传数据给前端时可能会抛出的异常
     */
    @RequestMapping(method = RequestMethod.GET, params = "json")
    public void getRec(Integer start, Integer size, HttpServletResponse response) throws IOException {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Vehicle.class);
        start = (start == null) ? 0 : start;
        size = (size == null) ? Configurator.getInstance().getInt("defaultPageSize") : size;
        List<Vehicle> vehicles = vehicleService.get(detachedCriteria, start, size);
        JSONObject json = new JSONObject();
        json.put("vehiclesLength", vehicleService.getAll().size());
        JsonUtil.write(vehicles, "vehicles", json, response);
    }

    /**
     * <p>获取单条车辆记录</p>
     * <p>GET    /vehicle/{vehicleId}</p>
     *
     * @param vehicleId 需要获取的车辆记录的ID
     * @throws IOException 传数据给前端时可能会抛出的异常
     */
    @RequestMapping(value = "{vehicleId}", method = RequestMethod.GET)
    public void getJsonRec(@PathVariable("vehicleId") int vehicleId, HttpServletResponse response) throws IOException {
        JsonUtil.write(vehicleService.get(vehicleId), response);
    }

    /**
     * <p>修改车辆记录</p>
     * <p>PUT    /vehicle?json</p>
     *
     * @param vehicle 修改后的车辆记录
     * @throws IOException 传数据给前端时可能会抛出的异常
     */
    @RequestMapping(method = RequestMethod.PUT, params = "json")
    public void updateRec(Vehicle vehicle, Integer driverId, Integer vehicleTypeId, HttpServletResponse response) throws IOException {
        System.out.println("VehicleId = " + vehicleTypeId);
        vehicleService.update(vehicle, driverId, vehicleTypeId);
        JsonUtil.writeMsg("1", response);
    }
}
