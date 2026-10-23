package stanel.app.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@ConfigurationPropertiesScan 
public class Application {

	public static void main(String[] args) {

		Dotenv.configure()
				.directory("./")
				.ignoreIfMissing()
				.systemProperties()
				.load();

		SpringApplication.run(Application.class, args);
	}

}
