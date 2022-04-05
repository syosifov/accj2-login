package net.yosifov.accounting.accj.accj2login;

import net.yosifov.accounting.accj.accj2login.utils.C;
import net.yosifov.accounting.accj.accj2login.utils.InitUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Accj2LoginApplication implements CommandLineRunner {

	@Autowired
	private InitUsers initUsers;

	public static void main(String[] args) {
		SpringApplication.run(Accj2LoginApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initUsers.createAppAdmin();
		initUsers.createAppUser("user1", "pass1", C.ROLE_ACC);

	}
}
