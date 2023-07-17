package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.model.util.Commands;

import java.util.List;
import java.util.Scanner;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com;

        while (true) {
            String command = prompt("Comands: ");
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    String firstName = prompt("Name: ");
                    String lastName = prompt("Surname: ");
                    String phone = prompt("Number: ");
                    userController.saveUser(new User(firstName, lastName, phone));
                    break;
                case READ:
                    String id = prompt("id: ");
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case LIST:
                    try {
                        List<User> users = userController.readAllUsers();
                        System.out.println(users);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case UPDATE:
                    try {
                        String name = prompt("Name: ");
                        String lstName = prompt("Surname: ");
                        String number = prompt("Number: ");
                        Long userid = Long.parseLong(prompt("Введите id"));
                        User updated = new User(name, lstName, number);
                        userController.updateUser(userid, updated);
                    } catch (Exception e) {
                        throw new RuntimeException();
                    }
                    break;
                case DELETE:
                    id = prompt("id: ");
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        userController.deleteUser(user, id);
                    } catch (Exception e) {
                        throw new RuntimeException("id is not");
                    }
                    break;
                case NONE:
                    System.out.println("Режим ожидания");
                    String s = prompt("Нажмите ENTER кнопку чтобы прдолжить");
                    break;
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
