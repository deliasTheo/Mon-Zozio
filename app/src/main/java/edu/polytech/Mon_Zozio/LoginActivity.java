package edu.polytech.Mon_Zozio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText mPseudo;
    private EditText mMdp;
    private Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPseudo = findViewById(R.id.login_pseudo_EditText);
        mMdp = findViewById(R.id.login_mdp_EditText);
        mLogin = findViewById(R.id.login_login_Button);




        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The user just clicked
                Intent mainActivityIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(mainActivityIntent);
            }
        });
    }
}