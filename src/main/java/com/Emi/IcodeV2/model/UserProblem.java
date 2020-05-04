package com.Emi.IcodeV2.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@IdClass(UserProblemId.class)
public class UserProblem {

    @Id
    private String username;
    @Id
    private Integer idp;

    private String Status;

    public UserProblem(){}
    public UserProblem(String username, Integer idp, String status) {
        this.username = username;
        this.idp = idp;
        Status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIdp() {
        return idp;
    }

    public void setIdp(Integer idp) {
        this.idp = idp;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "UserProblem{" +
                "username='" + username + '\'' +
                ", idp=" + idp +
                ", Status='" + Status + '\'' +
                '}';
    }
}


// darori if you have a more than one column id
