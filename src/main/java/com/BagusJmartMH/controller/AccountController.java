// TODO sesuaikan dengan package Anda: package com.alvinJmartRK.controller;
package com.BagusJmartMH.controller;

// TODO sesuaikan dengan package Anda: import com.alvinJmartRK.Account;
import com.BagusJmartMH.Account;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController 
{
	@GetMapping
	String index() { return "account page"; }
	
	@PostMapping("/register")
	Account register
	(
		@RequestParam String name,
		@RequestParam String email,
		@RequestParam String password
	)
	{
		return new Account(name, email, password, 0);
	}
	
	@GetMapping("/{id}")
	String getById(@PathVariable int id) { return "account id " + id + " not found!"; }
}