package com.logistics.controller;

import com.alibaba.fastjson.JSONObject;
import com.logistics.conf.Configurator;
import com.logistics.entity.Cargo;
import com.logistics.service.CargoService;
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
@RequestMapping("cargo")
public class CargoController {
    @Resource
    private CargoService cargoService;

    /**
     * <p>添加货物信息</p>
     * <p>POST    /cargo</p>
     *
     * @param cargo  需要添加的货物记录
     * @param taskId 与货物相关联的运输任务的ID
     * @throws IOException 传数据给前端时可能会抛出这个异常
     */
    @RequestMapping(method = RequestMethod.POST)
    public void add(Cargo cargo, Integer taskId, HttpServletResponse response) throws IOException {
        Integer id = cargoService.add(cargo, taskId);
        if (id != null) {
            JsonUtil.writeMsg(id.toString(), response);
        } else {
            JsonUtil.writeMsg("-1", response);
        }
    }

    /**
     * <p>删除货物信息</p>
     * <p>DELETE    /cargo</p>
     *
     * @param request 需要在该参数中添加一个类型为int[]名为idArray的属性，
     *                方法会取出所有在idArray里面的数据并以此作为id删除相应的记录
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(HttpServletRequest request) {
        int[] idArray = (int[]) request.getAttribute("idArray");
        for (int id : idArray) {
            cargoService.delete(id);
        }
    }

    /**
     * <p>查询货物记录</p>
     * <p>GET    /cargo</p>
     *
     * @param start 从该条记录开始显示
     * @param size  页面显示的记录条数
     * @throws IOException 传数据给前端时可能会抛出这个异常
     */
    @RequestMapping(method = RequestMethod.GET, params = "json")
    public void get(Integer start, Integer size, HttpServletResponse response) throws IOException {
        start = (start != null) ? start : 0;
        size = (size != null) ? size : Configurator.getInstance().getInt("defaultPageSize");
        List<Cargo> cargoList = cargoService.get(start, size);

        JSONObject json = new JSONObject();
        json.put("cargoListLength", cargoService.getAll().size());
        JsonUtil.write(cargoList, "cargoList", json, response);
    }

    /**
     * <p>查询单个货物记录</p>
     * <p>GET    /cargo/{cargoId}</p>
     *
     * @param cargoId 需要查询的记录的ID
     * @throws IOException 传数据给前端时可能会抛出这个异常
     */
    @RequestMapping(value = "{cargoId}", method = RequestMethod.GET, params = "json")
    public void get(@PathVariable("cargoId") Integer cargoId, HttpServletResponse response) throws IOException {
        JsonUtil.write(cargoService.get(cargoId), response);
    }

    /**
     * <p>修改货物记录</p>
     * <p>PUT    /cargo</p>
     *
     * @param cargo  修改后的货物信息
     * @param taskId 与货物相关联的运输任务的ID
     */
    @RequestMapping(method = RequestMethod.PUT)
    public void update(Cargo cargo, Integer taskId) {
        cargoService.update(cargo, taskId);
    }

    /**
     * 根据taskID取得记录
     *
     * @param taskId
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "{taskId}", method = RequestMethod.GET, params = "taskId")
    public void getCargoByTaskId(@PathVariable("taskId") int taskId, HttpServletResponse response) throws IOException {
        JSONObject json = new JSONObject();
        json.put("cargoListLength", cargoService.getAll().size());
        JsonUtil.write(cargoService.getCargoByTaskId(taskId), "cargoList", json, response);
    }
}
