package cn.tedu.store;

import javax.servlet.MultipartConfigElement;
import javax.servlet.annotation.MultipartConfig;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.util.unit.DataSize;
//@Configuration
@SpringBootApplication
@MapperScan("cn.tedu.store.mapper")
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}
	/*@bean
	 * public MultipartConfigElement getMultipartConfigElement() {
		MultipartConfigFactory factory=new MultipartConfigFactory();
		
		factory.setMaxFileSize(DataSize.ofMegabytes(100));
		factory.setMaxRequestSize(DataSize.ofMegabytes(500));
		
		MultipartConfigElement element=factory.createMultipartConfig();
		
		
		return element;
		
	}*/
	

}
