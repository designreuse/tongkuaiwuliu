package com.logistics.controller;

import com.alibaba.fastjson.JSONObject;
import com.logistics.conf.Configurator;
import com.logistics.entity.VehicleType;
import com.logistics.service.VehicleTypeService;
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
@RequestMapping("vehicleType")
public class VehicleTypeController {
    @Resource
    VehicleTypeService vehicleTypeService;

    /**
     * <p>增加车型记录</p>
     * <p>POST    /vehicleType</p>
     *
     * @param vehicleType 需要添加的车型记录
     * @throws java.io.IOException 返回JSON数据给前端时可能会抛出这个异常
     */
    @RequestMapping(method = RequestMethod.POST)
    public void addRec(VehicleType vehicleType, HttpServletResponse response) throws IOException {
        Integer id = vehicleTypeService.add(vehicleType);
        if (id != null) {
            JsonUtil.writeMsg(id.toString(), response);
        } else {
            JsonUtil.writeMsg("-1", response);
        }
    }

    /**
     * <p>批量删除车型记录</p>
     * <p>DELETE    /vehicleType?many</p>
     *
     * @param req 需要在里面添加一个id数组
     * @throws IOException 返回JSON数据给前端时可能会抛出这个异常
     */
    @RequestMapping(method = RequestMethod.DELETE, params = "many")
    public void vehicleType_DELETE_MANY(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Long> ids = needLongList(req, "ids");
        for (long id : ids) {
            vehicleTypeService.delete((int) id);
        }
        JsonUtil.writeMsg("1", resp);
    }

    /**
     * <p>删除单条车型记录</p>
     * <p>DELETE    /vehicleType?json</p>
     *
     * @param vehicleTypeId 需要删除的记录的id
     * @throws IOException 返回JSON数据给前端时可能会抛出这个异常
     */
    @RequestMapping(method = RequestMethod.DELETE, params = "json")
    public void vehicleType_DELETE_json(Integer vehicleTypeId, HttpServletResponse resp) throws IOException {
        vehicleTypeService.delete(vehicleTypeId);
        JsonUtil.writeMsg("1", resp);
    }

    /**
     * <p>获取车型记录</p>
     * <p>GET    /vehicleType?json</p>
     *
     * @param start 从该条记录开始显示
     * @param size  页面显示的记录条数
     * @throws java.io.IOException 返回JSON数据给前端时可能会抛出这个异常
     */
    @RequestMapping(method = RequestMethod.GET, params = "json")
    public void getJsonRecs(Integer start, Integer size, HttpServletResponse response) throws IOException {
        start = start != null ? start : 0;
        size = size != null ? size : Configurator.getInstance().getInt("defaultPageSize");
        List<VehicleType> vehicleTypes = vehicleTypeService.get(start, size);
        JSONObject json = new JSONObject();
        json.put("vehicleTypesLength", vehicleTypeService.getAll().size());
        JsonUtil.write(vehicleTypes, "vehicleTypes", json, response);
    }

    /**
     * <p>获取单个车型记录</p>
     * <p>GET    /vehicleType/{vehicleTypeId}</p>
     *
     * @param vehicleTypeId 在路径中指定的车型记录的ID
     * @throws java.io.IOException 返回JSON数据给前端时可能会抛出这个异常
     */
    @RequestMapping(value = "{vehicleTypeId}", method = RequestMethod.GET)
    public void getJsonRec(@PathVariable("vehicleTypeId") int vehicleTypeId, HttpServletResponse response) throws IOException {
        JsonUtil.write(vehicleTypeService.get(vehicleTypeId), response);
    }

    /**
     * <p>修改车型信息</p>
     * <p>PUT    /vehicleType?json</p>
     *
     * @param vehicleType 修改后的车型记录
     */
    @RequestMapping(method = RequestMethod.PUT, params = "json")
    public void updateRec(VehicleType vehicleType, HttpServletResponse response) throws IOException {
        vehicleTypeService.update(vehicleType);
        JsonUtil.writeMsg("1", response);
    }
}
