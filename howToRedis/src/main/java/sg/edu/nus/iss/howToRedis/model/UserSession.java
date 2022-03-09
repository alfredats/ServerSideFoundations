package sg.edu.nus.iss.howToRedis.model;

import java.util.Date;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Session")
public class UserSession {
    private String id;
    private String username;
    private Date loginTime;
    private String browser;

    public UserSession(
        String id,
        String username,
        Date loginTime,
        String browser
    ) {
        this.setId(id);
        this.setUsername(username);
        this.setLoginTime(loginTime);
        this.setBrowser(browser);
    }

    @Override
    public String toString() {
        return  "Session {" +
                "id='" + id + '\'' +
                ", username='" + username + "\' }";
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
