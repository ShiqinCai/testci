package com.netease.cloud.nce.ctrl.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netease.cloud.nce.commons.conf.Const;
import com.netease.cloud.nce.mq.MQ;
import com.netease.cloud.nce.sched.meta.MsgPayload;
import com.netease.nce.client.Message;

public class AbstractController {

    private static Logger logger = LoggerFactory.getLogger(AbstractController.class);

    protected Message createMessge(MsgPayload payload) {
        Message msg = new Message();
        msg.setFrom("ignore");
        msg.setTo(MQ.SERVICE_CTRL);
        msg.setMsg(JSONObject.fromObject(payload).toString());
        return msg;
    }
    
    protected void setErrorResponse(HttpServletResponse response, int code, String message) {
        response.setStatus(code);
        JSONObject err = new JSONObject();
        err.element("code", code);
        err.element("message", message);
        response(response, err);
    }

    protected void setResponse(HttpServletResponse response, Object result) {
        JSONObject err = new JSONObject();
        err.element("code", 200);
        err.element("message", "ok");
        err.element("result", result);
        response(response, err);
    }

    private void response(HttpServletResponse response, Object o) {
        try {
            response.setCharacterEncoding(Const.DEFAULT_ENCODING);
            response.getWriter().write(o.toString());
            response.flushBuffer();
        } catch (IOException e) {
            logger.warn("http response error.", e);
        }
    }
}
