package com.novel.mapper;

import com.novel.bean.NovelLower;
import com.novel.bean.NovelLowerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface NovelLowerMapper {
    long countByExample(NovelLowerExample example);

    int deleteByExample(NovelLowerExample example);

    int deleteByPrimaryKey(Integer novelid);

    int insert(NovelLower record);

    int insertSelective(NovelLower record);

    List<NovelLower> selectByExample(NovelLowerExample example);

    NovelLower selectByPrimaryKey(Integer novelid);

    int updateByExampleSelective(@Param("record") NovelLower record, @Param("example") NovelLowerExample example);

    int updateByExample(@Param("record") NovelLower record, @Param("example") NovelLowerExample example);

    int updateByPrimaryKeySelective(NovelLower record);

    int updateByPrimaryKey(NovelLower record);

}