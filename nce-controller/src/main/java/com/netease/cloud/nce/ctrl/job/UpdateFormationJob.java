/**
 * (C) Copyright Netease.com, Inc. 2015. All Rights Reserved.
 */
package com.netease.cloud.nce.ctrl.job;

import net.sf.json.JSONObject;

import com.netease.cloud.nce.mq.MQ;
import com.netease.cloud.nce.sched.annotation.JobSchema;
import com.netease.cloud.nce.sched.job.Job;
import com.netease.cloud.nce.sched.meta.Export;


/**
 * 更新应用
 * 
 * @author Feng Changjian (hzfengchj@corp.netease.com)
 * @version $Id: UpdateFormationJob.java, v 1.0 2015年4月13日 下午5:25:26
 */
@JobSchema(mode = JobSchema.MODE_ASYNC)
public class UpdateFormationJob extends Job {
    
    public Export call() {

        JSONObject deployMsg = new JSONObject();
        deployMsg.element("uniqueId", init.getUniqueId());
        deployMsg.element("cmd", init.getCmd());
        deployMsg.element("content", init.getContent());
        MQ.sendMessageToContainer(deployMsg.toString());

        return export;
    }
}
