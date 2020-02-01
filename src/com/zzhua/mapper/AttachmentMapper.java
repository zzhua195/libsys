package com.zzhua.mapper;

import com.zzhua.domain.Attachment;

import java.util.List;

public interface AttachmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Attachment record);

    int insertSelective(Attachment record);

    Attachment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Attachment record);

    int updateByPrimaryKey(Attachment record);

    List<Attachment> selectAll(Attachment attachment);
}