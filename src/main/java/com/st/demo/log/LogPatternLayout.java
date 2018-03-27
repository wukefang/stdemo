package com.st.demo.log;

import ch.qos.logback.classic.PatternLayout;

public class LogPatternLayout extends PatternLayout {
    static {
        defaultConverterMap.put("traceId",LogTraceConvert.class.getName());
    }
}
