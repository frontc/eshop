package cn.lefer.eshop.user;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;

@Data
public class User implements Serializable {
    private Long userID;
    private String userName;
    private String password;
    private Boolean userStatus;
    private String salt;
    private HashSet<String> roles = new HashSet<>();
    private HashSet<String> permissions = new HashSet<>();
}
