package com.example.sunny.mapper;

import com.example.sunny.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,tag,gmt_create,gmt_modified,creator) value (#{title},#{description},#{tag},#{gmtCreate},#{gmtModified},#{creator})")
    void create(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(Integer offset, Integer size);

    @Select("select * from question")
    List<Question> selectByPage();

    @Select("select count(1) from question")
    Integer count();
}
