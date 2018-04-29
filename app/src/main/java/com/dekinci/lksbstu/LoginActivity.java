package com.dekinci.lksbstu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.dekinci.lksbstu.model.PolyManager;
import com.dekinci.lksbstu.utils.FactCallback;
import com.dekinci.lksbstu.utils.Synchronizer;
import com.example.hackaton.goprojectpisat.R;
import com.wang.avi.AVLoadingIndicatorView;


public class LoginActivity extends AppCompatActivity {
    private UserLoginTask mAuthTask = null;

    private AutoCompleteTextView loginView;
    private EditText passwordView;

    private View loginFormView;
    private AVLoadingIndicatorView loadingIndicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (PolyManager.get().getApi().isLoggedIn()) {
            proceed();
            return;
        }

        loadingIndicatorView = findViewById(R.id.avi);

        loginView = findViewById(R.id.login);
        passwordView = findViewById(R.id.password);
        passwordView.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin();
                return true;
            }
            return false;
        });

        Button loginSignInButton = findViewById(R.id.login_sign_in_button);
        loginSignInButton.setOnClickListener(view -> attemptLogin());

        loginFormView = findViewById(R.id.login_form);
        loginFormView.setVisibility(View.VISIBLE);
    }

    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        loginView.setError(null);
        passwordView.setError(null);

        String login = loginView.getText().toString();
        String password = passwordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(login)) {
            loginView.setError(getString(R.string.error_field_required));
            focusView = loginView;
            cancel = true;
        } else if (!isLoginValid(login)) {
            loginView.setError(getString(R.string.error_invalid_email));
            focusView = loginView;
            cancel = true;
        }

        if (cancel)
            focusView.requestFocus();
        else {
            showProgress();
            mAuthTask = new UserLoginTask(login, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isLoginValid(String login) {
        return true;
    }

    private boolean isPasswordValid(String password) {
        return true;
    }

    private void showProgress() {
        loginFormView.setVisibility(View.VISIBLE);
        loginFormView.setAlpha(1);
        loginFormView.animate()
                .setDuration(100)
                .y(0)
                .alpha(0)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        loginFormView.setVisibility(View.INVISIBLE);
                        loadingIndicatorView.setY(0);
                        loadingIndicatorView.setAlpha(0);
                        loadingIndicatorView.setVisibility(View.VISIBLE);
                        loadingIndicatorView.animate()
                                .setDuration(100)
                                .y(200)
                                .alpha(1)
                                .start();

                        loginFormView.animate().setListener(null);
                    }
                })
                .start();
    }

    private void hideProgress() {
        loadingIndicatorView.setAlpha(1);
        loadingIndicatorView.setVisibility(View.VISIBLE);
        loadingIndicatorView.animate()
                .setDuration(100)
                .y(0)
                .alpha(0)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        loadingIndicatorView.setVisibility(View.INVISIBLE);
                        loginFormView.setAlpha(0);
                        loginFormView.setVisibility(View.VISIBLE);
                        loginFormView.animate()
                                .setDuration(100)
                                .y(200)
                                .alpha(1)
                                .start();
                        loadingIndicatorView.animate().setListener(null);
                    }
                })
                .start();
    }

    private void proceed() {
        PolyManager.get().setUser();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @SuppressLint("StaticFieldLeak")
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            Synchronizer<Boolean> synchronizer = new Synchronizer<>();
            PolyManager.get().getApi().login(mEmail, mPassword, new FactCallback() {
                @Override
                public void success(Void aVoid) {
                    synchronizer.set(true);
                }

                @Override
                public void failure(Throwable throwable) {
                    synchronizer.set(false);
                }
            });

            return synchronizer.get();
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;

            if (success)
                proceed();
            else {
                hideProgress();
                passwordView.setError(getString(R.string.error_incorrect_password));
                passwordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            hideProgress();
        }
    }
}

