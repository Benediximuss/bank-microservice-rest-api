package com.uguroztunc.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.uguroztunc.model.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String>{
	
}
