package com.github;

/**
 * @author subeiLY
 * @create 2022-01-15 14:45
 */
public class HelloService {
    HelloProperties helloProperties;

    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    public String sayHello(String name){
        return helloProperties.getPrefix() + name +
                helloProperties.getSuffix();
    }
}
