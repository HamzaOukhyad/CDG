package ma.cdgcapital.saham;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CKGSAHAMApplication {

 
  public static void main(String[] args) {

    SpringApplication.run(CKGSAHAMApplication.class, args);
    
  }
}
