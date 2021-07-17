package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Роберт", "Левандовский", "robert@mail.ru",
              new Car("Ауди С8", 2020)));
      userService.add(new User("Лионель", "Месси", "lionel@mail.ru",
              new Car("Мерседес-Бенз Ситан", 2012)));
      userService.add(new User("Кевин", "Де Брёйне", "kevin@mail.ru",
              new Car("Газ Волга Сибер", 2008)));
      userService.add(new User("Криштиану", "Роналду", "cristiano@mail.ru",
              new Car("Самсунг СМ3", 2012)));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      userService.getUserByModelAndSeriesCar("Самсунг см3", 2012);

      context.close();
   }
}
