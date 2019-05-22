package com.example.bikeweb.Dao;

import com.example.bikeweb.entity.Bike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BikeDao {

    @Select("select * from tb_bike where status=0 order by number")
    public List<Bike> getBikes();

    @Select("select * from tb_bike where number = #{number}")
    public Bike getBikeByNumber(String number);

    @Update("<script> update tb_bike set id=#{id}<if test='gps!=null'>,gps=#{gps}</if><if test='status!=null'>,status=#{status}</if> where id=#{id} </script>")
    public void updateBike(Bike bike);
}
