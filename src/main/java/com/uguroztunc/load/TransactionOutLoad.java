package com.uguroztunc.load;

import com.uguroztunc.model.Transaction;

public class TransactionOutLoad {
	
	private String message;
	private Transaction Data;
	
	
	public TransactionOutLoad() {}

	public TransactionOutLoad(String message, Transaction data) {
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

	public Transaction getData() {
		return Data;
	}

	public void setData(Transaction data) {
		Data = data;
	}
	
}
