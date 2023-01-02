package io.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import io.bankapp.model.Accounts;
import io.bankapp.model.Logger;
import io.bankapp.service.AccountService;

@RestController
public class AccountController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private LoggerController loggerController;

	// checkBalance
	@GetMapping("/account/balance")
	public int getBalance(@RequestParam int acctID) {
		return accountService.getBalance(acctID);
	}

	// depositAmount
	@PutMapping("/account/deposit")
	public void depositAmount(@RequestParam int acctID, @RequestParam int amount) {
		int initBal = getBalance(acctID);
		accountService.depositAmount(acctID, amount);
		Logger logger = new Logger(acctID, "Deposited", "Success", initBal, initBal + amount);
		loggerController.addLog(logger);
	}

	// withdrawAmount
	@PutMapping("/account/withdraw")
	public void withdrawAmount(@RequestParam int acctID, @RequestParam int amount) {
		int initBal = getBalance(acctID);
		accountService.withdrawAmount(acctID, amount);
		Logger logger = new Logger(acctID, "Withdrawn", "Success", initBal, initBal - amount);
		loggerController.addLog(logger);
	}

	// transferAmount
	@PutMapping("/account/transfer")
	public void transferAmount(@RequestParam int acctID, @RequestParam int destAcctID, @RequestParam int amount) {
		int initBalSender = getBalance(acctID);
		int initBalReceiver = getBalance(destAcctID);
		accountService.transferAmount(acctID, destAcctID, amount);
		Logger loggerSender = new Logger(acctID, "Transferred", "Success", initBalSender, initBalSender - amount);
		loggerController.addLog(loggerSender);
		Logger loggerReceiver = new Logger(destAcctID, "Received", "Success", initBalReceiver,
				initBalReceiver + amount);
		loggerController.addLog(loggerReceiver);
	}

	// getAccountInfo
	@GetMapping("/account")
	public Accounts getAccountInfo(@RequestParam int acctID) {
		return accountService.getAccountInfo(acctID);
	}

}
