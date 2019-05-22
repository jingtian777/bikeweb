package com.example.bikeweb.Dao;

import com.example.bikeweb.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;

@Mapper
public interface UserDao {

    @Insert("insert into tb_user (phone,password,email) values (#{phone},#{password},#{email})")
    @SelectKey(statement = "select max(id) as id from tb_user", before = false, keyProperty = "id",resultType = Long.class)
    public void insert(User user);

    @Select("select * from tb_user where id = #{id}")
    @Results({ @Result(property = "gender", column = "gender", typeHandler = EnumOrdinalTypeHandler.class),
            @Result(property = "permission", column = "permission", typeHandler = EnumOrdinalTypeHandler.class) })
    public User getUserById(Long id);

    @Select("select * from tb_user where phone = #{phone}")
    @Results({ @Result(property = "gender", column = "gender", typeHandler = EnumOrdinalTypeHandler.class),
            @Result(property = "permission", column = "permission", typeHandler = EnumOrdinalTypeHandler.class) })
    public User getUserByPhone(String phone);

    @Update("<script> update tb_user set id=#{id}<if test='phone!=null'>,phone=#{phone}</if><if test='password!=null'>,password=#{password}</if><if test='name!=null'>,name=#{name}</if><if test='portrait!=null'>,portrait=#{portrait}</if><if test='email!=null'>,email=#{email}</if><if test='money!=null'>,money=#{money}</if><if test='age!=null'>,age=#{age}</if><if test='gender!=null'>,gender=#{gender,typeHandler=org.apache.ibatis.type.EnumOrdinalTypeHandler}</if><if test='permission!=null'>,permission=#{permission,typeHandler=org.apache.ibatis.type.EnumOrdinalTypeHandler}</if> where id=#{id} </script>")
    public void updateUser(User user);

    @Select("select count(*) as A,sum(cost) as B from tb_record where user_id =#{id} and eGps is not null and UNIX_TIMESTAMP(bTime)>(UNIX_TIMESTAMP(utc_timestamp())-576000)")
    @Results({ @Result(property = "Usedcount", column = "A"),
            @Result(property = "Usedcost", column = "B")})
    public User getUsedCount(Long id);

}