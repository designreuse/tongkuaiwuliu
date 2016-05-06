package com.logistics.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.logistics.entity.BasicEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * Created by Mklaus on 15/7/25.
 */
public class JsonUtil {

    public static void writeMsg(String msg, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();

        out.print(msg);

        out.flush();
        out.close();
    }

    public static void write(JSONObject json, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();

        out.print(json);

        out.flush();
        out.close();
    }

    public static void write(Object o, HttpServletResponse resp) throws IOException {
        write(o, null, null, resp);
    }


    public static void write(Object o, String oname, JSONObject json, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();

        JSONArray dataArray;
        json = json != null ? json : new JSONObject();

        String jsonString = "";

        try {

            if (o instanceof Collection) {
                dataArray = jsonizeCollect((Collection) o);
                if (oname != null) {
                    json.put(oname, dataArray);
                    jsonString = json.toJSONString();
                } else {
                    jsonString = dataArray.toJSONString();
                }
            } else if (o instanceof BasicEntity) {
                json = jsonize((BasicEntity) o, json);
                if (oname != null) {
                    JSONObject result = new JSONObject();
                    jsonString = result.put(oname, json).toString();
                } else {
                    jsonString = json.toJSONString();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        out.print(jsonString);

        out.flush();
        out.close();
    }

    /**
     * 序列化类集
     *
     * @param
     * @return
     */
    public static JSONArray jsonizeCollect(Collection c) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        JSONArray jsonArray = new JSONArray();

        for (Object o : c) {
            jsonArray.add(jsonize((BasicEntity) o, null));
        }

        return jsonArray;
    }


    /**
     * 利用反射获取类的所有属性，并转化成Json
     */
    public static JSONObject jsonize(BasicEntity object, JSONObject add) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        JSONObject json = (add != null) ? add : new JSONObject();

        // 父类属性需要自己添加
        json.put("id", object.getId());
        json.put("is_deleted", object.isDeleted());
        json.put("create_time", object.getCreated_time());
        json.put("modify_time", object.getModify_time());

        // 获取实体类的所有属性，返回Field数组
        Field[] field = object.getClass().getDeclaredFields();

        // 遍历所有属性
        for (int j = 0; j < field.length; j++) {

            //获取属性的名字
            String name = field[j].getName();
            //获取属性的类型
            String type = field[j].getGenericType().toString();
            //首字母大写 结合getter,setter使用
            String Mname = name.substring(0, 1).toUpperCase() + name.substring(1);


            /**
             * 指定项目包中的类时，只需put id,避免循环引用
             *
             * type example
             * 类type   : class com.klaus.ohMySpace.entity.Note
             * 类集type : java.util.Set<com.klaus.ohMySpace.entity.Note>
             */
            if (type.contains("com.logistics.entity")) {
                if (type.startsWith("java.util")) {
                    Method m = object.getClass().getMethod("get" + Mname);
                    JSONArray ids = new JSONArray();
                    if (((Collection) (m.invoke(object))).size() > 0) {
                        for (BasicEntity be : (Collection<BasicEntity>) (m.invoke(object))) {
                            if (type.contains("GoodsGroup")) {
                                ids.add(jsonize(be, null));
                            } else {
                                ids.add(be.getId());
                            }
                        }
                        json.put(name, ids);
                    }
                } else {
                    Method m = object.getClass().getMethod("get" + Mname);
                    if (m.invoke(object) != null) {
                        int id = ((BasicEntity) (m.invoke(object))).getId();
                        json.put(name, id);
                    }
                }
            } else {
                Method m = object.getClass().getMethod("get" + Mname);
                String value = (m.invoke(object) != null) ? m.invoke(object).toString() : "";    //调用getter方法获取属性值
                json.put(name, value);
            }
        }

        return json;
    }
}
