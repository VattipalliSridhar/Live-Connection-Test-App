package com.apps.sampletestapp.view.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;

import com.apps.sampletestapp.R;
import com.apps.sampletestapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonRegister.setEnabled(false);
        binding.editTextTextEmailAddress.addTextChangedListener(loginTextWatcher);
        binding.editTextTextPassword.addTextChangedListener(loginTextWatcher);

        binding.buttonRegister.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, MainActivity.class)));
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String usernameInput = binding.editTextTextEmailAddress.getText().toString().trim();
            String passwordInput = binding.editTextTextPassword.getText().toString().trim();

            binding.buttonRegister.setEnabled(!emailValidator(usernameInput) && !validatePassword(passwordInput));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    private boolean validatePassword(String passwordInput) {
        if (passwordInput.length() < 12 && passwordInput.length() >= 6) {
            return false;
        }
        return true;
    }

    private boolean emailValidator(String usernameInput) {

        if (!usernameInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(usernameInput).matches()) {
            //Toast.makeText(this, "Email Verified !", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}