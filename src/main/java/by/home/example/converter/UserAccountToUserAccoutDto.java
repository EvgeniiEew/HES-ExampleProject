package by.home.example.converter;

import by.home.example.domain.Status;
import by.home.example.domain.StatusView;
import by.home.example.domain.UserAccount;
import by.home.example.dto.UserAccountDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserAccountToUserAccoutDto implements Converter<UserAccount, UserAccountDto> {
    public UserAccountToUserAccoutDto() {
    }

    @Override
    public UserAccountDto convert(UserAccount source) {
        UserAccountDto userAccountDto = new UserAccountDto();
        userAccountDto.setId(source.getId());
        userAccountDto.setUserName(source.getUserName());
        userAccountDto.setPassword(source.getPassword());
        userAccountDto.setFirstName(source.getFirstName());
        userAccountDto.setLastName(source.getLastName());
        userAccountDto.setRole(source.getRole());
        if (source.getStatus().equals(Status.ACTIVE)) {
            userAccountDto.setStatusView(StatusView.UNLOCK);
        } else {
            userAccountDto.setStatusView(StatusView.LOCK);
        }
        userAccountDto.setCreatedAt(source.getCreatedAt());
        return userAccountDto;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserAccountToUserAccoutDto)) return false;
        final UserAccountToUserAccoutDto other = (UserAccountToUserAccoutDto) o;
        if (!other.canEqual((Object) this)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserAccountToUserAccoutDto;
    }

    public int hashCode() {
        int result = 1;
        return result;
    }

    public String toString() {
        return "UserAccountToUserAccoutDto()";
    }
}
