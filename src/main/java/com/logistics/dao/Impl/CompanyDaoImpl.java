package com.logistics.dao.Impl;

import com.logistics.dao.CompanyDao;
import com.logistics.entity.Company;
import org.springframework.stereotype.Repository;

/**
 * Created by Ranger on 2015/8/13.
 */
@Repository("companyDaoImpl")
public class CompanyDaoImpl extends BaseDaoImpl<Company> implements CompanyDao {
    @Override
    public Class classOfT() {
        return Company.class;
    }
}
