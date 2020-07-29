package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
        if (user.getCar() != null) {
            sessionFactory.getCurrentSession().save(user.getCar());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserByCar(Car car) {
        String hql =" FROM User WHERE car=:car";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("car",car);
       // Query query = sessionFactory.getCurrentSession().createQuery("from User where car=:car").setParameter("car", car).setMaxResults(1);
        return (User) query.getSingleResult();
    }
/*
 String hql = "select f FROM Family f  WHERE (:person in elements(f.children))";
 Query query = session.createQuery(hql);
 query.setEntity("person",somePersonObject);
 */

}
