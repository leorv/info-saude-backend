package com.infosaude.infosaude;

import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InfosaudeApplication {

	public static void main(String[] args) {

        ClassLoader classLoader = InfosaudeApplication.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());

        try {
            FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            System.out.println("Um erro ocorreu ao buscar a chave do serviço.");
            System.out.println(e.getMessage());
        }

		SpringApplication.run(InfosaudeApplication.class, args);
	}

}
