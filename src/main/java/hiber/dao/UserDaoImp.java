package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
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
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void getUserByModelAndSeriesCar(String model, int series) {
      String hql = "SELECT car FROM Car car WHERE car.model=:model AND car.series=:series";
      Car car = sessionFactory.getCurrentSession().createQuery(hql, Car.class)
              .setParameter("model", model)
              .setParameter("series", series).uniqueResult();
      User user = car.getUser();
      System.out.println(user);
    }

}
