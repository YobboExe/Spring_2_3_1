package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    @Override
    public List<User> findAll() {
        TypedQuery<User> query = entityManagerFactory.createEntityManager().createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User fineOne(int id) {
        return entityManagerFactory.createEntityManager().find(User.class, id);
    }


    @Override
    public void save(User user) {
        entityManagerFactory.createEntityManager().persist(user);
    }

    @Override
    public void update(int id, User updatedUser) {
        entityManagerFactory.createEntityManager().createQuery("FROM User WHERE id =: paramName");
    }

    @Override
    public void delete(int id) {
        entityManagerFactory.createEntityManager().createQuery("DELETE FROM User user WHERE user.id =: id");
    }
}
