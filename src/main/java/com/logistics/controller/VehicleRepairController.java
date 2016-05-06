package com.logistics.controller;

import com.logistics.conf.Configurator;
import com.logistics.entity.VehicleRepair;
import com.logistics.service.VehicleRepairService;
import com.logistics.util.JsonUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Controller;
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
@RequestMapping("vehicleRepair")
public class VehicleRepairController {
    @Resource
    private VehicleRepairService vehicleRepairService;

    /**
     * <p>添加汽车维修记录</p>
     * <p>POST    /vehicleRepair?json</p>
     *
     * @param vehicleRepair 需要添加的单车维修记录
     * @throws IOException 传数据给前端时可能会抛出的异常
     */
    @RequestMapping(method = RequestMethod.POST, params = "json")
    public void add(VehicleRepair vehicleRepair, HttpServletResponse response) throws IOException {
        Integer id = vehicleRepairService.add(vehicleRepair);
        if (id != null) {
            JsonUtil.writeMsg(id.toString(), response);
        } else {
            JsonUtil.writeMsg("-1", response);
        }
    }

    /**
     * <p>删除汽车维修记录</p>
     * <p>DELETE    /vehicleRepair</p>
     *
     * @param request 需要在该参数中添加一个类型为int[]名为idArray的属性，
     *                方法会取出所有在idArray里面的数据并以此作为id删除相应的记录
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(HttpServletRequest request) {
        int[] idArray = (int[]) request.getAttribute("idArray");
        for (int id : idArray) {
            vehicleRepairService.delete(id);
        }
    }

    /**
     * <p>查询汽车维修记录</p>
     * <p>GET    /vehicleRepair?json</p>
     *
     * @param start 从该条记录开始显示
     * @param size  页面显示的数据条数
     * @throws IOException 传数据给前端时可能会抛出的异常
     */
    @RequestMapping(method = RequestMethod.GET, params = "json")
    public void get(Integer start, Integer size, HttpServletResponse response) throws IOException {
        start = (start != null) ? start : 0;
        size = (size != null) ? size : Configurator.getInstance().getInt("defaultPageSize");
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(VehicleRepair.class);
        List<VehicleRepair> vehicleRepairList = vehicleRepairService.get(detachedCriteria, start, size);
        JsonUtil.write(vehicleRepairList, response);
    }

    /**
     * <p>修改汽车维修记录</p>
     * <p>PUT    /vehicleRepair</p>
     *
     * @param vehicleRepair 修改后的汽车维修记录
     */
    @RequestMapping(method = RequestMethod.PUT)
    public void update(VehicleRepair vehicleRepair) {
        vehicleRepairService.update(vehicleRepair);
    }
}
