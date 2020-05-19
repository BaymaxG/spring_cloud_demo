/**
 * Copyright © 2020-2020 BaymaxG.guNingbo
 */

package cn.itcast.gateway.filter;

import com.baymax.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义全局过滤器 -- 模拟用户登录
 * 实现接口GlobalFilter，Ordered
 *
 * @author guNingbo
 * @since 2020/5/19
 */
@Component
public class LoginFilter implements GlobalFilter, Ordered {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);

    /**
     * @param exchange 上下文
     * @param chain 网关过滤器接口
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        LOGGER.info("=>> LoginFilter filter start ...");
        ServerHttpRequest request = exchange.getRequest();
        String token = request.getQueryParams().getFirst("token");
        if (StringUtil.isNullOrEmpty(token)) {
            LOGGER.error("can't get token, please login!");
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            return response.setComplete();
        }
        return chain.filter(exchange); // 认证成功，继续向下执行
    }

    /**
     * 自定义过滤器的执行顺序
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
