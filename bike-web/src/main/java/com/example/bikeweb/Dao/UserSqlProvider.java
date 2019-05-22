package com.example.bikeweb.Dao;

import com.example.bikeweb.entity.User;
import org.apache.ibatis.jdbc.SQL;

public class UserSqlProvider {
    public static String updateUserSql(final User user) {
        return new SQL(){{
            UPDATE("tb_user");
            SET("password=#{password}");
            if(user.getName()!=null)
                SET("name=#{name}");
            if(user.getPortrait()!=null)
                SET("portrait=#{portrait}");
            if(user.getEmail()!=null)
                SET("email=#{email}");
            SET("money=#{money} age=#{age} gender=#{gender}");
            WHERE("id = #{id}");
        }}.toString();
    }
}
