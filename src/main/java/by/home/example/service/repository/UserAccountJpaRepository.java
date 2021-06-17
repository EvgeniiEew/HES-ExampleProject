package by.home.example.service.repository;

import by.home.example.domain.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserAccountJpaRepository extends JpaRepository<UserAccount, Integer> {
    Page<UserAccount> findAllByIdIn(List<Integer> list, Pageable pageable);

    @Query(value = "select id from user_account", nativeQuery = true)
    List<Integer> findAllIdNative();

    @Query(value = "select id from user_account u where  u.user_name =?1", nativeQuery = true)
    List<Integer> findAllIdNativeName(String userName);

    @Query(value = "select id from user_account u where  u.role =?1", nativeQuery = true)
    List<Integer> findAllIdNativeByRole(String role);

    UserAccount findByUserName(String userName);

}
