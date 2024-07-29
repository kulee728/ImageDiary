package coddingcoggies;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("coddingcoggies.mapper")
@SpringBootApplication
public class CoddingCoggiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoddingCoggiesApplication.class, args);
	}

}
