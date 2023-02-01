package com.uguroztunc.load;

import com.uguroztunc.model.AccountSummary;

public class AccountSummaryLoad {
	
	private String message;
	private AccountSummary Data;
	
	
	public AccountSummaryLoad() {}


	public AccountSummaryLoad(String message, AccountSummary data) {
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


	public AccountSummary getData() {
		return Data;
	}


	public void setData(AccountSummary data) {
		Data = data;
	}
	
		
	
}
