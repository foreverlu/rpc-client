public class RpcClientApp {

    public static void main(String[] args) {

        RpcClientProxy proxy = new RpcClientProxy();
        IUserService userService = proxy.clientProxy(IUserService.class,"localhost",9001);
        System.out.println(userService.sayHello("cww"));

    }

}
