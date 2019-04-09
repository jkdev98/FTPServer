import org.apache.ftpserver.ftplet.*;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.ConcurrentLoginPermission;
import org.apache.ftpserver.usermanager.impl.TransferRatePermission;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class FtpUserBase {
    private final MyUserManager userManager = new MyUserManager();

    private void addUser(String name, String password, File FtpRoot)  {

        BaseUser user = new BaseUser();
        user.setName(name);
        user.setPassword(password);
        user.setHomeDirectory("C:\\\\Users\\jkdev\\dirs\\" + name + "\\");
        File userHome = new File(FtpRoot, name);
        userHome.mkdirs();
        user.setHomeDirectory(userHome.getAbsolutePath());
        user.setMaxIdleTime(0);
        user.setEnabled(true);

        List<Authority> authorities = new ArrayList<>();
        authorities.add(new ConcurrentLoginPermission(0, 0));
        authorities.add(new WritePermission());
        authorities.add(new TransferRatePermission(0, 0));
        user.setAuthorities(authorities);
        userManager.save(user);
        //um.save(user);
    }

    UserManager getUserManager() {
        return this.userManager;
    }


    void loadUserConfiguration(File f, File ftpRoot) {
        try {
            Scanner input = new Scanner(f);

            while (input.hasNextLine()) {
                String line = input.nextLine();

                String []loginData = line.split(";");
                String user = loginData[0];
                String password = loginData[1];
                addUser(user, password, ftpRoot);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
