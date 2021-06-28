package com.chemcool.school.apigateway.configuration.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class WebApiGatewayFallbackProvider implements FallbackProvider {
    private static final String DEFAULT_FALLBACK_MESSAGE = "Service not available";

    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return cause instanceof HystrixTimeoutException ?
                new GatewayClientFallbackResponse(HttpStatus.GATEWAY_TIMEOUT, DEFAULT_FALLBACK_MESSAGE)
                : new GatewayClientFallbackResponse(HttpStatus.INTERNAL_SERVER_ERROR, DEFAULT_FALLBACK_MESSAGE);
    }
}

