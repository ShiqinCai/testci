/**
 * (C) Copyright Netease.com, Inc. 2015. All Rights Reserved.
 */
package com.netease.cloud.nce.ctrl.job;

import net.sf.json.JSONObject;

import com.netease.cloud.nce.mq.MQ;
import com.netease.cloud.nce.sched.job.Job;
import com.netease.cloud.nce.sched.meta.Export;


/**
 * 处理构建应用返回结果。
 * 
 * @author Feng Changjian (hzfengchj@corp.netease.com)
 * @version $Id: BuildReleaseRespJob.java, v 1.0 2015年3月17日 下午5:07:13
 */
public class BuildReleaseRespJob extends Job {
    
    public Export call() {

        JSONObject buildResultMsg = new JSONObject();
        buildResultMsg.element("uniqueId", init.getUniqueId());
        buildResultMsg.element("cmd", init.getCmd());
        buildResultMsg.element("content", init.getContent());
        MQ.sendMessage(fromSet.get(MQ.SERVICE_WEB), buildResultMsg.toString());

        return export;
    }
}
