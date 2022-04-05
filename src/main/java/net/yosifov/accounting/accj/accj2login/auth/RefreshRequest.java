package net.yosifov.accounting.accj.accj2login.auth;

public class RefreshRequest {

    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "RefreshRequest{" +
                "refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
