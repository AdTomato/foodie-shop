package com.imooc.mapper;

import com.imooc.pojo.PdmanDbVersion;
import com.imooc.pojo.PdmanDbVersionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PdmanDbVersionMapper {
    long countByExample(PdmanDbVersionExample example);

    int deleteByExample(PdmanDbVersionExample example);

    int insert(PdmanDbVersion record);

    int insertSelective(PdmanDbVersion record);

    List<PdmanDbVersion> selectByExample(PdmanDbVersionExample example);

    int updateByExampleSelective(@Param("record") PdmanDbVersion record, @Param("example") PdmanDbVersionExample example);

    int updateByExample(@Param("record") PdmanDbVersion record, @Param("example") PdmanDbVersionExample example);
}