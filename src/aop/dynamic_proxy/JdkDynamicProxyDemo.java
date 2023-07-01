package aop.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import aop.static_proxy.service.EchoService;
import aop.static_proxy.service.impl.DefaultEchoService;
import aop.static_proxy.service.impl.ProxyEchoService;

/**
 * @Author lishaohui
 * @Date 2023/5/26 18:14
 */
public class JdkDynamicProxyDemo {

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                    ProxyEchoService echoService = new ProxyEchoService(new DefaultEchoService());
                    return echoService.echo((String) args[0]);
                }
                return null;
            }
        });

        EchoService echoService = (EchoService) proxy;
        echoService.echo("Hello Aop");

    }


}
