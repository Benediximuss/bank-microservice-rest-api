package com.uguroztunc.load;

import com.uguroztunc.model.Account;

public class AccountOutLoad {
	
	private String message;
	private Account Data;
	
	
	public AccountOutLoad() {}

	public AccountOutLoad(String message, Account data) {
		super();
		this.message = message;
		Data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Account getData() {
		return Data;
	}

	public void setData(Account data) {
		Data = data;
	}

}
