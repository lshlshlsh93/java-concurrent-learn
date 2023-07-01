package aop.static_proxy.service.impl;

import aop.static_proxy.service.EchoService;

/**
 * 代理 {@link EchoService} 实现
 *
 * @Author lishaohui
 * @Date 2023/5/26 18:10
 */
public class ProxyEchoService implements EchoService {

    private final EchoService echoService;

    public ProxyEchoService(EchoService echoService) {
        this.echoService = echoService;
    }

    @Override
    public String echo(String message) {
        long start = System.currentTimeMillis();
        String result = echoService.echo(message);
        long cost = System.currentTimeMillis() - start;
        System.out.println("Echo method cost :" + cost + "ms");
        return result;
    }
}
