package bank.controllers;

import bank.models.Account;
import bank.services.AccountService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account")
    public String getAll(@RequestParam("id") int clientId,
                         @RequestParam(name = "error", required = false) String error,
                         Model model) {
        model.addAttribute("accounts", accountService.findAccountsByClientId(clientId));
        model.addAttribute("clientId", clientId);
        if (error != null) {
            model.addAttribute("errorMessage", "Счёт с указанным номером уже существует в системе");
        }
        return "accounts";
    }

    @GetMapping("/account/json")
    public ResponseEntity<String> getAccountsJson() {
        String rsl = accountService.findAllAccounts();
        return new ResponseEntity<>(rsl, HttpStatus.OK);
    }

    @PostMapping("/account")
    public String save(@ModelAttribute Account account) {
        accountService.saveAccount(account);
        return "redirect:/account?id=" + account.getClientId();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleAccountNumberDuplicateException(Model model, HttpServletRequest request) {
        return "redirect:/account?id=" + request.getParameter("clientId") + "&error=true";
    }
}
