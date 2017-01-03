/**
 * (C) Copyright Netease.com, Inc. 2015. All Rights Reserved.
 */
package com.netease.cloud.nce.ctrl.job;

import net.sf.json.JSONObject;

import com.netease.cloud.nce.mq.MQ;
import com.netease.cloud.nce.sched.job.Job;
import com.netease.cloud.nce.sched.meta.Export;

/**
 * 删除租户的响应Job。
 * 
 * @author Feng Changjian (hzfengchj@corp.netease.com)
 * @version $Id: DeleteNodeRespJob.java, v 1.0 2015年7月2日 下午1:58:05
 */
public class DeleteTenantRespJob extends Job {

    public Export call() {

        JSONObject deleteMsg = new JSONObject();
        deleteMsg.element("uniqueId", init.getUniqueId());
        deleteMsg.element("cmd", init.getCmd());
        deleteMsg.element("content", init.getContent());
        MQ.sendMessage(fromSet.get(MQ.SERVICE_WEB), deleteMsg.toString());

        return export;
    }
}
