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
 * 删除formation。
 * 
 * @author Feng Changjian (hzfengchj@corp.netease.com)
 * @version $Id: DeleteFormationJob.java, v 1.0 2015年4月2日 下午4:28:20
 */
@JobSchema(mode = JobSchema.MODE_ASYNC)
public class DeleteFormationJob extends Job {
    
    public Export call() {

        JSONObject deleteMsg = new JSONObject();
        deleteMsg.element("uniqueId", init.getUniqueId());
        deleteMsg.element("cmd", init.getCmd());
        deleteMsg.element("content", init.getContent());
        MQ.sendMessageToContainer(deleteMsg.toString());

        return export;
    }
}
