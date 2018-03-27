package com.st.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.st.util.TraceIdUtils;

public class LogTraceConvert extends ClassicConverter {


    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        return TraceIdUtils.get();
    }
}
