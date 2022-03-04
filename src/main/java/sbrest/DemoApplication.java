package sbrest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxAPIResponse;
import com.box.sdk.BoxCollaboration;
import com.box.sdk.BoxCollaborator;
import com.box.sdk.BoxConfig;
import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import com.box.sdk.BoxUser;
import com.box.sdk.JWTEncryptionPreferences;

@SpringBootApplication
@ComponentScan
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		//boxUpload();
	}
	
	// Cors Configuration
	@Value("${client.url}")
	private String clientUrl;
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins(clientUrl).allowedMethods("PUT", "DELETE", "POST", "GET");
			}
		};
	}
	
	public static void boxUpload() throws IOException {
		 System.out.println( "Hello World!" );
	        
//	        String authorizationUrl = "https://account.box.com/api/oauth2/authorize?client_id=[ullh30s10qnffdgiu1mz87pgaz1itl5u]&response_type=code";
//	        //BoxAPIResponse.redirect(authorizationUrl);
//	        
//	        String secret = "I9r7ZIFUpEUiNpTlDpfE9WVz4Wro4qrt";
//	        String access = "8hgBYKgyJxwdcjnPhfnGyooJ2qtrhdkV";
	        BoxAPIConnection api = new BoxAPIConnection("gLUyjEyDwTZUvRyrSJUnqeSVTyepXtuA");
	        

	        
	       // BoxCollaborator user = new BoxUser(api, "18429209010");
	        BoxFolder folder = new BoxFolder(api, "155054681560");
	        //folder.collaborate(user, BoxCollaboration.Role.EDITOR);

	       
	        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
	        
	        for (BoxItem.Info itemInfo : rootFolder) {
	            System.out.format("[%s] %s\n", itemInfo.getID(), itemInfo.getName());
	        }
	        
	        
	        //BoxFolder folder = new BoxFolder(api, "155054681560");
	      
	        FileInputStream stream = new FileInputStream("./input/test.txt");
	        BoxFile.Info newFileInfo = folder.uploadFile(stream, "test.txt");
	        stream.close();
	
	}
}
