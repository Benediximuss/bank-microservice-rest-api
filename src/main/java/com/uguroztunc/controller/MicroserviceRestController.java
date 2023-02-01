package com.uguroztunc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uguroztunc.load.AccountOutLoad;
import com.uguroztunc.load.AccountSummaryLoad;
import com.uguroztunc.load.TransactionInLoad;
import com.uguroztunc.load.TransactionListOutLoad;
import com.uguroztunc.load.TransactionOutLoad;
import com.uguroztunc.model.Account;
import com.uguroztunc.model.AccountSummary;
import com.uguroztunc.model.Transaction;
import com.uguroztunc.repo.AccountRepository;
import com.uguroztunc.repo.TransactionRepository;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/cs310hw1")
public class MicroserviceRestController {
	
	@Autowired private AccountRepository accountRepository;
	@Autowired private TransactionRepository transactionRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(MicroserviceRestController.class);
	
	@PostConstruct
	public void init()
	{
		if (accountRepository.count() == 0)
		{
			logger.info("Database is empty, initializing...");
			
			Account acc1 = new Account("1111", "Jack Johns");
			Account acc2 = new Account("2222", "Henry Williams");
			
			accountRepository.save(acc1);
			accountRepository.save(acc2);
			
			Transaction trans1 = new Transaction(accountRepository.findByid("1111"), accountRepository.findByid("2222"), 1500.0);
			Transaction trans2 = new Transaction(accountRepository.findByid("2222"), accountRepository.findByid("1111"), 2500.0);
			
			transactionRepository.save(trans1);
			transactionRepository.save(trans2);
			
			logger.info("All test data saved!");	
		}
		else
		{
			logger.info("Database is not empty!");	
		}
	}
	
	@GetMapping("/account")
	public List<Account> accounts()
	{
		return accountRepository.findAll();
	}
	
	@PostMapping("/account/save")
	public AccountOutLoad saveAccount(@RequestBody Account account)
	{
		AccountOutLoad output = new AccountOutLoad();
		
		if (account.getOwner() == null || account.getId() == null)
		{
			output.setMessage("ERROR:missing fields");
		}
		else
		{			
			Account accountSaved = accountRepository.save(account);
			
			output.setMessage("SUCCESS");
			output.setData(accountSaved);
		}
			
		return output;
	}	

	
	@GetMapping("/transaction")
	public List<Transaction> transactions()
	{
		return transactionRepository.findAll();
	}
	
	@PostMapping("/transaction/save")
	public TransactionOutLoad saveTransaction(@RequestBody TransactionInLoad transactionBody)
	{
		TransactionOutLoad output = new TransactionOutLoad();
		
		if (transactionBody.getFromAccountId() == null || transactionBody.getToAccountId() == null || transactionBody.getAmount() == null)
		{
			output.setMessage("ERROR:missing fields");
		}
		else
		{
			Account fromAccount = accountRepository.findByid(transactionBody.getFromAccountId());
			Account toAccount = accountRepository.findByid(transactionBody.getToAccountId());
			
			if (fromAccount != null && toAccount != null)
			{
				Transaction transaction = new Transaction(fromAccount, toAccount, transactionBody.getAmount());
				Transaction transactionSaved = transactionRepository.save(transaction);
				
				output.setMessage("SUCCESS");
				output.setData(transactionSaved);	
			}
			else
			{
				output.setMessage("ERROR: account id");
			}	
		}
		
		return output;
	}
	
	
	
	@GetMapping("/account/{accountId}")
	public AccountSummaryLoad accountSummary(@PathVariable String accountId)
	{
		AccountSummaryLoad output = new AccountSummaryLoad();
		Account targetAccount = accountRepository.findByid(accountId);
		
		if (targetAccount == null)
		{
			output.setMessage("ERROR:account doesnt exist!");
		}
		else
		{
			AccountSummary accountSummary = new AccountSummary(targetAccount.getId(), targetAccount.getOwner(), targetAccount.getCreateDate());
			List<Transaction> transList = transactionRepository.findAll();
			
			for (Transaction transaction: transList)
			{
				if (transaction.getFrom().getId().equals(targetAccount.getId()))
				{
					accountSummary.setBalance(accountSummary.getBalance() - transaction.getAmount());
					accountSummary.insertToOut(transaction);
				}
				else if (transaction.getTo().getId().equals(targetAccount.getId()))
				{
					accountSummary.setBalance(accountSummary.getBalance() + transaction.getAmount());
					accountSummary.insertToIn(transaction);
				}
			}
			
			output.setMessage("SUCCESS");
			output.setData(accountSummary);
		}
		
		return output;
	}
	
	
	@GetMapping("/transaction/to/{accountId}")
	public TransactionListOutLoad incomingTransactions(@PathVariable String accountId)
	{
		TransactionListOutLoad output = new TransactionListOutLoad();
		List<Transaction> transList = transactionRepository.findAll();
			
		if (accountRepository.findByid(accountId) == null)
		{
			output.setData(null);
			output.setMessage("ERROR:account doesn’t exist");
		}
		else
		{
			for (Transaction transaction: transList)
			{
				if (transaction.getTo().getId().equals(accountId))
				{
					output.insert(transaction);
				}
			}
						
			output.setMessage("SUCCESS");
		}
		
		return output;
	}
	
	@GetMapping("/transaction/from/{accountId}")
	public TransactionListOutLoad outgoingTransactions(@PathVariable String accountId)
	{
		TransactionListOutLoad output = new TransactionListOutLoad();
		List<Transaction> transList = transactionRepository.findAll();
			
		if (accountRepository.findByid(accountId) == null)
		{
			output.setData(null);
			output.setMessage("ERROR:account doesn’t exist");
		}
		else
		{
			for (Transaction transaction: transList)
			{
				if (transaction.getFrom().getId().equals(accountId))
				{
					output.insert(transaction);
				}
			}
			
			output.setMessage("SUCCESS");
		}
		
		return output;
	}
	
}
