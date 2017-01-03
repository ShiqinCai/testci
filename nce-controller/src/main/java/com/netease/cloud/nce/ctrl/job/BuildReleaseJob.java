/**
 * (C) Copyright Netease.com, Inc. 2015. All Rights Reserved.
 */
package com.netease.cloud.nce.ctrl.job;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.netease.cloud.nce.mq.MQ;
import com.netease.cloud.nce.sched.annotation.JobSchema;
import com.netease.cloud.nce.sched.job.Job;
import com.netease.cloud.nce.sched.meta.Export;
import com.netease.cloud.nce.service.IReleaseService;


/**
 * 构建应用。
 * 
 * @author Feng Changjian (hzfengchj@corp.netease.com)
 * @version $Id: BuildReleaseJob.java, v 1.0 2015年3月17日 下午4:30:16
 */
@JobSchema(mode = JobSchema.MODE_ASYNC)
public class BuildReleaseJob extends Job {
    
    @Autowired
    private IReleaseService releaseService = null;

    public Export call() {

        JSONObject buildMsg = new JSONObject();
        buildMsg.element("uniqueId", init.getUniqueId());
        buildMsg.element("cmd", init.getCmd());
        buildMsg.element("content", init.getContent());
        MQ.sendMessageToBuild(buildMsg.toString());

        return export;
    }
}
