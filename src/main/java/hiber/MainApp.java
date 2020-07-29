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

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));

        Car car1 = new Car("car1",11);
        Car car2 = new Car("car2",12);

        //userService.add(new User("User5", "Lastname5", "user5@mail.ru", new Car("car1", 11)));
        //userService.add(new User("User6", "Lastname6", "user6@mail.ru", new Car("car2", 12)));


        userService.add(new User("User5", "Lastname5", "user5@mail.ru", car1));
        userService.add(new User("User6", "Lastname6", "user6@mail.ru", car2));

       List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();

          //  if (user.getCar() != null) {
              //  user = userService.getUserByCar(user.getCar());
                     User user1 = userService.getUserByCar(car1);
                System.out.println("getUserByCar: " + user1.getFirstName()+ " " + user1.getLastName()+" "+user1.getEmail()+" "+user1.getCar());
           // }
        }

      /*  User user = new User();
        if (user.getCar() != null) {
            user = userService.getUserByCar(user.getCar());
          //  User user = userService.getUserByCar(car1);
            System.out.println("getUserByCar: " + user);
        }
*/

        context.close();
    }
}

