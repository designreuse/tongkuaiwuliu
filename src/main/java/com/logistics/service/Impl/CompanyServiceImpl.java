package com.logistics.service.Impl;

import com.logistics.dao.BaseDao;
import com.logistics.dao.CompanyDao;
import com.logistics.entity.Company;
import com.logistics.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Ranger on 2015/8/14.
 */
@Repository
public class CompanyServiceImpl extends BaseServiceImpl<Company> implements CompanyService {
    @Autowired
    private CompanyDao companyDao;

    @Override
    public BaseDao getBaseDao() {
        return companyDao;
    }
}
