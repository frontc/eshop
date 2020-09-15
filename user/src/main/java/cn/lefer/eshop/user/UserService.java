package cn.lefer.eshop.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    final private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User loadUserByUserName(String userName){
        User user = userRepository.getUserByUserName(userName,false);
        user.setRoles(userRepository.getRoleByUserID(user.getUserID(),false));
        user.setPermissions(userRepository.getPermissionByUserID(user.getUserID(),false));
        return user;
    }
}
