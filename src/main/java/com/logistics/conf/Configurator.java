package com.logistics.conf;

/**
 * Created by Mklaus on 15/7/17.
 */

import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 从 /logistics.properties 读取参数并供应给应用程序
 */
public class Configurator
{
    // CONSTRUCT
    private Properties prop;

    private Configurator()
    {
        this.load();

        return;
    }

    // SINGLETON
    private static class Singleton
    {
        public static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance()
    {
        return(Singleton.INSTANCE);
    }

    /** 加载配置文件
     */
    private void load()
    {
        try
        {
            InputStreamReader is;

            is = new InputStreamReader(
                    this.getClass()
                            .getResourceAsStream("/logisticsSystem.properties"),
                    "utf-8"
            );

            this.prop = new Properties();
            this.prop.load(is);
            is.close();
        }
        catch (Exception ex)
        {
            throw(new RuntimeException(ex));
        }
    }

    /** 重载配置文件
     */
    public void reload()
    {
        this.load();
    }

    /** 获得整个属性库的只读副本
     * 本质上是将原有 prop 作为默认表创建新表并返回.
     * 因此, 对得到的表作出的修改不会影响到原表.
     */
    public Properties getProperties()
    {
        Properties prop = new Properties(this.prop);

        return(prop);
    }


    /**
     * 提取配置文件中的参数
     */
    public String get(String name)
    {
        return(
                this.prop.getProperty(name)
        );
    }

    public String get(String name, String defaultValue)
    {
        String v = this.prop.getProperty(name);

        return(
                v!=null ? v : null
        );
    }


    public Integer getInt(String name)
    {
        try
        {
            String v = this.get(name);
            return(
                    v!=null ? Integer.valueOf(v) : null
            );
        }
        catch (Exception ex)
        {
            throw(new RuntimeException(ex));
        }
    }

    public Integer getInt(String name, Integer defaultValue)
    {
        Integer v = this.getInt(name);
        return(
                v!=null ? v : defaultValue
        );
    }

    public Long getLong(String name)
    {
        try
        {
            String v = this.get(name);
            return(
                    v!=null ? Long.valueOf(v) : null
            );
        }
        catch (Exception ex)
        {
            throw(new RuntimeException(ex));
        }
    }

    public Long getLong(String name, Long defaultValue)
    {
        Long v = this.getLong(name);
        return(
                v!=null ? v : defaultValue
        );
    }

    public Double getDouble(String name)
    {
        try
        {
            String v = this.get(name);
            return(
                    v!=null ? Double.valueOf(v) : null
            );
        }
        catch (Exception ex)
        {
            throw(new RuntimeException(ex));
        }
    }

    public Double getDouble(String name, Double defaultValue)
    {
        Double v = this.getDouble(name);
        return(
                v!=null ? v : defaultValue
        );
    }
}

