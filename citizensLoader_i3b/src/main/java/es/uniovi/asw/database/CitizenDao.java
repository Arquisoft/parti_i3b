package es.uniovi.asw.database;

import java.util.List;

import es.uniovi.asw.parser.User;

/**
 * Interface for the methods that the DAO must accomplish
 * 
 * @author Gonzalo de la Cruz Fernández - UO244583
 *
 */
public interface CitizenDao {

    boolean insert(User c);

    User findById(String ID);

    void remove(String ID);

    List<User> findAll();

    void cleanDatabase();
}
