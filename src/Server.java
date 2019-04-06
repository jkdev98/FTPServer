import org.apache.ftpserver.ConnectionConfig;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.ssl.SslConfigurationFactory;
import org.apache.log4j.Logger;



import java.io.File;

public class Server {

    private static final FtpServerFactory serverFactory = new FtpServerFactory();

    private static final ListenerFactory listener = new ListenerFactory();

    private FtpUserBase userBase;

    Server(FtpUserBase userBase) {
       this.userBase = userBase;
    }

    public static void setSSL(){
        // define SSL configuration
        SslConfigurationFactory ssl = new SslConfigurationFactory();
        ssl.setKeystoreFile(new File("C:\\Users\\jkdev\\IdeaProjects\\FTPserver\\src\\ftp\\resources\\ftpserver.jks"));
        ssl.setKeystorePassword("password");
        ssl.setClientAuthentication("NEED");

        // set the SSL configuration for the listener
        listener.setSslConfiguration(ssl.createSslConfiguration());
        listener.setImplicitSsl(true);
    }

    public void setServerPort(int port) {
        listener.setPort(port);
    }

    void startServer() {
      //  setSSL();

        //Logger logger = Logger.getLogger(org.apache.log4j.Logger.class);
        //logger.isTraceEnabled();
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
