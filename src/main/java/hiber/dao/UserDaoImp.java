package hiber.dao;

import hiber.model.User;
import hiber.model.Car;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    public List<User> listUsers() {
        Query<User> query = sessionFactory.getCurrentSession().createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User getUserByCar(String model, int series) {
        return sessionFactory.getCurrentSession().
                createQuery("from Car where model= :cM and series= :cS", Car.class).
                setParameter("cM", model).setParameter("cS", series).
                getSingleResult().getUser();
    }
}
