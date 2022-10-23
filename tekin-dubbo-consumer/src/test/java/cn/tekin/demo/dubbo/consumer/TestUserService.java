//测试类，注意这里的包名称需要和 src/main/java里面启动类的包名称一致，否则@Reference对象无法注入
package cn.tekin.demo.dubbo.consumer;

import cn.tekin.demo.dubbo.api.service.UserService;
import cn.tekin.demo.dubbo.api.pojo.User;
import com.alibaba.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserService {
    // Dubbo注入， 注意本类的包名需要和Springboot启动类里面的一致，否则无法注入
    // 参数说明 version 这个是自定义的，用于consumer端多版本调用场景, loadbalance负载均衡模式 random/roundrobin
    @Reference(version = "1.0.0", loadbalance = "roundrobin")
    private UserService userService;

    /**
     * dubbo Consumer消费测试
     */
    @Test
    public void testQueryAll(){
        for (int i = 0; i <100 ; i++) {

            try {
                List<User> users = this.userService.queryAll();
                for (User user : users) {
                    System.out.println(user.toString());
                }

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}