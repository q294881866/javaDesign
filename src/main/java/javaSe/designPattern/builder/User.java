package javaSe.designPattern.builder;

import lombok.Data;

/**
 * @author peipengfei
 * @date 2019/2/19
 */
@Data
public class User {
    protected String userName;
    protected String password;
    protected Boolean gender;
    protected String  email;
    protected Long tel;
}
