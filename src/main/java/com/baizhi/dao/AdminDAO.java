package com.baizhi.dao;

import com.baizhi.entity.Admin;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/7/29.
 */
@Component
public interface AdminDAO {
    Admin selectByNameAndPassword(Admin admin);
}
