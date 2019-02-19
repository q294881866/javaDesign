package javaSe.designPattern.builder;

/**
 * @author peipengfei
 * @date 2019/2/19
 */
public class UserBuilder extends User {

    public UserBuilder(Build build) {
        this.userName = build.userName;
        this.password = build.password;
        this.gender = build.gender;
        this.email = build.email;
        this.tel = build.tel;
    }

    public static class Build {
        private String userName;
        private String password;
        private Boolean gender;
        private String email;
        private Integer tel;

        public Build(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        public Build setGender(Boolean gender) {
            this.gender = gender;
            return this;
        }

        public Build setEmail(String email) {
            this.email = email;
            return this;
        }

        public Build setTel(Integer tel) {
            this.tel = tel;
            return this;
        }

        public User build() {
            return new UserBuilder(this);
        }
    }
}
