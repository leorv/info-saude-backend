package com.infosaude.infosaude;

import java.io.InputStream;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.infosaude.infosaude.config.FirebaseCredential;

import org.apache.commons.io.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class InfosaudeApplication {

    static Environment environment;

    public InfosaudeApplication (Environment environment){
        InfosaudeApplication.environment = environment;
    }

    public static void main(String[] args) {

        // ClassLoader classLoader = InfosaudeApplication.class.getClassLoader();
        // File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());
        
        
        try {
            // FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
            InputStream firebaseCredentialStream = createFirebaseCredential();

            FirebaseOptions options = FirebaseOptions.builder()
                    // .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setCredentials(GoogleCredentials.fromStream(firebaseCredentialStream))
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            System.out.println("Um erro ocorreu ao buscar a chave do serviço.");
            System.out.println(e.getMessage());
        }

		SpringApplication.run(InfosaudeApplication.class, args);
	}

    private static InputStream createFirebaseCredential() throws Exception {
        // private key
        String privateKey = environment.getRequiredProperty("FIREBASE_PRIVATE_KEY").replace("\\n", "\n");

        FirebaseCredential firebaseCredential = new FirebaseCredential();
        firebaseCredential.setType(environment.getRequiredProperty("FIREBASE_TYPE"));
        firebaseCredential.setProject_id(environment.getRequiredProperty("FIREBASE_PROJECT_ID"));
        firebaseCredential.setPrivate_key_id(environment.getRequiredProperty("FIREBASE_PRIVATE_KEY_ID"));
        firebaseCredential.setPrivate_key(privateKey);
        firebaseCredential.setClient_email(environment.getRequiredProperty("FIREBASE_CLIENT_EMAIL"));
        firebaseCredential.setClient_id(environment.getRequiredProperty("FIREBASE_CLIENT_ID"));
        firebaseCredential.setAuth_uri(environment.getRequiredProperty("FIREBASE_AUTH_URI"));
        firebaseCredential.setToken_uri(environment.getRequiredProperty("FIREBASE_TOKEN_URI"));
        firebaseCredential.setAuth_provider_x509_cert_url(
                environment.getRequiredProperty("FIREBASE_AUTH_PROVIDER_X509_CERT_URL"));
        firebaseCredential.setClient_x509_cert_url(environment.getRequiredProperty("FIREBASE_CLIENT_X509_CERT_URL"));
        // serialization of the object to json string
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(firebaseCredential);

        // convert jsonString string to InputStream using Apache Commons
        return IOUtils.toInputStream(jsonString, "utf-8");
    }

}
