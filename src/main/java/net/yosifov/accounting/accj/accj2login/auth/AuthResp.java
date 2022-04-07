package net.yosifov.accounting.accj.accj2login.auth;

public class AuthResp {

    private String token;
    private long issuedAt;
    private long expiresAt;
    private String refreshToken;
    private String issuedAtTime;
    private String expiresAtTime;

    public AuthResp() {
    }

    public AuthResp(String token,
                    long issuedAt,
                    long expiresAt,
                    String refreshToken,
                    String issuedAtTime,
                    String expiresAtTime) {
        this.token = token;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
        this.refreshToken = refreshToken;
        this.issuedAtTime = issuedAtTime;
        this.expiresAtTime = expiresAtTime;
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

    public String getIssuedAtTime() {
        return issuedAtTime;
    }

    public void setIssuedAtTime(String issuedAtTime) {
        this.issuedAtTime = issuedAtTime;
    }

    public String getExpiresAtTime() {
        return expiresAtTime;
    }

    public void setExpiresAtTime(String expiresAtTime) {
        this.expiresAtTime = expiresAtTime;
    }
}
