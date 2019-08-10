package com.baizhi.dao;

import com.baizhi.entity.User;
import com.baizhi.entity.UserCount;
import com.baizhi.entity.UserMap;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2019/8/1.
 */
@Component
public interface UserDAO extends BaseDAO<User> {

    User selectOne(String id);
    List<User> select();
    List<UserCount> total();
    List<UserMap> map();
}
