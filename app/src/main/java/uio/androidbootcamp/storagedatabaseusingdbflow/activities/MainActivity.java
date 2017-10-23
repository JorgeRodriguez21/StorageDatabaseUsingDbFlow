package uio.androidbootcamp.storagedatabaseusingdbflow.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import uio.androidbootcamp.storagedatabaseusingdbflow.R;
import uio.androidbootcamp.storagedatabaseusingdbflow.handlers.UsersHandler;
import uio.androidbootcamp.storagedatabaseusingdbflow.models.User;
import uio.androidbootcamp.storagedatabaseusingdbflow.repositories.UserDataBaseRepository;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edit_text_name)
    protected EditText nameEditText;

    @BindView(R.id.edit_text_username)
    protected EditText usernameEditText;

    @BindView(R.id.edit_text_age)
    protected EditText ageEditText;

    @BindView(R.id.edit_text_password)
    protected EditText passwordEditText;

    @BindView(R.id.button_sign_in)
    protected TextView signInButton;

    @BindView(R.id.button_sign_up)
    protected Button signUpButton;

    private UsersHandler usersHandler;

    private boolean isThereNotValidFields;

    private UserDataBaseRepository userDataBaseRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        userDataBaseRepository = new UserDataBaseRepository();
        usersHandler = new UsersHandler(userDataBaseRepository);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLogin();
            }
        });
        setTextToSignInLink();
    }

    private void setTextToSignInLink() {
        SpannableString content = new SpannableString(getString(R.string.sign_in_invite));
        content.setSpan(new UnderlineSpan(), 0, getString(R.string.sign_in_invite).length(), 0);
        signInButton.setText(content);
        signInButton.setTextColor(ContextCompat.getColor(this, R.color.red));
    }

    public void checkNonEmptyFields(EditText editText) {
        if (usersHandler.checkEmptyString(editText.getText().toString())) {
            editText.setError(getString(R.string.field_can_not_be_empty));
            isThereNotValidFields = true;
        } else {
            isThereNotValidFields = false;
        }
    }

    public void checkPasswordCorrectness(EditText editText) {
        if (!usersHandler.checkValidPassword(editText.getText().toString())) {
            editText.setError(getString(R.string.password_requirement));
            isThereNotValidFields = true;
        } else {
            isThereNotValidFields = false;
        }
    }

    public void registerUser() {
        checkNonEmptyFields(usernameEditText);
        checkNonEmptyFields(nameEditText);
        checkNonEmptyFields(ageEditText);
        checkPasswordCorrectness(passwordEditText);
        if (!isThereNotValidFields) {
            User user = new User(nameEditText.getText().toString(), usernameEditText.getText().toString(),
                    passwordEditText.getText().toString(), Integer.parseInt(ageEditText.getText().toString()));
            boolean isUserSaved = usersHandler.saveInDatabase(user);
            if(isUserSaved){
                Toast.makeText(this, R.string.user_creation_successfull, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, UsersActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(this, R.string.error_creating_user, Toast.LENGTH_LONG).show();
            }
        }
    }

    public void navigateToLogin(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
