package tk.leooresende.crudusuarios;

import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudUsuariosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CrudUsuariosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Locale.setDefault(new Locale("pt", "BR"));
	}
}
