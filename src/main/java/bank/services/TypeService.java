package bank.services;

import bank.models.Type;
import bank.repositories.TypeDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {
    private final TypeDao typeDao;

    public TypeService(TypeDao typeDao) {
        this.typeDao = typeDao;
    }

    public List<Type> findAllTypes() {
        return typeDao.findAll();
    }

}
