package com.uguroztunc.load;

import java.util.ArrayList;
import java.util.List;

import com.uguroztunc.model.Transaction;

public class TransactionListOutLoad {
	
	private String message;
	private List<Transaction> Data = new ArrayList<>();
	
	
	public TransactionListOutLoad() {}
	
	public TransactionListOutLoad(String message, List<Transaction> data) {
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

	public List<Transaction> getData() {
		return Data;
	}

	public void setData(List<Transaction> data) {
		Data = data;
	}
	
	public void insert(Transaction transaction)
	{
		Data.add(transaction);
	}

}
