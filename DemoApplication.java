package sbrest;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;
import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxAPIResponse;
import com.box.sdk.BoxCCGAPIConnection;
import com.box.sdk.BoxCollaboration;
import com.box.sdk.BoxCollaborator;
import com.box.sdk.BoxConfig;
import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import com.box.sdk.BoxUser;
import com.box.sdk.JWTEncryptionPreferences;

import sbrest.signapi.OAuthTokens;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootApplication
@ComponentScan
public class DemoApplication {

	public static void main(String[] args) throws Exception {
		
		SpringApplication.run(DemoApplication.class, args);
		
		boxUpload();
		
		//boxUpload(downloadAgreements(new URL(getUrl("3AAABLblqZhC_KwnMPGwcB1KB_364iKZrLabqRc_WbM23FuRQuTmmu4mRDQeiokjRtIdGaHefE2semx254flA8-Zmhz-n1sL6"))));

		
	        
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
	        
	        
	        BoxCCGAPIConnection api = BoxCCGAPIConnection.applicationServiceAccountConnection(
	        	    "ffc823n3uuf15g5vooj45xg3eri29ukn",
	        	    "I9r7ZIFUpEUiNpTlDpfE9WVz4Wro4qrt",
	        	    "873161760"
	        	);
	        
	        api.asUser("18429209010");
	        
	        String tmpdir = System.getProperty("java.io.tmpdir");
	        System.out.println("Temp file path: " + tmpdir);

	        try {

	            // Create an temporary file
	            Path temp = Files.createTempFile("tempfile", ".pdf");
	            System.out.println("Temp file : " + temp);
	            

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        
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
