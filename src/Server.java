import org.apache.ftpserver.ConnectionConfig;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.impl.DefaultFtpServerContext;
import org.apache.ftpserver.listener.ListenerFactory;


import java.io.IOException;


class Server {

    private static final FtpServerFactory serverFactory = new FtpServerFactory();

    private static final ListenerFactory listener = new ListenerFactory();

    private FtpUserBase userBase;

    Server(FtpUserBase userBase) {
       this.userBase = userBase;
    }

    void setServerPort(int port) {
        listener.setPort(port);
    }

    void startServer()  {
        
        serverFactory.setUserManager(userBase.getUserManager());

        serverFactory.setConnectionConfig(new ConnectionConfig() {
            @Override
            public int getMaxLoginFailures() {
                return 5;
            }

            @Override
            public int getLoginFailureDelay() {
                return 0;
            }

            @Override
            public int getMaxAnonymousLogins() {
                return 0;
            }

            @Override
            public int getMaxLogins() {
                return 0;
            }

            @Override
            public boolean isAnonymousLoginEnabled() {
                return false;
            }

            @Override
            public int getMaxThreads() {
                return 4;
            }
        });

        FtpServer server = serverFactory.createServer();

        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
