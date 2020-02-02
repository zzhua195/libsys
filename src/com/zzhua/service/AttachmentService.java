package com.zzhua.service;

import com.zzhua.domain.Attachment;

import java.util.List;

/**
 * Copyright (C), 2019, 深圳太极云软技术有限公司
 * Author：   zzhua
 * Created by Administrator on 2020/2/1
 * <p>
 * Description:
 */


public interface AttachmentService {

    List<Attachment> selectAll(Attachment attachment);

    void addAttachment(Attachment attachment);

}
