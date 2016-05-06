package com.logistics.controller;

import com.logistics.conf.Configurator;
import com.logistics.entity.CarrierBusiness;
import com.logistics.service.CarrierBusinessService;
import com.logistics.util.JsonUtil;
import com.logistics.util.Params;
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

/**
 * Created by Ranger on 2015/8/26.
 */
@Controller
@RequestMapping("carrierBusiness")
public class CarrierBusinessController {
    @Resource
    private CarrierBusinessService carrierBusinessService;

    /**
     * <p>添加承运商业务记录</p>
     * <p>POST    /carrierBusiness?json</p>
     *
     * @param carrierBusiness 需要保存的承运商业务记录
     * @throws IOException 传数据给前端时可能会抛出的异常
     */
    @RequestMapping(method = RequestMethod.POST, params = "json")
    public void add(CarrierBusiness carrierBusiness, Integer transTaskId, Integer companyId,
                    HttpServletResponse response) throws IOException {
        Integer id = carrierBusinessService.add(carrierBusiness, transTaskId, companyId);
        if (id != null) {
            JsonUtil.writeMsg(id.toString(), response);
        } else {
            JsonUtil.writeMsg("-1", response);
        }
    }

    /**
     * <p>删除单条运输记录</p>
     * <p>DELETE    /carrierBusiness?single</p>
     *
     * @param id 需要删除的记录的ID
     * @throws IOException 传数据给前端时可能会抛出的异常
     */
    @RequestMapping(method = RequestMethod.DELETE, params = "single")
    public void deleteRec(Integer id, HttpServletResponse response) throws IOException {
        carrierBusinessService.delete(id);
        JsonUtil.writeMsg("1", response);
    }

    /**
     * <p>批量删除承运商业务记录</p>
     * <p>DELETE    /carrierBusiness?batch</p>
     *
     * @param request 需要在里面添加一个类型为int[]名称为idArray的参数
     * @throws IOException 传数据给前端时可能会抛出的异常
     */
    @RequestMapping(method = RequestMethod.DELETE, params = "batch")
    public void deleteRecs(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int[] idArray = Params.getIntArray(request, "idArray");
        if (idArray == null) {
            JsonUtil.writeMsg("-1", response);
            return;
        }
        for (int id : idArray) {
            carrierBusinessService.delete(id);
        }
        JsonUtil.writeMsg("1", response);
    }

    /**
     * <p>查询单条承运商信息记录</p>
     * <p>GET    /carrierBusinessId/{carrierBusinessId}?json</p>
     *
     * @param carrierBusinessId 需要查找的承运商业务记录的ID
     * @throws IOException 传数据给前端时可能会抛出的异常
     */
    @RequestMapping(value = "{carrierBusinessId}", method = RequestMethod.GET, params = "json")
    public void getRec(@PathVariable("carrierBusinessId") Integer carrierBusinessId, HttpServletResponse response) throws IOException {
        JsonUtil.write(carrierBusinessService.get(carrierBusinessId), response);
    }

    /**
     * <p>查询承运商信息记录</p>
     * <p>GET    /carrierBusiness?json</p>
     *
     * @param start 从该条记录开始显示
     * @param size  页面显示记录的条数
     * @throws IOException 传数据给前端时可能会抛出的异常
     */
    @RequestMapping(method = RequestMethod.GET, params = "json")
    public void getRecs(Integer start, Integer size, HttpServletResponse response) throws IOException {
        start = (start != null) ? start : 0;
        size = (size != null) ? size : Configurator.getInstance().getInt("defaultPageSize");
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CarrierBusiness.class);
        List<CarrierBusiness> carrierBusinessList = carrierBusinessService.get(detachedCriteria, start, size);
        JsonUtil.write(carrierBusinessList, response);
    }

    /**
     * <p>修改承运商业务记录</p>
     * <p>PUT    /carrierBusiness?json</p>
     *
     * @param carrierBusiness 修改后的承运商记录
     * @throws IOException 传数据给前端时可能会抛出的异常
     */
    @RequestMapping(method = RequestMethod.PUT, params = "json")
    public void update(CarrierBusiness carrierBusiness, Integer transTaskId, Integer companyId,
                       HttpServletResponse response) throws IOException {
        carrierBusinessService.update(carrierBusiness, transTaskId, companyId);
        JsonUtil.writeMsg("1", response);
    }

    /**
     * <p>根据任务单ID获取承运商业务记录</p>
     * <p>GET    /carrierBusiness/{taskId}?byTaskId</p>
     *
     * @param taskId 任务单的ID
     */
    @RequestMapping(value = "{taskId}", method = RequestMethod.GET, params = "byTaskId")
    public void getCarrierBusinessByTaskId(@PathVariable("taskId") int taskId, HttpServletResponse response) throws IOException {
        JsonUtil.write(carrierBusinessService.getCarrierBusinessByTaskId(taskId), response);
    }
}
