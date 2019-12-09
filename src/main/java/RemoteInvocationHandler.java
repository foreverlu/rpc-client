import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteInvocationHandler implements InvocationHandler {

    private String host;

    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public RemoteInvocationHandler(){}

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String methodName = method.getName();
        RPCRequest request = new RPCRequest();
        request.setMethod(methodName);
        request.setParameters(args);

        RpcClientTransport rpcClientTransport = new RpcClientTransport(host,port);

        return rpcClientTransport.sendRquest(request);
    }
}
