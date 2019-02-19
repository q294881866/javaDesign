package javaSe.designPattern.builder;

/**
 * @author peipengfei
 * @date 2019/2/19
 */
public final class Users {

    private Users() {
    }

    public static User register(String userName, String password) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        return user;
    }

    public static User registerByMail(String userName, String password, String email) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setEmail(email);

        return user;
    }
}
