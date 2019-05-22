package com.example.bikeweb.entity;

import com.example.bikeweb.Dto.UserDto;
import lombok.Data;

@Data
public class User extends Base{
    public User(){}
    public User(UserDto userDto){
        id = userDto.getId();
        phone = userDto.getPhone();
        password = userDto.getPassword();
        name = userDto.getName();
        portrait = userDto.getPortrait();
        money = userDto.getMoney();
        email = userDto.getEmail();
        age = userDto.getAge();
        if(userDto.getGender() != null) gender = Gender.values()[userDto.getGender()];
        if(userDto.getPermission() != null) permission = Role.values()[userDto.getPermission()];
    }
    public static enum Gender {
        /** 0=男*/
        MAN,
        /** 1=女*/
        WOMAN,
    }
    public static enum Role {
        /** 没有用 0  */
        INVALID,
        /** 普通用户 1  */
        ORDINARY,
        /** 管理员 2  */
        STAFF,
        /** 合作 3 */
        VIP,
    }
    private String phone;
    private String password;
    private String name;
    private String portrait;
    private Double money;
    private String email;
    private Integer age;
    private Gender gender;
    private Role permission;

    private Integer Usedcount;
    private Double Usedcost;

    public static User convert(UserDto userDto){
        if(userDto == null)
            return null;
        return new User(userDto);
    }
}
