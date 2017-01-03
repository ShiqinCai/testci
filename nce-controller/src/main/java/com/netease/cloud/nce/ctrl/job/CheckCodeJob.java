/**
 * (C) Copyright Netease.com, Inc. 2015. All Rights Reserved.
 */
package com.netease.cloud.nce.ctrl.job;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netease.cloud.nce.sched.annotation.JobSchema;
import com.netease.cloud.nce.sched.job.Job;
import com.netease.cloud.nce.sched.meta.Export;

/**
 * 通过检查消息体是否存在code字段来判断此是否为正确的请求报文。隐含条件是，今后Playload里第一层不能有以"code"为key的参数。
 * 
 * @author Feng Changjian (hzfengchj@corp.netease.com)
 * @version $Id: CheckCodeJob.java, v 1.0 2015年5月29日 下午2:07:30
 */
@JobSchema(mode = JobSchema.MODE_SYNC)
public class CheckCodeJob extends Job {

    private static Logger logger = LoggerFactory.getLogger(CheckCodeJob.class);

    public Export call() {

        Map<String, Object> content = init.getContent();
        if (content.get("code") != null) {
            logger.warn("此为响应消息，不会在此处出现，MsgPayload:" + init);
            throw new RuntimeException("unexpect message payload.");
        }

        return export;
    }
}
