/**
 * (C) Copyright Netease.com, Inc. 2015. All Rights Reserved.
 */
package com.netease.cloud.nce.ctrl.job;

import net.sf.json.JSONObject;

import com.netease.cloud.nce.mq.MQ;
import com.netease.cloud.nce.sched.job.Job;
import com.netease.cloud.nce.sched.meta.Export;

/**
 * 删除应用的响应Job。
 * 
 * @author Feng Changjian (hzfengchj@corp.netease.com)
 * @version $Id: DeleteFormationRespJob.java, v 1.0 2015年4月2日 下午4:29:32
 */
public class DeleteFormationRespJob extends Job {

    public Export call() {

        JSONObject deleteMsg = new JSONObject();
        deleteMsg.element("uniqueId", init.getUniqueId());
        deleteMsg.element("cmd", init.getCmd());
        deleteMsg.element("content", init.getContent());
        MQ.sendMessage(fromSet.get(MQ.SERVICE_WEB), deleteMsg.toString());

        return export;
    }
}
