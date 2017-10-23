package uio.androidbootcamp.storagedatabaseusingdbflow.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import uio.androidbootcamp.storagedatabaseprojectwithdbflow.R;
import uio.androidbootcamp.storagedatabaseprojectwithdbflow.handlers.RegisterUserHandler;
import uio.androidbootcamp.storagedatabaseprojectwithdbflow.models.User;
import uio.androidbootcamp.storagedatabaseprojectwithdbflow.services.UserDataBaseService;

/**
 * Created by jrodri on 7/7/17.
 */

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edit_text_username)
    protected EditText userNameEditText;

    @BindView(R.id.edit_text_password)
    protected EditText passwordEditText;

    @BindView(R.id.button_sign_in)
    protected Button signInButton;

    private RegisterUserHandler registerUserHandler;

    private UserDataBaseService userDataBaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        userDataBaseService = new UserDataBaseService();
        registerUserHandler = new RegisterUserHandler(userDataBaseService);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login() {
        String username = userNameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        User user = new User(username, password);
        if (registerUserHandler.login(user)) {
            Toast.makeText(this, R.string.successfull_login, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, UsersActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.failed_login, Toast.LENGTH_LONG).show();
            passwordEditText.setText("");
        }
    }
}
