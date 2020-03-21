package com.hbr.pojo.vo;

public class AdminVO {
    private String id;

    private String username;

    private String password;
    
   

	public AdminVO( String username, String password, String adminToken) {
		super();
		
		this.username = username;
		this.password = password;
		this.adminToken = adminToken;
	}

	public String getAdminToken() {
		return adminToken;
	}

	public void setAdminToken(String adminToken) {
		this.adminToken = adminToken;
	}

	private String adminToken;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}