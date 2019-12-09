import java.lang.reflect.Proxy;

public class RpcClientProxy {

    public <T> T clientProxy(Class interfac, String host, int port) {
        return (T) Proxy.newProxyInstance(interfac.getClassLoader(), new Class[]{interfac},
            new RemoteInvocationHandler(host, port));
    }

}
