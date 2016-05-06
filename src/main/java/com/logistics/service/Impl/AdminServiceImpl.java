package com.logistics.service.Impl;

import com.logistics.conf.Configurator;
import com.logistics.crypto.CryptoBase;
import com.logistics.crypto.RSACrypto;
import com.logistics.crypto.RSAKeyCache;
import com.logistics.dao.AdminDao;
import com.logistics.dao.BaseDao;
import com.logistics.entity.Admin;
import com.logistics.service.AdminService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.PrivateKey;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mklaus on 15/7/23.
 */
@Service("adminServiceImpl")
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService{

    @Autowired
    private AdminDao adminDao;

    @Override
    public BaseDao getBaseDao() {
        return adminDao;
    }

    @Override
    public Admin login(Admin admin) {

        int id = getIdByAdminName(admin.getAdminName());

        PrivateKey key = RSAKeyCache.getInstance().get((long) id);
        byte[] password = RSACrypto.getInstance().decrypt(admin.getPassword(),key);

        admin.setPassword(password);

        Admin login = null;
        if ((login = verifyPassword(admin)) != null){
            return login;
        }
        return null;
    }

    public Admin verifyPassword(Admin admin){
        CryptoBase cb = CryptoBase.getInstance();

        Admin login = null;
        DetachedCriteria dc = DetachedCriteria.forClass(Admin.class);
        dc.add(Restrictions.eq("adminName",admin.getAdminName()));
        List<Admin> list = getBaseDao().search(dc);
        if (list.size() > 0){
            login = list.get(0);

            byte[] salt = login.getSalt();
            byte[] buf = ByteBuffer.allocate(admin.getPassword().length + salt.length).put(admin.getPassword()).put(salt).array();

            System.out.println(new String(cb.MD5Digest(buf)));
            System.out.println(new String(login.getPassword()));

            if(Arrays.equals(cb.MD5Digest(buf),login.getPassword())){
                return login;
            }
        }
        return null;
    }

    @Override
    public boolean passwd(Admin admin, String oldPass, String newPass) throws IOException{
        admin.setPassword(oldPass.getBytes("UTF-8"));
        if ((admin = this.verifyPassword(admin)) != null){
            CryptoBase cb = CryptoBase.getInstance();
            byte[] salt = cb.randomBytes(Configurator.getInstance().getInt("logistics.admin.saltlength", 4));
            admin.setSalt(salt);
            admin.setPassword(newPass.getBytes("UTF-8"));

            byte[] buf = ByteBuffer.allocate(admin.getPassword().length + salt.length)
                    .put(admin.getPassword())
                    .put(salt)
                    .array();
            admin.setPassword(cb.MD5Digest(buf));

            this.adminDao.update(admin);

            return true;
        }
        return false;
    }

    @Override
    public boolean isExists(String adminName) {
        DetachedCriteria dc = DetachedCriteria.forClass(Admin.class);
        dc.add(Restrictions.eq("adminName", adminName));
        List<Admin> list = getBaseDao().search(dc);
        if (list.size() > 0){
            return true;
        }
        return false;
    }

    @Override
    public int getIdByAdminName(String adminName) {
        DetachedCriteria dc = DetachedCriteria.forClass(Admin.class);
        dc.add(Restrictions.eq("adminName", adminName));
        List<Admin> list = getBaseDao().search(dc);
        if (list.size() > 0){
            return list.get(0).getId();
        }
        return -1;
    }

    @Override
    public Admin register(Admin admin) {
        CryptoBase cb = CryptoBase.getInstance();

        byte[] salt = cb.randomBytes(Configurator.getInstance().getInt("logistics.admin.saltlength", 4));
        admin.setSalt(salt);

        byte[] buf = ByteBuffer.allocate(admin.getPassword().length + salt.length)
                .put(admin.getPassword())
                .put(salt)
                .array();
        admin.setPassword(cb.MD5Digest(buf));

        adminDao.save(admin);

        return  admin;
    }
}
