package com.netease.cloud.nce.ctrl;

import com.netease.fronted.nce.NceTomcatServer;
import com.netease.fronted.nce.TomcatConfig;

public class NceControllerTomcatServer {

	public static void main(String[] args) {
		NceTomcatServer.start(8282);
	}

}
