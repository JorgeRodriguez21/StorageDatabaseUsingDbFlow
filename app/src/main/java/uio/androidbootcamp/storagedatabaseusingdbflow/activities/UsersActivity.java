package uio.androidbootcamp.storagedatabaseusingdbflow.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uio.androidbootcamp.storagedatabaseusingdbflow.R;
import uio.androidbootcamp.storagedatabaseusingdbflow.adapters.UsersRecyclerAdapter;
import uio.androidbootcamp.storagedatabaseusingdbflow.handlers.UsersHandler;
import uio.androidbootcamp.storagedatabaseusingdbflow.models.User;
import uio.androidbootcamp.storagedatabaseusingdbflow.repositories.UserDataBaseRepository;

/**
 * Created by jrodri on 7/7/17.
 */

public class UsersActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view_users)
    protected RecyclerView countryRV;

    private List<User> users;

    private UsersHandler usersHandler;

    private UsersRecyclerAdapter usersRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        ButterKnife.bind(this);
        usersHandler = new UsersHandler(new UserDataBaseRepository());
        users = usersHandler.getAllUsersFromDatabase();
        initializeAdapter();
    }

    private void initializeAdapter() {
        countryRV.setLayoutManager(new LinearLayoutManager(this));
        usersRecyclerAdapter = new UsersRecyclerAdapter(this, users);
        usersRecyclerAdapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User selectedUser = (User) v.getTag();
                if (selectedUser != null) {
                    String messageText = buildMessage(selectedUser);
                    Toast.makeText(getApplicationContext(), messageText, Toast.LENGTH_LONG).show();
                }
            }
        });
        countryRV.setAdapter(usersRecyclerAdapter);
    }

    private String buildMessage(User selectedUser) {
        return selectedUser.getUsername() + " " + selectedUser.getAge();
    }

}
