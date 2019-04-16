package com.meituan;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class testlog {
    private static Log logger = LogFactory.getLog(testlog.class);

    public static void main(String[] args) {
        logger.trace("我是trace信息");
        logger.debug("我是debug信息");
        logger.info("我是info信息");
        logger.warn("我是warn信息");
        logger.error("我是error信息");
        logger.fatal("我是fatal信息");
    }
}
