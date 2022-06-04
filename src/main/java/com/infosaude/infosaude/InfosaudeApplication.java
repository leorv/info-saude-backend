package com.infosaude.infosaude;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.infosaude.infosaude.config.FirebaseConfig;

@SpringBootApplication
public class InfosaudeApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfosaudeApplication.class, args);
        try {
            FirebaseConfig firebaseConfig = new FirebaseConfig();
            firebaseConfig.firebaseApp();
        } catch (Exception e) {
            System.out.println("Um erro ocorreu ao buscar a chave do serviço firebase.");
            System.out.println(e.getMessage());
        }
    }
}
