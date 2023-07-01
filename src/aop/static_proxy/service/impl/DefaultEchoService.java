package aop.static_proxy.service.impl;

import aop.static_proxy.service.EchoService;

/**
 * 默认 {@link EchoService} 实现
 *
 * @Author lishaohui
 * @Date 2023/5/26 18:08
 */
public class DefaultEchoService implements EchoService {

    @Override
    public String echo(String message) {
        return "[ECHO] " + message;
    }
}
