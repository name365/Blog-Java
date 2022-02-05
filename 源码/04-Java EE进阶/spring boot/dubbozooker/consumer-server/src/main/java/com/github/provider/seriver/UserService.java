package com.github.provider.seriver;

import com.github.consumer.seriver.TicketService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @author subeiLY
 * @create 2022-02-04 10:29
 */
@Service // 注入到容器中
public class UserService {
    @Reference // 远程引用指定的服务，他会按照全类名进行匹配，看谁给注册中心注册了这个全类名
    TicketService ticketService;
    public void bugTicket(){
        String ticket = ticketService.getTicket();
        System.out.println("在注册中心买到:"+ticket);
    }
}

