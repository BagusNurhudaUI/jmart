
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

/**
 * merupakan class untuk mengatur perubahan post and get pada Account
 */
@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {

	public static final String REGEX_EMAIL = "^[a-zA-Z0-9&*_~]+(\\.[a-zA-Z0-9&*_~]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
	public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?!.* ).{8,}$";
	public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
	public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);

	@JsonAutowired(value = Account.class, filepath = "C:/Users/bagus/Desktop/jmart/account.json")
	public static JsonTable<Account> accountTable;


	public JsonTable<Account> getJsonTable(){
		return accountTable;
	}

	/**
	 * merupakan method untuk menulis ke json berdasarkan id dengan param email dan password
	 * @param email enail dari suatu objek
	 * @param password password yang digunakan untuk login
	 * @return
	 */
	@PostMapping("/login")
	Account login(@RequestParam String email,
				  @RequestParam String password){
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

	/**
	 * merupakan method yang digunkan untuk membuat akun pada file json
	 * @param name nama dari akun
	 * @param email email dari akun
	 * @param password password untuk akun
	 * @return
	 */
	@PostMapping("/register")
	Account register
			(
					@RequestParam String name,
					@RequestParam String email,
					@RequestParam String password
			)
	{
		Matcher emailMatcher = REGEX_PATTERN_EMAIL.matcher(email);
		boolean emailMatch = emailMatcher.find();
		Matcher passwordMatcher = REGEX_PATTERN_PASSWORD.matcher(password);
		boolean passwordMatch = passwordMatcher.find();
		boolean unique = true;

		for(Account acc: accountTable){
			if(acc.email.equals(email)){
				unique = false;
				break;
			}
		}

		if(!name.isBlank() && emailMatch && passwordMatch && unique){

			Account regAccount = new Account(name, email, hashPassword(password), 0);
			accountTable.add(regAccount);
			return regAccount;

		} else {
			return null;
		}

	}

	/**
	 * merupakan method yang digunakan untuk membuat toko pada sebuah account
	 * @param id id dari akun
	 * @param name nama toko
	 * @param address alamat toko
	 * @param phoneNumber nomor telpon dari toko
	 * @return
	 */
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

	/**
	 * merupakan method yang digunakan untuk menambahkan balance dari parameter id
	 * @param id id dari akun yang ingin ditambah saldonya
	 * @param balance nilai balance yang ingin ditambahkan
	 * @return
	 */
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

	/**
	 * method yang digunakan untuk melakukan hashing pada password yang telah diinput
	 * @param input input dari passwordnya
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
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

	/**
	 * method yang digunakan utnuk hashing pass
	 * @param password password yang ditampilkan pada json
	 * @return
	 */
	public String hashPassword(String password){
		try{
			String generatedPassword = null;

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] bytes = md.digest();

			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < bytes.length; i++){
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
			return generatedPassword;
		} catch (NoSuchAlgorithmException e){
			e.printStackTrace();
			return password;
		}
	}

	/**
	 * merupakan test get mapping untuk mengetahui param benar
	 * @return
	 */
	@GetMapping
	String index() { return "account page"; }

	/**
	 * yaitu mengambil informasi dari account json berdasarkan ID
	 * @param id dari account
	 * @return
	 */
	@GetMapping("/{id}")
	public Account getByAccountId(@PathVariable int id) { return getById(id); }


}