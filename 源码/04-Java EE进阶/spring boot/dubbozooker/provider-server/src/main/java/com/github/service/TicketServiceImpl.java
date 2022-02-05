package com.github.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @author subeiLY
 * @create 2022-02-04 10:30
 */
@Service //将服务发布出去
@Component //放在容器中
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "《subeiLY》";
    }
}
