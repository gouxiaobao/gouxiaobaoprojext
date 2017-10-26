package ntest.util.https;

/**
 * Created by guo on 2017/4/11.
 */
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SSLSocketFactoryWrapper extends SSLSocketFactory {

    private final SSLSocketFactory wrappedFactory;
    private final String[] enabledProtocols;
    private final String[] enabledSuites;

    public SSLSocketFactoryWrapper(SSLSocketFactory factory, String[] protocols, String[] suites) {
        wrappedFactory = factory;
        enabledProtocols = protocols;
        enabledSuites = suites;
    }


    /**
     * @param host
     * @param port
     * @return
     * @throws IOException
     * @throws UnknownHostException
     * @see javax.net.SocketFactory#createSocket(java.lang.String, int)
     */
    public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
        SSLSocket socket = (SSLSocket)wrappedFactory.createSocket(host, port);
        setParameters(socket);
        return socket;
    }

    /**
     * @param host
     * @param port
     * @param localHost
     * @param localPort
     * @return
     * @throws IOException
     * @throws UnknownHostException
     * @see javax.net.SocketFactory#createSocket(java.lang.String, int, java.net.InetAddress, int)
     */
    public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException,
            UnknownHostException {
        SSLSocket socket = (SSLSocket)wrappedFactory.createSocket(host, port, localHost, localPort);
        setParameters(socket);
        return socket;
    }

    /**
     * @param host
     * @param port
     * @return
     * @throws IOException
     * @see javax.net.SocketFactory#createSocket(java.net.InetAddress, int)
     */
    public Socket createSocket(InetAddress host, int port) throws IOException {
        SSLSocket socket = (SSLSocket)wrappedFactory.createSocket(host, port);
        setParameters(socket);
        return socket;
    }

    /**
     * @param address
     * @param port
     * @param localAddress
     * @param localPort
     * @return
     * @throws IOException
     * @see javax.net.SocketFactory#createSocket(java.net.InetAddress, int, java.net.InetAddress, int)
     */
    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort)
            throws IOException {
        SSLSocket socket = (SSLSocket)wrappedFactory.createSocket(address, port, localAddress, localPort);
        setParameters(socket);
        return socket;
    }

    /**
     * @return
     * @throws IOException
     * @see javax.net.SocketFactory#createSocket()
     */
    public Socket createSocket() throws IOException {
        SSLSocket socket = (SSLSocket)wrappedFactory.createSocket();
        setParameters(socket);
        return socket;
    }

    /**
     * @return
     * @see javax.net.ssl.SSLSocketFactory#getDefaultCipherSuites()
     */
    public String[] getDefaultCipherSuites() {
        return wrappedFactory.getDefaultCipherSuites();
    }

    /**
     * @return
     * @see javax.net.ssl.SSLSocketFactory#getSupportedCipherSuites()
     */
    public String[] getSupportedCipherSuites() {
        return enabledSuites == null ? wrappedFactory.getSupportedCipherSuites() : enabledSuites;
    }

    /**
     * @param s
     * @param host
     * @param port
     * @param autoClose
     * @return
     * @throws IOException
     * @see javax.net.ssl.SSLSocketFactory#createSocket(java.net.Socket, java.lang.String, int, boolean)
     */
    public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
        SSLSocket socket = (SSLSocket)wrappedFactory.createSocket(s, host, port, autoClose);
        setParameters(socket);
        return socket;
    }

    /**
     * Override the configured parameters on the socket.
     *
     * @param socket
     */
    private void setParameters(SSLSocket socket) {
        if (enabledProtocols != null) {
            socket.setEnabledProtocols(enabledProtocols);
        }
        if (enabledSuites != null) {
            socket.setEnabledCipherSuites(enabledSuites);
        }
    }
}
