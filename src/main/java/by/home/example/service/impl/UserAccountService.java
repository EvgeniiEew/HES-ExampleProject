package by.home.example.service.impl;

import by.home.example.domain.Status;
import by.home.example.domain.StatusView;
import by.home.example.domain.UserAccount;
import by.home.example.dto.UserAccountDto;
import by.home.example.dto.UserNewDto;
import by.home.example.service.IUserAccountService;
import by.home.example.service.repository.UserAccountJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserAccountService implements IUserAccountService {
    @Autowired
    private UserAccountJpaRepository userAccountJpaRepository;
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public Page<UserAccount> findAllUsers(int pageNo, int pageSize, String userName, String role) {
        Pageable pageable;
        if (userName != null & role == null) {
            pageable = PageRequest.of(pageNo - 1, pageSize);
            return userAccountJpaRepository.findAllByIdIn(userAccountJpaRepository.findAllIdNativeName(userName), pageable);
        } else if (userName == null & role != null) {
            pageable = PageRequest.of(pageNo - 1, pageSize);
            return userAccountJpaRepository.findAllByIdIn(userAccountJpaRepository.findAllIdNativeByRole(role), pageable);
        } else {
            pageable = PageRequest.of(pageNo - 1, pageSize);
        }
        return userAccountJpaRepository.findAllByIdIn(userAccountJpaRepository.findAllIdNative(), pageable);
    }

    @Override
    public void saveUserAccount(UserNewDto userNewDto) {
        UserAccount userAccount = conversionService.convert(userNewDto, UserAccount.class);
        userAccount.setPassword(bCryptPasswordEncoder.encode(userNewDto.getPassword()));
        userAccountJpaRepository.save(userAccount);
    }

    @Override
    public UserAccountDto setNewStatusUserAndSave(StatusView statusView, UserAccountDto userAccountDto) {
        UserAccount userAccount = conversionService.convert(userAccountDto, UserAccount.class);
        if (statusView == StatusView.UNLOCK) {
            userAccount.setStatus(Status.ACTIVE);
            userAccountDto.setStatusView(StatusView.UNLOCK);
        } else {
            userAccount.setStatus(Status.INACTIVE);
            userAccountDto.setStatusView(StatusView.LOCK);
        }
        userAccountJpaRepository.save(userAccount);
        return userAccountDto;
    }

    @Override
    public UserAccountDto getOneUser(Integer id) {
        UserAccount userAccount = userAccountJpaRepository.getOne(id);
        return conversionService.convert(userAccount, UserAccountDto.class);
    }

    @Override
    public void editUser(UserAccountDto userAccountDto) {
        UserAccount userAccount = userAccountJpaRepository.getOne(userAccountDto.getId());
        userAccount.setUserName(userAccountDto.getUserName());
        userAccount.setPassword(bCryptPasswordEncoder.encode(userAccountDto.getPassword()));
        userAccount.setFirstName(userAccountDto.getFirstName());
        userAccount.setLastName(userAccountDto.getLastName());
        userAccount.setRole(userAccountDto.getRole());
        userAccountJpaRepository.save(userAccount);
    }

    @Override
    public UserAccount findByLogin(String login) {
        return userAccountJpaRepository.findByUserName(login);
    }
}
