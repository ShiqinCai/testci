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
 * 扩容/缩容应用。
 * 
 * @author Feng Changjian (hzfengchj@corp.netease.com)
 * @version $Id: ResizeFormationJob.java, v 1.0 2015年4月14日 下午7:07:36
 */
@JobSchema(mode = JobSchema.MODE_ASYNC)
public class ResizeFormationJob extends Job {
    
    public Export call() {

        JSONObject deployMsg = new JSONObject();
        deployMsg.element("uniqueId", init.getUniqueId());
        deployMsg.element("cmd", init.getCmd());
        deployMsg.element("content", init.getContent());
        MQ.sendMessageToContainer(deployMsg.toString());

        return export;
    }
}
