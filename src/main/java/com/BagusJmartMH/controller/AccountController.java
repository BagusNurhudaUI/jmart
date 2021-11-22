
package com.BagusJmartMH.controller;

import com.BagusJmartMH.Account;
import com.BagusJmartMH.Algorithm;
import com.BagusJmartMH.Store;
import com.BagusJmartMH.dbjson.JsonAutowired;
import com.BagusJmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] digest = md.digest(password.getBytes());
		BigInteger no = new BigInteger(1, digest);
		String hash = no.toString(16);
		while (hash.length() < 32) hash = "0" + hash;
		String finalHash = hash;

		return Algorithm.<Account>find(accountTable, obj -> obj.email.equals(email) && obj.password.equals(finalHash));
	}

	@PostMapping("/register")
	Account register
			(
					@RequestParam String name,
					@RequestParam String email,
					@RequestParam String password
			)
	{
		if(name.isBlank()) return null;
		Matcher matcher1 = REGEX_PATTERN_EMAIL.matcher(email);
		if(!matcher1.find()) return null;
		Matcher matcher2 = REGEX_PATTERN_PASSWORD.matcher(password);
		if(!matcher2.find()) return null;
		if(Algorithm.<Account>find(accountTable, obj -> obj.email.equals(email)) != null) return null;

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] digest = md.digest(password.getBytes());
		BigInteger no = new BigInteger(1, digest);
		String hash = no.toString(16);
		while (hash.length() < 32) hash = "0" + hash;
		Account a = new Account(name, email, hash, 0);

		accountTable.add(a);
		return a;
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

	public static String MD5(String passwordToHash) throws NoSuchAlgorithmException {
		String messageDg = null;
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(passwordToHash.getBytes());
		byte[] bytes = md.digest();

		StringBuilder sb = new StringBuilder();
		for(int i=0; i< bytes.length ;i++)
		{
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		messageDg = sb.toString();
		return messageDg;
	}

//	@GetMapping
//	String index() { return "account page"; }
//

//
//	@GetMapping("/{id}")
//	String getById(@PathVariable int id) { return "account id " + id + " not found!"; }
}