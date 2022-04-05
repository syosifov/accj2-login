package net.yosifov.accounting.accj.accj2login.auth;

public class AuthResp {

    private String token;
    private long issuedAt;
    private long expiresAt;
    private String refreshToken;

    public AuthResp() {
    }

    public AuthResp(String token,
                    long issuedAt,
                    long expiresAt,
                    String refreshToken) {
        this.token = token;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public long getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(long issuedAt) {
        this.issuedAt = issuedAt;
    }
}
