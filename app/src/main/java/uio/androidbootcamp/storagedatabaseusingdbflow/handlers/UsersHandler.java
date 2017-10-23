package uio.androidbootcamp.storagedatabaseusingdbflow.handlers;


import java.util.ArrayList;
import java.util.List;

import uio.androidbootcamp.storagedatabaseusingdbflow.models.User;
import uio.androidbootcamp.storagedatabaseusingdbflow.repositories.UserDataBaseRepository;

/**
 * Created by jrodri on 6/7/17.
 */

public class UsersHandler {

    private final static String passwordFormat = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_])(?=\\S+$).{8,}$";

    private UserDataBaseRepository userDataBaseRepository;
    private List<User> users;

    public UsersHandler(UserDataBaseRepository userDataBaseRepository) {
        this.userDataBaseRepository = userDataBaseRepository;
        users = getUsers();
    }

    public boolean checkUserIsAnAdult(int age) {
        return age >= 18;
    }

    public boolean checkEmptyString(String data) {
        return data.isEmpty();
    }

    public boolean checkValidPassword(String password) {
        return password.matches(passwordFormat);
    }

    public boolean saveInDatabase(User user) {
        return userDataBaseRepository.saveEntity(user);
    }
    public boolean removeFromDatabase(User user) {
        return userDataBaseRepository.deleteEntity(user);
    }

    public boolean login(User user) {
        return userDataBaseRepository.entityExists(user);
    }

    public List<User> getAllUsersFromDatabase() {
        return userDataBaseRepository.getAllRecords();
    }

    private List<User> getUsers() {
        User user1 = new User("Jorge", "jorgito", "password1", 18);
        User user2 = new User("Luz", "luzma", "password2", 21);
        User user3 = new User("Diego", "dieguito", "password3", 24);
        User user4 = new User("Carlos", "carlitos", "password4", 48);
        User user5 = new User("Sebastian", "sebitas", "password8", 14);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        return users;
    }

    private User getUserToSave() {
        int minimumValue = 0;
        int maximumValue = users.size();
        int randomNum = minimumValue + (int) (Math.random() * maximumValue);
        return users.get(randomNum);
    }

    public boolean saveRandomUser() {
        return saveInDatabase(getUserToSave());
    }

}
