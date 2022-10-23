package cn.tekin.demo.dubbo.api.service;

import cn.tekin.demo.dubbo.api.pojo.User;

import java.util.List;

/**
 * @author tekintian@gmail.com
 * @version v0.0.1
 * @since v0.0.1 2022-10-23 19:07
 */
public interface UserService {
    /**
     * 查询所有的用户数据
     *
     * @return
     */
    List<User> queryAll();
}
