package com.novel.mapper;

import com.novel.bean.NovelClock;
import com.novel.bean.NovelClockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NovelClockMapper {
    long countByExample(NovelClockExample example);

    int deleteByExample(NovelClockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NovelClock record);

    int insertSelective(NovelClock record);

    List<NovelClock> selectByExample(NovelClockExample example);

    NovelClock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NovelClock record, @Param("example") NovelClockExample example);

    int updateByExample(@Param("record") NovelClock record, @Param("example") NovelClockExample example);

    int updateByPrimaryKeySelective(NovelClock record);

    int updateByPrimaryKey(NovelClock record);
}