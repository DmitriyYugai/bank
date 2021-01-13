package bank.controllers;

import bank.models.Client;
import bank.services.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping({"/", "/index"})
    public String getAll(Model model) {
        model.addAttribute("clients", clientService.findAllClients());
        return "clients";
    }

    @PostMapping("/client")
    public String save(@ModelAttribute Client client) {
        clientService.saveClient(client);
        return "redirect:/";
    }

}
