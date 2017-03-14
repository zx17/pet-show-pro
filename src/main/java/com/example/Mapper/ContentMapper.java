package com.example.Mapper;

import com.example.Entity.Contentlist;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Xin09 on 2017/3/3.
 */
@Mapper
@Repository
public interface ContentMapper {

    @Select("select * from content order by id desc")
    List<Contentlist> findbytype();

    @Select("select count(1) from content")
    int quertCount();


}
