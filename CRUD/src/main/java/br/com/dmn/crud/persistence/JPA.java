package br.com.dmn.crud.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPA {
    
    private static final String PERSISTENCE_UNIT = "CRUD-PU";
    
    private static EntityManager _manager;
    private static EntityManagerFactory _factory;
    
    public static EntityManager getEntityManager() {
        if (_factory == null || !_factory.isOpen()) {
            _factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
        if (_manager == null || !_manager.isOpen()) {
            _manager = _factory.createEntityManager();
        }
        return _manager;
    }
    
    public static void closeEntityManager() {
        if (_manager.isOpen() && _manager != null) {
            _manager.close();
            _factory.close();
        }
    }
    
}
