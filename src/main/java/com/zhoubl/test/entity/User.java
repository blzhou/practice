package com.zhoubl.test.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @version 1.0V
 * @Author zhoubl
 * @Date 2019/1/25 11:57
 * @Description TODO(描述该类做什么)
 */
public class User implements Serializable {

    private static final long serialVersionUID = -4894146918387749396L;

    private String userName;

    private String nickName;

    private Integer age;

    private Date birthday;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return userName.equals(user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}
