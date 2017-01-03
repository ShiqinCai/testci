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
 * 初始化租户IaaS资源。
 * 
 * @author Feng Changjian (hzfengchj@corp.netease.com)
 * @version $Id: InitTenantJob.java, v 1.0 2015年3月20日 下午8:27:29
 */
@JobSchema(mode = JobSchema.MODE_ASYNC)
public class InitTenantJob extends Job {
    
    public Export call() {

        String tenantId = init.getContentString("tenantId");
        
        JSONObject initMsg = new JSONObject();
        initMsg.element("uniqueId", init.getUniqueId());
        initMsg.element("cmd", "initTenant");
        initMsg.element("content", init.getContent());
        MQ.sendMessageToContainer(initMsg.toString());

        return export;
    }
}
