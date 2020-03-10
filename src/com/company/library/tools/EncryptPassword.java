package com.company.library.tools;

import com.company.library.dto.User;
import org.mindrot.jbcrypt.BCrypt;

public class EncryptPassword {

    public String createPass(String plain_password)
    {

        String pw_hash = BCrypt.hashpw(plain_password, BCrypt.gensalt());

        return pw_hash;
    }

    public boolean checkPassLogin(User user, String login, String password)
    {

        if((BCrypt.checkpw(login, user.getLogin())) && (BCrypt.checkpw(password, user.getPassword())))
            return true;

        return false;
    }

}
