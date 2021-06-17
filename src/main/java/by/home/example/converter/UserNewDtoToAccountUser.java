package by.home.example.converter;

import by.home.example.domain.Status;
import by.home.example.domain.UserAccount;
import by.home.example.dto.UserNewDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserNewDtoToAccountUser implements Converter<UserNewDto, UserAccount> {
    @Override
    public UserAccount convert(UserNewDto source) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUserName(source.getUserName());
        userAccount.setFirstName(source.getFirstName());
        userAccount.setLastName(source.getLastName());
        userAccount.setRole(source.getRole());
        userAccount.setStatus(Status.ACTIVE);
        userAccount.setCreatedAt(new Date());
        return userAccount;
    }
}
