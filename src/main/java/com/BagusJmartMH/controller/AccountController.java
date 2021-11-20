
package com.BagusJmartMH.controller;

import com.BagusJmartMH.Account;
import com.BagusJmartMH.Store;
import com.BagusJmartMH.dbjson.JsonAutowired;
import com.BagusJmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {
	@JsonAutowired(value = Account.class, filepath = "C:/Users/bagus/Desktop/jmart/account.json")
	public static final String REGEX_EMAIL = "^[a-zA-Z0-9&*_~]+(\\.[a-zA-Z0-9&*_~]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
	public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?!.* ).{8,}$";
	public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
	public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);
	public static JsonTable<Account> accountTable;



	public JsonTable<Account> getJsonTable(){
		return accountTable;
	}

	@PostMapping("/login")
	Account login(@RequestParam String email,@RequestParam String password){
		for(Account account : accountTable) {
			if(account.email.equals(email) && account.password.equals(password))
				return account;
		}
		return null;
	}

	@PostMapping("/register")
	Account register
			(
					@RequestParam String name,
					@RequestParam String email,
					@RequestParam String password
			)
	{
		Matcher matcherEmail = REGEX_PATTERN_EMAIL.matcher(email);
		Matcher matcherPassword = REGEX_PATTERN_PASSWORD.matcher(password);

		boolean uniqueEmail = true;

		for(Account account : accountTable) {
			if(account.email.equals(email))
				uniqueEmail = false;
		}

		if(!name.isBlank() && matcherEmail.find() && matcherPassword.find() && uniqueEmail) {
			Account newAccount = new Account(name, email, password, 0.0);
			accountTable.add(newAccount);
			return newAccount;
		}

		return null;
	}

	@PostMapping("/{id}/registerStore")
	Store registerStore(int id, String name, String address, String phoneNumber){
		for(Account account : accountTable) {
			if(account.id == id && account.store != null){
				account.store = new Store(name, address, phoneNumber, 0.0);
				return account.store;
			}

		}
		return null;
	}
	@PostMapping("/{id}/topUp")
	boolean topUp(int id, double balance){
		for(Account account : accountTable) {
			if(account.id == id) {
				account.balance += balance;
				return true;
			}
		}
		return false;
	}


//	@GetMapping
//	String index() { return "account page"; }
//

//
//	@GetMapping("/{id}")
//	String getById(@PathVariable int id) { return "account id " + id + " not found!"; }
}