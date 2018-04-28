package com.dekinci.lksbstu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.dekinci.lksbstu.utils.FactCallback;
import com.dekinci.lksbstu.utils.Synchronizer;
import com.example.hackaton.goprojectpisat.R;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;


public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView loginView;
    private EditText passwordView;
    private View progressView;
    private View loginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginView = findViewById(R.id.email);
        populateAutoComplete();

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
        progressView = findViewById(R.id.login_progress);
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts())
            return;

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED)
            return true;

        if (shouldShowRequestPermissionRationale(READ_CONTACTS))
            Snackbar.make(loginView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, v ->
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS));
        else
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);

        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS &&
                grantResults.length == 1 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED)
            populateAutoComplete();
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        loginView.setError(null);
        passwordView.setError(null);

        // Store values at the time of the login attempt.
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
            showProgress(true);
            mAuthTask = new UserLoginTask(login, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isLoginValid(String login) {
        //TODO
        return true;
    }

    private boolean isPasswordValid(String password) {
        //TODO
        return true;
    }

    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        loginFormView.animate().setDuration(shortAnimTime).alpha(show ? 0 : 1)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                    }
                });

        progressView.setVisibility(show ? View.VISIBLE : View.GONE);
        progressView.animate().setDuration(shortAnimTime).alpha(show ? 1 : 0)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                    }
                });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                Uri.withAppendedPath(
                        ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,
                ContactsContract.Contacts.Data.MIMETYPE + " = ?",
                new String[]{ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE},
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                LoginActivity.this,
                android.R.layout.simple_dropdown_item_1line,
                emailAddressCollection);
        loginView.setAdapter(adapter);
    }

    private void proceed() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
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
            showProgress(false);

            if (success)
                proceed();
            else {
                passwordView.setError(getString(R.string.error_incorrect_password));
                passwordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

