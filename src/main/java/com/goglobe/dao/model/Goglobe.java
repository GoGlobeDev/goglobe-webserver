package com.goglobe.dao.model;

public class Goglobe {
    private Integer id;

    private String account;

    private String email;

    private String code;

    private String invitedCode;

    private Integer money;

    private Integer status;
    
    private String phone;
    
    public Goglobe() {
		super();
	}
    
	public Goglobe(String account, String code, String invitedCode, Integer status) {
		super();
		this.account = account;
		this.code = code;
		this.invitedCode = invitedCode;
		this.status = status;
	}
	
	public Goglobe(String account, Integer money, Integer status) {
		super();
		this.account = account;
		this.money = money;
		this.status = status;
	}
	
	public Goglobe(String account, String email) {
		super();
		this.account = account;
		this.email = email;
	}

	public Goglobe(String invitedCode, Integer money) {
		super();
		this.invitedCode = invitedCode;
		this.money = money;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInvitedCode() {
        return invitedCode;
    }

    public void setInvitedCode(String invitedCode) {
        this.invitedCode = invitedCode;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}