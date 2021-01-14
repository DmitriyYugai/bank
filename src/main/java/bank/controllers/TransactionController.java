package bank.controllers;

import bank.ex.NoSuchAccountException;
import bank.ex.NotEnoughMoneyException;
import bank.models.Transaction;
import bank.services.ClientService;
import bank.services.TransactionService;
import bank.services.TypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TransactionController {
    private final TypeService typeService;
    private final ClientService clientService;
    private final TransactionService transactionService;

    public TransactionController(TypeService typeService, ClientService clientService, TransactionService transactionService) {
        this.typeService = typeService;
        this.clientService = clientService;
        this.transactionService = transactionService;
    }

    @GetMapping("/transaction")
    public String getAll(Model model) {
        model.addAttribute("transactions", transactionService.findAllTransactions());
        model.addAttribute("clients", clientService.findAllClients());
        return "transactions";
    }

    @GetMapping("/transaction/filter")
    public ResponseEntity<String> filter(@RequestParam("id") int id) {
        String rsl = transactionService.findTransactionsByClientId(id);
        return new ResponseEntity<>(rsl, HttpStatus.OK);
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("clientId") int clientId,
                       @RequestParam("number") int number,
                       @RequestParam(name = "errNoMoney", required = false) String errNoMoney,
                       @RequestParam(name = "errNoAcc", required = false) String errNoAcc,
                       Model model) {
        if (errNoMoney != null) {
            model.addAttribute("errNoMoney", "Недостаточно средств");
        }
        if (errNoAcc != null) {
            model.addAttribute("errNoAcc", "Счёт с указанным номером не существует в системе");
        }
        model.addAttribute("types", typeService.findAllTypes());
        model.addAttribute("clientId", clientId);
        model.addAttribute("number", number);
        return "edit";
    }

    @PostMapping("/transaction")
    @Transactional
    public String save(@ModelAttribute Transaction transaction) throws NotEnoughMoneyException, NoSuchAccountException {
        System.out.println(transaction);
        transactionService.doTransaction(transaction);
        System.out.println(transaction);
        return "redirect:/account?id=" + transaction.getClient().getId();
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    public String handleNotEnoughMoneyException(HttpServletRequest request) {
        return "redirect:/edit?clientId=" + request.getParameter("client.id") +
                "&number=" + request.getParameter("number") + "&errNoMoney=true";
    }

    @ExceptionHandler(NoSuchAccountException.class)
    public String handleNoSuchAccountException(HttpServletRequest request) {
        return "redirect:/edit?clientId=" + request.getParameter("client.id") +
                "&number=" + request.getParameter("number") + "&errNoAcc=true";
    }
}
