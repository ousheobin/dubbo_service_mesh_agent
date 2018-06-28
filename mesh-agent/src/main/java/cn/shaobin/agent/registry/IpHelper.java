package cn.shaobin.agent.registry;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IpHelper {
	
	private static Logger logger = LoggerFactory.getLogger(IpHelper.class);

    public static String getHostIp() throws Exception {
        String ip = InetAddress.getLocalHost().getHostAddress();
        logger.info("Get Host Ip: "+ip);
        return ip;
    }
}
