package com.zzhua.service.impl;

import com.zzhua.domain.Attachment;
import com.zzhua.mapper.AttachmentMapper;
import com.zzhua.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (C), 2019, 深圳太极云软技术有限公司
 * Author：   zzhua
 * Created by Administrator on 2020/2/1
 * <p>
 * Description:
 */

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Override
    public List<Attachment> selectAll(Attachment attachment) {
        return attachmentMapper.selectAll(attachment);
    }

    @Override
    public void addAttachment(Attachment attachment) {
        attachmentMapper.insertSelective(attachment);
    }
}
