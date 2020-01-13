package com.xding.statemachine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * 参考：使用Spring StateMachine框架实现状态机 http://blog.didispace.com/spring-statemachine/
 *
 * @author xding
 * @date 2019-07-18 15:02
 */
@Slf4j
@Configuration
@EnableStateMachine
public class SettleStateMachineConfig extends EnumStateMachineConfigurerAdapter<SettleStates, SettleEvents> {


    @Override
    public void configure(StateMachineConfigurationConfigurer<SettleStates, SettleEvents> config) throws Exception {
        config.withConfiguration();
    }

    @Override
    public void configure(StateMachineStateConfigurer<SettleStates, SettleEvents> states) throws Exception {
        states.withStates()
            .initial(SettleStates.WAIT_BUSINESS_SUBMIT)
            .states(EnumSet.allOf(SettleStates.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<SettleStates, SettleEvents> transitions) throws Exception {
        transitions
            .withExternal()
            .source(SettleStates.WAIT_BUSINESS_SUBMIT).target(SettleStates.WAIT_FINANCE_SUBMIT)
            .event(SettleEvents.COMMIT)
            .and()
            .withExternal()
            .source(SettleStates.WAIT_BUSINESS_SUBMIT).target(SettleStates.BUSINESS_CLOSED)
            .event(SettleEvents.CLOSE)
            .and()
            .withExternal()
            .source(SettleStates.WAIT_FINANCE_SUBMIT).target(SettleStates.APPROVING)
            .event(SettleEvents.COMMIT);

    }


}
