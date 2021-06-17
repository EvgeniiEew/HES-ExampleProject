package by.home.example.converter;

import by.home.example.domain.UserAccount;
import by.home.example.dto.UserAccountDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserAccoutDtoToUserAccount implements Converter<UserAccountDto, UserAccount> {
    @Override
    public UserAccount convert(UserAccountDto source) {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(source.getId());
        userAccount.setUserName(source.getUserName());
        userAccount.setPassword(source.getPassword());
        userAccount.setFirstName(source.getFirstName());
        userAccount.setLastName(source.getLastName());
        userAccount.setRole(source.getRole());
        userAccount.setCreatedAt(source.getCreatedAt());
        return userAccount;
    }
}
