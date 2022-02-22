package com.github.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author subeiLY
 * @create 2022-02-22 23:31
 */
public class MyRondomRule extends AbstractLoadBalancerRule {
    public MyRondomRule() {
    }

    // total = 0 当total数等于5以后，我们指针才能往下走
    // index = 0 当前对外提供服务的服务器地址
    // 如果total等于5，则index+1，将total重置为0即可！
    // 问题：我们只有3台机器，所有total>3 则将total置为0；
    private int total = 0; //总共被调用的次数
    private int currentIndex = 0; //当前提供服务的机器序号！
    // ILoadBalancer选择的随机算法
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while(server == null) {
                // 查看线程是否中断了
                if (Thread.interrupted()) {
                    return null;
                }

                // Reachable： 可及；可到达；够得到
                List<Server> upList = lb.getReachableServers(); // 活着的服务
                List<Server> allList = lb.getAllServers(); // 获取所有的服务
                int serverCount = allList.size();
                if (serverCount == 0) {
                    return null;
                }

                // 生成区间随机数！
//                int index = this.chooseRandomInt(serverCount);
                // 从活着的服务中，随机取出一个
//                server = (Server)upList.get(index);

                // =====================================
                if (total<5){
                    server = upList.get(currentIndex);
                    total++;
                }else {
                    total = 0;
                    currentIndex++;
                    if (currentIndex>=upList.size()){
                        currentIndex = 0;
                    }
                    server = upList.get(currentIndex);
                }
                // =====================================

                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        return server;
                    }

                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }

    // 随机
    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    @Override
    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
