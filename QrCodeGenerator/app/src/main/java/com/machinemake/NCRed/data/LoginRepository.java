package com.machinemake.NCRed.data;

import com.example.NCRed.data.model.LoggedInUser;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private com.machinemake.NCRed.data.LoginDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private LoggedInUser user = null;

    // private constructor : singleton access
    private LoginRepository(com.machinemake.NCRed.data.LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(com.machinemake.NCRed.data.LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public com.machinemake.NCRed.data.Result<LoggedInUser> login(String username, String password) {
        // handle login
        com.machinemake.NCRed.data.Result<LoggedInUser> result = dataSource.login(username, password);
        if (result instanceof com.machinemake.NCRed.data.Result.Success) {
            setLoggedInUser(((com.machinemake.NCRed.data.Result.Success<LoggedInUser>) result).getData());
        }
        return result;
    }
}