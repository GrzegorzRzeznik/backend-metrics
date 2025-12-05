package com.epam.edp.demo;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;



@Component
public class CustomMetricsConfig {

    private final MeterRegistry meterRegistry;

    public CustomMetricsConfig(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @PostConstruct
    public void registerMemoryUsageGauge() {
        meterRegistry.gauge("app_memory_usage_bdscgh7i", this, CustomMetricsConfig::getMemoryUsage);
    }

    private double getMemoryUsage() {
        // Returns used memory in bytes
        return (double) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
    }
}