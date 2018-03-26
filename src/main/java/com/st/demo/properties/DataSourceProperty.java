package com.st.demo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jdbc")
public class DataSourceProperty {

    private JdbcProperty read;

    private JdbcProperty write;

    public JdbcProperty getRead() {
        return read;
    }

    public void setRead(JdbcProperty read) {
        this.read = read;
    }

    public JdbcProperty getWrite() {
        return write;
    }

    public void setWrite(JdbcProperty write) {
        this.write = write;
    }
}
