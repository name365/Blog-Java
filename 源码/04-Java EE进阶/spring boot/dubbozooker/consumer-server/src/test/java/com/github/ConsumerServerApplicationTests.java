package com.github;

import com.github.consumer.seriver.TicketService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerServerApplicationTests {
    @Autowired
    TicketService ticketService;
    @Test
    public void contextLoads() {
        ticketService.getTicket();
    }
}
