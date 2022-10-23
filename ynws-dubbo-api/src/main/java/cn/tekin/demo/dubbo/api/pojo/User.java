package cn.tekin.demo.dubbo.api.pojo;

/**
 * @author tekintian@gmail.com
 * @version v0.0.1
 * @since v0.0.1 2022-10-23 12:31
 */
// 使用dubbo要求传输的对象必须实现序列化接口
public class User implements java.io.Serializable {
    private static final long serialVersionUID = -7341603933521593227L;
    private Long id;
    private String username;
    private String password;
    private Integer age;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }
}