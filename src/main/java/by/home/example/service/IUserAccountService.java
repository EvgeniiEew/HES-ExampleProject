package by.home.example.service;

import by.home.example.domain.StatusView;
import by.home.example.domain.UserAccount;
import by.home.example.dto.UserAccountDto;
import by.home.example.dto.UserNewDto;
import org.springframework.data.domain.Page;

public interface IUserAccountService {
    UserAccountDto setNewStatusUserAndSave(StatusView statusView, UserAccountDto userAccountDto);

    Page<UserAccount> findAllUsers(int pageNo, int pageSize, String userName, String role);

    void saveUserAccount(UserNewDto userNewDto);

    UserAccountDto getOneUser(Integer id);

    void editUser(UserAccountDto userAccountDto);

    UserAccount findByLogin(String login);
}
