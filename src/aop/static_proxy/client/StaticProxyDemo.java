package aop.static_proxy.client;

import aop.static_proxy.service.EchoService;
import aop.static_proxy.service.impl.DefaultEchoService;
import aop.static_proxy.service.impl.ProxyEchoService;

/**
 * @Author lishaohui
 * @Date 2023/5/26 18:06
 */
public class StaticProxyDemo {

    public static void main(String[] args) {
        final EchoService echoService = new ProxyEchoService(new DefaultEchoService());
        echoService.echo("Hello AOP");
    }

}
