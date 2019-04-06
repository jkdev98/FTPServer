import org.apache.ftpserver.ftplet.Authentication;
import org.apache.ftpserver.ftplet.AuthenticationFailedException;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.User;
import org.apache.ftpserver.usermanager.impl.AbstractUserManager;

import java.util.HashMap;

public class MyUserManager extends AbstractUserManager {

    private HashMap<String, User> userMap = new HashMap<>();

    @Override
    public User getUserByName(String userName) throws FtpException {
        return userMap.get(userName);
    }

    @Override
    public String[] getAllUserNames() throws FtpException {
        return (String[]) userMap.keySet().toArray();
    }

    @Override
    public void delete(String name) throws FtpException {
        userMap.remove(name);
    }

    @Override
    public void save(User user) throws FtpException {
        userMap.put(user.getName(), user);
    }

    @Override
    public boolean doesExist(String name) throws FtpException {
        return userMap.containsKey(name);
    }

    @Override
    public User authenticate(Authentication authentication) throws AuthenticationFailedException {
        //do zrobienia!!
        return null;
    }
}
