/**
 * (C) Copyright Netease.com, Inc. 2015. All Rights Reserved.
 */
package com.netease.cloud.nce.ctrl.job;

import net.sf.json.JSONObject;

import com.netease.cloud.nce.mq.MQ;
import com.netease.cloud.nce.sched.job.Job;
import com.netease.cloud.nce.sched.meta.Export;


/**
 * 初始化租户IaaS资源的处理响应。
 * 
 * @author Feng Changjian (hzfengchj@corp.netease.com)
 * @version $Id: InitTenantRespJob.java, v 1.0 2015年3月24日 下午4:24:11
 */
public class InitTenantRespJob extends Job {
    
    public Export call() {

        JSONObject resultMsg = new JSONObject();
        resultMsg.element("uniqueId", init.getUniqueId());
        resultMsg.element("cmd", "initTenant");
        resultMsg.element("content", init.getContent());
        MQ.sendMessage(fromSet.get(MQ.SERVICE_WEB), resultMsg.toString());

        return export;
    }
}
