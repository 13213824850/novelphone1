package com.novel.mapper;

import com.novel.bean.ReadRecord;
import com.novel.bean.ReadRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReadRecordMapper {
    long countByExample(ReadRecordExample example);

    int deleteByExample(ReadRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReadRecord record);

    int insertSelective(ReadRecord record);

    List<ReadRecord> selectByExample(ReadRecordExample example);

    ReadRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReadRecord record, @Param("example") ReadRecordExample example);

    int updateByExample(@Param("record") ReadRecord record, @Param("example") ReadRecordExample example);

    int updateByPrimaryKeySelective(ReadRecord record);

    int updateByPrimaryKey(ReadRecord record);
}