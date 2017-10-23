package uio.androidbootcamp.storagedatabaseusingdbflow.repositories;


import uio.androidbootcamp.storagedatabaseusingdbflow.models.User;

/**
 * Created by jrodri on 7/7/17.
 */
public class UserDataBaseRepository extends DataBaseRepository<User> {

    public UserDataBaseRepository() {
        super(User.class);
    }
}
