package com.example.bikeweb.Dao;

import com.example.bikeweb.entity.Charge;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ChargeDao {

    @Select("select * from tb_charge where number = #{number}")
    @Results({ @Result(property = "userId", column = "user_id"),})
    public Charge getChargeByNumber(String number);

    @Update("<script> update tb_charge set id=#{id}, validity=1, user_id=#{userId} where id=#{id} </script>")
    public void useCharge(Long id,Long userId);
}
