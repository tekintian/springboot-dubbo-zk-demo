package cn.tekin.demo.dubbo.service;

import java.util.ArrayList;
import java.util.List;

import cn.tekin.demo.dubbo.api.pojo.User;
import cn.tekin.demo.dubbo.api.service.UserService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

/**
 * @author tekintian@gmail.com
 * @version v0.0.1
 * @since v0.0.1 2022-10-23 12:32
 */
@Service(version = "${dubbo.service.version}") //声明这是一个dubbo服务
public class UserServiceImpl implements UserService {

    // 获取当前运行端口
    @Value("${server.port}")
    private String port;

    @Value("${spring.application.name}")
    private String appName;

    /**
     * 实现查询，这里做模拟实现，不做具体的数据库查询
     */
    @Override
    public List<User> queryAll() {
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setAge(10 + i);
            user.setId(Long.valueOf(i + 1));
            user.setPassword("123456");
            user.setUsername("username_" + i);
            list.add(user);
        }

        System.out.println(String.format("Dubbo Provider %s:%s",this.appName,this.port));
        return list;
    }
}