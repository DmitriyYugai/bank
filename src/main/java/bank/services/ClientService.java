package bank.services;

import bank.models.Client;
import bank.repositories.ClientDao;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ClientService {
    private final ClientDao clientDao;

    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public List<Client> findAllClients() {
        List<Client> rsl = clientDao.findAll();
        rsl.sort(Comparator.comparing(Client::getId));
        return rsl;
    }

    public Client saveClient(Client client) {
        return clientDao.save(client);
    }
}
