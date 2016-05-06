package com.logistics.controller;

import com.alibaba.fastjson.JSONObject;
import com.logistics.conf.Configurator;
import com.logistics.entity.Address;
import com.logistics.service.AddressService;
import com.logistics.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.logistics.util.Params.needLongList;

/**
 * Created by Mklaus on 15/8/24.
 */
@Controller
@RequestMapping("address")
public class AddressController {
    @Resource
    private AddressService addressService;

    @RequestMapping(method = RequestMethod.GET, params = "json")
    public void address_GET_json_hql(Integer start, Integer size, HttpServletResponse resp) throws IOException {
        start = start != null ? start : 0;
        size = size != null ? size : Configurator.getInstance().getInt("defaultPageSize");
        List<Address> l = addressService.get(start, size);
        JSONObject json = new JSONObject();
        json.put("addressLength", addressService.getAll().size());
        JsonUtil.write(l, "addresses", json, resp);
    }

    @RequestMapping(method = RequestMethod.GET, params = "level")
    public void address_GET_json_byLevel(int level, HttpServletResponse resp) throws IOException {
        List<Address> l = addressService.getByLevel(level);
        JsonUtil.write(l, resp);
    }

    @RequestMapping(method = RequestMethod.POST, params = "json")
    public void address_CREATE_json(Address address, Integer parentId, HttpServletResponse resp) throws IOException {
        Integer id = addressService.add(address, parentId);
        JsonUtil.write(id.toString(), resp);
    }

    @RequestMapping(method = RequestMethod.DELETE, params = "json")
    public void address_DELETE_json(Integer addressId, HttpServletResponse resp) throws IOException {
        addressService.delete(addressId);
        JsonUtil.write("1", resp);
    }

    @RequestMapping(method = RequestMethod.DELETE, params = "many")
    public void address_DELETE_json_many(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Long> ids = needLongList(req, "ids");
        for (long id : ids) {
            addressService.delete((int) id);
        }
        JsonUtil.writeMsg("1", resp);
    }

    @RequestMapping(method = RequestMethod.PUT, params = "json")
    public void address_UPDATE_json(Address address, HttpServletResponse resp) throws IOException {
        addressService.update(address);
        JsonUtil.write("1", resp);
    }
}
