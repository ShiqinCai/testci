package com.netease.cloud.nce.ctrl.controller;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netease.cloud.nce.sched.util.SchedUtils;
import com.netease.cloud.nce.sched.util.StatUtils;

@Controller
@RequestMapping("/stat")
public class StatController extends AbstractController {

    public static final Logger logger = LoggerFactory.getLogger(StatController.class);

    @RequestMapping(value = "/sched", method = RequestMethod.GET)
    public void statSched(String tenantId, HttpServletResponse response, ModelMap model) {
        setResponse(response, StatUtils.get());
    }

    @RequestMapping(value = "/sched/graceful-shutdown", method = RequestMethod.POST)
    public void gracefulShutdown(String tenantId, HttpServletResponse response, ModelMap model) {
        SchedUtils.gracefulShutdown();
        setResponse(response, new JSONObject());
    }

    @RequestMapping(value = "/sched/graceful-shutdown", method = RequestMethod.GET)
    public void getGracefulShutdownStatus(String tenantId, HttpServletResponse response, ModelMap model) {
        setResponse(response, new JSONObject().element("couldShutdown", SchedUtils.couldShutdown()));
    }
}
