package repository;

import hello.UserInfo;

public interface DBService {

    boolean updateInfo(String id, String oldPass, String newPass);

    UserInfo getParticipant(String email, String password);

    void insertUser(UserInfo user);

    void deleteUser(UserInfo user);
}
