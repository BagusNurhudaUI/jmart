
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
	//@JsonAutowired(value = Account.class, filepath = "C:/Users/bagus/Desktop/jmart/account.json")
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
			String passH = null;
			try{
				passH = MD5(password);
			}
			catch (NoSuchAlgorithmException e){
				e.printStackTrace();
			}
			if(account.email.equals(email) && account.password.equals(passH))
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
		boolean isUnique = true;
		for(Account a : accountTable)
		{
			if(a.email.equals(email))
			{
				isUnique = false;
				break;
			}
		}
		if(matcherEmail.find() && matcherPassword.find() && (!name.isBlank()) && isUnique)
		{
			String passH = null;
			try {
				passH = MD5(password);
			}
			catch (NoSuchAlgorithmException e){
				e.printStackTrace();
			}
			Account account = new Account(name, email, passH, 0.0);
			accountTable.add(account);
			return account;
		}
		return null;
	}

	@PostMapping("/{id}/registerStore")
	Store registerStore(@PathVariable int id,@RequestParam String name,@RequestParam String address,@RequestParam String phoneNumber){
		Account a = Algorithm.<Account> find(accountTable, obj -> obj.id == id);
		if(a == null || a.store != null)
		{
			return null;
		}
		a.store = new Store(name, address, phoneNumber, 0.0);
		return a.store;
	}
	@PostMapping("/{id}/topUp")
	boolean topUp(@PathVariable int id,@RequestParam double balance){
		Account a = getById(id);
		if(a != null)
		{
			a.balance += balance;
			return true;
		}
		return false;
	}

	public static String MD5(String input) throws NoSuchAlgorithmException {
		try {

			// membuat instance mengenai MD5 yang diambil dari library security MD5
			MessageDigest md = MessageDigest.getInstance("MD5");

			// mengambil method digest yang ada di library security messagedigest untuk membaca teks yang diberikan
			// kemudian hasil teks tersebut akan dikembalikan dalam bentuk byte di array
			byte[] pesan = md.digest(input.getBytes());

			// melakukan konversi hasil hashing tersebut menjadi signum menggunakan library biginteger
			BigInteger bigInteger = new BigInteger(1, pesan);

			// Hasil dari signum tersebut akan diubah menjadi nilai hex
			String hasil = bigInteger.toString(16);
			// apabila panjang dari hasil kurang dari 32 maka akan ada penambahan angka 0 di depan hasil
			while (hasil.length() < 32) {
				hasil = "0" + hasil;
			}
			return hasil; //mengembalikkan nilai hasil
		}
		// fungsi catch apabila saat mode try mengalami kegagalan
		catch (NoSuchAlgorithmException e) {
			System.out.println("Run Time Error");
			throw new RuntimeException(e);
		}
	}

//	@GetMapping
//	String index() { return "account page"; }
//

//
//	@GetMapping("/{id}")
//	String getById(@PathVariable int id) { return "account id " + id + " not found!"; }
}