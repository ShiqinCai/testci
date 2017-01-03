/**
 * (C) Copyright Netease.com, Inc. 2015. All Rights Reserved.
 */
package com.netease.cloud.nce.ctrl.job;

import net.sf.json.JSONObject;

import com.netease.cloud.nce.mq.MQ;
import com.netease.cloud.nce.sched.job.Job;
import com.netease.cloud.nce.sched.meta.Export;

/**
 * 部署应用的响应Job。
 * 
 * @author Feng Changjian (hzfengchj@corp.netease.com)
 * @version $Id: deployRespFormationJob.java, v 1.0 2015年3月25日 上午9:58:53
 */
public class DeployFormationRespJob extends Job {

    public Export call() {

        JSONObject deployMsg = new JSONObject();
        deployMsg.element("uniqueId", init.getUniqueId());
        deployMsg.element("cmd", init.getCmd());
        deployMsg.element("content", init.getContent());
        MQ.sendMessage(fromSet.get(MQ.SERVICE_WEB), deployMsg.toString());

        return export;
    }
}
