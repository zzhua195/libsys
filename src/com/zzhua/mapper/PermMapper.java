package com.zzhua.mapper;

import com.zzhua.domain.Perm;

import java.util.List;

public interface PermMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Perm record);

    int insertSelective(Perm record);

    Perm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Perm record);

    int updateByPrimaryKey(Perm record);

    List<Perm> selectPermsByUid(Integer uid);
}