package com.uguroztunc.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.uguroztunc.model.Account;

public interface AccountRepository extends MongoRepository<Account, String>{
	public Account findByid(String id);
}
