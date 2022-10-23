package cn.tekin.demo.dubbo;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Springboot APP Entry
 * @author tekintian@gmail.com
 * @version v0.0.1
 * @since v0.0.1 2022-10-23 12:33
 */
@SpringBootApplication
public class DubboProvider {
    public static void main(String[] args) {
        // .web(WebApplicationType.NONE)  表示当前应用为非web应用
        new SpringApplicationBuilder(DubboProvider.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}