package com.xxl.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

//    @Bean({"ExecuteCmdUtil"})
//    public ExecuteAlgorithmsUtil executeCmdUtil(@Qualifier("globalFileStorage") FileStorage globalStoage,
//                                                Registration registration) {
//        return new ExecuteAlgorithmsUtil(null, envVars.getCmdLogPath(), envVars.getCmdCppPath(),
//                envVars.getCmdJavaPath(), envVars.isCoreDumpEnable(), envVars.getCoreDumpFileNum(), globalStoage,
//                registration.getHost());
//    }
}
