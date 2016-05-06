package com.logistics.conf;

import com.logistics.crypto.CryptoBase;
import com.logistics.entity.Admin;
import com.logistics.service.AdminService;
import com.logistics.service.Impl.AdminServiceImpl;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * Created by Mklaus on 15/9/3.
 */

public class AdminAdd {



    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-common.xml");
        SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
        Session session = sessionFactory.openSession();

        System.out.println("Admin Toolkit - AdminAdd");
        System.out.println("WARNING: This tool is used to create admin id, WITHOUT any privilege check.");

        AdminService adminService = new AdminServiceImpl();

        System.out.println("Add Admin...");

        String username = args[0];
        System.out.println("Using username:"+username);
        String password = args[1];
        System.out.println("Using password:"+password);


        try {
            session.beginTransaction();

            if (isExists(username, session)){
                throw new Exception("username already exists : " + username);
            }

            register(new Admin(username, password.getBytes("UTF-8"), 1), session);

            session.getTransaction().commit();

        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Operation failed.\nUSAGE: AdminAdd username password");
            System.out.println("And check if:\n* username is unique\n* write access to database\n* your OS/JRE supports UTF-8");
        }

        System.out.println("create success.");
    }


    public static boolean isExists(String adminName,Session session) {
        DetachedCriteria dc = DetachedCriteria.forClass(Admin.class);
        dc.add(Restrictions.eq("adminName", adminName));
        List<Admin> list = dc.getExecutableCriteria(session).list();
        if (list.size() > 0){
            return true;
        }
        return false;
    }

    public static void register(Admin admin,Session session){
        CryptoBase cb = CryptoBase.getInstance();

        byte[] salt = cb.randomBytes(Configurator.getInstance().getInt("logistics.admin.saltlength", 4));
        admin.setSalt(salt);

        byte[] buf = ByteBuffer.allocate(admin.getPassword().length + salt.length)
                .put(admin.getPassword())
                .put(salt)
                .array();
        admin.setPassword(cb.MD5Digest(buf));
        session.save(admin);
    }
}
