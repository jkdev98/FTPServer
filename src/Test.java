import java.io.File;

public class Test {

    public static void main(String[] args) throws Exception {

        File temporaryFileToDelete = File.createTempFile("the file", "txt");
        temporaryFileToDelete.delete();
        File tmpDirectory = temporaryFileToDelete.getParentFile();
        File temporaryFtpRoot = new File(tmpDirectory, "ftp");
        FtpUserBase userBase = new FtpUserBase();

        userBase.createFactory();

        userBase.loadUserConfiguration(new File("./src/config.txt"), temporaryFtpRoot);

        Server ftpServer = new Server(userBase);

        ftpServer.setServerPort(21);
        ftpServer.startServer();

        //Server serv = new Server();

        //serv.startServer();

    }
}