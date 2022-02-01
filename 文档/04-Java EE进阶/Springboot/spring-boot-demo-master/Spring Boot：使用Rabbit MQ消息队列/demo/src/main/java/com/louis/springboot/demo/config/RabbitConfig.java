package com.louis.springboot.demo.config;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

	//=================== 普通队列 模式  ====================
	
    /**
     * 定义demoQueue队列
     * @return
     */
    @Bean
    public Queue demoString() {
        return new Queue("demoQueue");
    }
    
   //=================== fanout广播模式  ====================

    @Bean
    public Queue fanoutA() {
        return new Queue("fanout.a");
    }

    @Bean
    public Queue fanoutB() {
        return new Queue("fanout.b");
    }

    @Bean
    public Queue fanoutC() {
        return new Queue("fanout.c");
    }

    /**
     * 定义个fanout交换器
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        // 定义一个名为fanoutExchange的fanout交换器
        return new FanoutExchange("fanoutExchange");
    }

    /**
     * 将定义的fanoutA队列与fanoutExchange交换机绑定
     * @return
     */
    @Bean
    public Binding bindingExchangeWithA() {
        return BindingBuilder.bind(fanoutA()).to(fanoutExchange());
    }

    /**
     * 将定义的fanoutB队列与fanoutExchange交换机绑定
     * @return
     */
    @Bean
    public Binding bindingExchangeWithB() {
        return BindingBuilder.bind(fanoutB()).to(fanoutExchange());
    }

    /**
     * 将定义的fanoutC队列与fanoutExchange交换机绑定
     * @return
     */
    @Bean
    public Binding bindingExchangeWithC() {
        return BindingBuilder.bind(fanoutC()).to(fanoutExchange());
    }

   //=================== topic主题模式  ====================

    @Bean
    public Queue topiocA() {
        return new Queue("topic.a");
    }

    @Bean
    public Queue topicB() {
        return new Queue("topic.b");
    }

    @Bean
    public Queue topicC() {
        return new Queue("topic.c");
    }

    /**
     * 定义个topic交换器
     * @return
     */
    @Bean
    TopicExchange topicExchange() {
        // 定义一个名为fanoutExchange的fanout交换器
        return new TopicExchange("topicExchange");
    }

    /**
     * 将定义的topicA队列与topicExchange交换机绑定
     * @return
     */
    @Bean
    public Binding bindingTopicExchangeWithA() {
        return BindingBuilder.bind(topiocA()).to(topicExchange()).with("topic.msg");
    }

    /**
     * 将定义的topicB队列与topicExchange交换机绑定
     * @return
     */
    @Bean
    public Binding bindingTopicExchangeWithB() {
        return BindingBuilder.bind(topicB()).to(topicExchange()).with("topic.#");
    }

    /**
     * 将定义的topicC队列与topicExchange交换机绑定
     * @return
     */
    @Bean
    public Binding bindingTopicExchangeWithC() {
        return BindingBuilder.bind(topicC()).to(topicExchange()).with("topic.*.z");
    }
}