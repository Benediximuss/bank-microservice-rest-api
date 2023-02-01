package com.uguroztunc.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountSummary {
	
	private String id;
	private String owner;
	private LocalDateTime createDate;
	private List<Transaction> transactionsOut = new ArrayList<>();;
	private List<Transaction> transactionsIn = new ArrayList<>();;
	private Double balance;
	
	
	public AccountSummary() {}
	
	public AccountSummary(String id, String owner, LocalDateTime createDate) {
		super();
		this.id = id;
		this.owner = owner;
		this.createDate = createDate;
		this.transactionsOut = new ArrayList<>();
		this.transactionsIn = new ArrayList<>();
		this.balance = 0.0;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public List<Transaction> getTransactionsOut() {
		return transactionsOut;
	}

	public void setTransactionsOut(List<Transaction> transactionsOut) {
		this.transactionsOut = transactionsOut;
	}

	public List<Transaction> getTransactionsIn() {
		return transactionsIn;
	}

	public void setTransactionsIn(List<Transaction> transactionsIn) {
		this.transactionsIn = transactionsIn;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	public void insertToIn(Transaction transaction)
	{
		this.transactionsIn.add(transaction);
	}
	
	public void insertToOut(Transaction transaction)
	{
		this.transactionsOut.add(transaction);
	}
	
	
}
