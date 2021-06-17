package by.home.example.controller;

import by.home.example.domain.StatusView;
import by.home.example.domain.UserAccount;
import by.home.example.dto.UserAccountDto;
import by.home.example.dto.UserNewDto;
import by.home.example.service.IUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserAccountController {
    private final String USER_ACCOUNT_LIST = "userAccountList";
    private final String USER_NEW = "userNewList";
    private final String USER_VIEW = "viewUserList";
    private final String EDIT_USER_LIST = "userEditList";
    private final int pageSize = 5;
    @Autowired
    private IUserAccountService iUserAccountService;


    @PostMapping("/user/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String registerPatient(@Valid UserNewDto userNewDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userNewDto", userNewDto);
            return USER_NEW;
        }
        iUserAccountService.saveUserAccount(userNewDto);
        return "redirect:/user/";
    }

    @GetMapping("/user/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String registerPage(UserNewDto userNewDto, Model model) {
        if (userNewDto == null) {
            userNewDto = new UserNewDto();
        }
        model.addAttribute("newUser", userNewDto);
        return USER_NEW;
    }


    @PostMapping("/user")
    public String getListUsers(@RequestParam(required = false, name = "pageNo") Integer pageNo, Model model) {
        return getListAccountUsers(pageNo, model, null, null);
    }

    @GetMapping("/user")
    public String getListAccountUsers(@RequestParam(required = false, name = "pageNo") Integer pageNo,
                                      Model model,
                                      @RequestParam(required = false, name = "userName") String userName,
                                      @RequestParam(required = false, name = "role") String role) {
        if (pageNo == null) {
            pageNo = Integer.valueOf(1);
        }
        Page<UserAccount> userAccountsPage = iUserAccountService.findAllUsers(pageNo, pageSize, userName, role);
        List<UserAccount> userAccountList = userAccountsPage.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", userAccountsPage.getTotalPages());
        model.addAttribute("totalItems", userAccountsPage.getTotalElements());
        model.addAttribute("usersList", userAccountList);
        return USER_ACCOUNT_LIST;
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable(value = "id") Integer id, @RequestParam(required = false, name = "statusView") StatusView statusView, Model model) {
        UserAccountDto userAccountDto = iUserAccountService.getOneUser(id);
        if (statusView != null) {
            model.addAttribute("UserAccountDto", iUserAccountService.setNewStatusUserAndSave(statusView, userAccountDto));
            return USER_VIEW;
        }
        model.addAttribute("UserAccountDto", userAccountDto);
        return USER_VIEW;
    }

    @GetMapping("/user/{id}/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        UserAccountDto userAccountDto = iUserAccountService.getOneUser(id);
        model.addAttribute("userAccountDto", userAccountDto);
        return EDIT_USER_LIST;
    }


    @PostMapping("/user/{id}/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public String editUser(@PathVariable("id") Integer id, @Valid UserAccountDto userAccountDto,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userAccountDto", userAccountDto);
            return EDIT_USER_LIST;
        }
        iUserAccountService.editUser(userAccountDto);
        return "redirect:/user/" + id;
    }
}
