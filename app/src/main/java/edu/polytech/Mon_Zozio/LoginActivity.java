package edu.polytech.Mon_Zozio;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.snackbar.Snackbar;

import edu.polytech.Mon_Zozio.databinding.ActivityLoginBinding;

import java.util.Arrays;
import java.util.List;

//public class LoginActivity extends AppCompatActivity {
public class LoginActivity extends BaseActivity<ActivityLoginBinding> {
    private static final int RC_SIGN_IN = 123;
    /*private EditText mPseudo;
    private EditText mMdp;*/
    private Button mloginButton;

    @Override
    ActivityLoginBinding getViewBinding() {
        return ActivityLoginBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*mPseudo = findViewById(R.id.login_pseudo_EditText);
        mMdp = findViewById(R.id.login_mdp_EditText);*/
        mloginButton = findViewById(R.id.loginButton);

        setupListeners();
    }

    private void setupListeners(){
        // Login Button
        mloginButton.setOnClickListener(view -> {
            startSignInActivity();
        });
    }


    //Lauch Sign-in activity
    private void startSignInActivity(){



        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.EmailBuilder().build());

        //Collections.singletonList(new AuthUI.IdpConfig.EmailBuilder().build());

        // Launch the activity
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        //.setTheme(R.style.LoginTheme)
                        .setAvailableProviders(providers)
                        .setIsSmartLockEnabled(false, true)
                        .setLogo(R.drawable.logo)
                        .build(),
                RC_SIGN_IN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.handleResponseAfterSignIn(requestCode, resultCode, data);
    }


    // Method that handles response after SignIn Activity close
    private void handleResponseAfterSignIn(int requestCode, int resultCode, Intent data){

        IdpResponse response = IdpResponse.fromResultIntent(data);

        if (requestCode == RC_SIGN_IN) {
            // SUCCESS
            if (resultCode == RESULT_OK) {
                //userManager.createUser();
                showSnackBar(getString(R.string.connection_succeed));
            } else {
                // ERRORS
                if (response == null) {
                    showSnackBar(getString(R.string.error_authentication_canceled));
                } else if (response.getError()!= null) {
                    if(response.getError().getErrorCode() == ErrorCodes.NO_NETWORK){
                        showSnackBar(getString(R.string.error_no_internet));
                    } else if (response.getError().getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                        showSnackBar(getString(R.string.error_unknown_error));
                    }
                }
            }
        }
    }

    // Show Snack Bar with a message
    private void showSnackBar( String message){
        //Snackbar.make(binding.loginLayout, message, Snackbar.LENGTH_SHORT).show();
        Log.d(TAG, "signInWithEmail:success");
    }


    /*mLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // The user just clicked
            Intent mainActivityIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(mainActivityIntent);
        }
    });*/
}