import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RpcClientTransport {

    private String host;

    private int port;

    public RpcClientTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private Socket newSocket(){
        Socket newSocket = null;
        try {
            newSocket = new Socket(host,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newSocket;
    }

    public Object sendRquest(RPCRequest request){
        Socket socket = newSocket();

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object result = objectInputStream.readObject();

            objectInputStream.close();
            objectOutputStream.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(null != socket){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
