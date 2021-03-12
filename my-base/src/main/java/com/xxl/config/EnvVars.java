package com.xxl.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnvVars {

    @Value("${base.work.mode}")
    private String workMode ;

    public String getWorkMode() {
        return workMode;
    }
}
