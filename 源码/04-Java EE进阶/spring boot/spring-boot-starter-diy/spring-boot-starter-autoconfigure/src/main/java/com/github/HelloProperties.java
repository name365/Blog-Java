package com.github;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author subeiLY
 * @create 2022-01-15 14:45
 */
@ConfigurationProperties(prefix = "github.qwe")
public class HelloProperties {
    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
