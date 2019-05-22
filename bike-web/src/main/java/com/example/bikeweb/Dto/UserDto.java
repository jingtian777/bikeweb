package com.example.bikeweb.Dto;

import com.example.bikeweb.entity.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    public UserDto(){}
    public UserDto(User user){
        id = user.getId();
        phone = user.getPhone();
        password = user.getPassword();
        name = user.getName();
        portrait = user.getPortrait();
        money = user.getMoney();
        email = user.getEmail();
        age = user.getAge();
        gender = user.getGender()==null?null:user.getGender().ordinal();
        permission = user.getPermission()==null?null:user.getPermission().ordinal();
    }
    private Long id;
    private String phone;
    private String password;
    private String name;
    private String portrait;
    private Double money;
    private String email;
    private Integer age;
    private Integer gender;
    private Integer permission;

    // 7天内的骑车次数
    private Integer usednum;
    // 7天内的骑车花费
    private Double costsum;

    public static UserDto convert(User user){
        if(user == null)
            return null;
        return new UserDto(user);
    }
}
