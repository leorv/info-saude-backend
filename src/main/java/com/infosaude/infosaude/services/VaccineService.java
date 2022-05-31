package com.infosaude.infosaude.services;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import com.infosaude.infosaude.entities.Vaccine;

import org.springframework.stereotype.Service;

@Service
public class VaccineService {

    public String createVaccine(Vaccine vaccine) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        String id = dbFirestore.collection("vaccines").document().getId();
        vaccine.setId(id);
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("vaccines").document(id).set(vaccine);

        return collectionsApiFuture.get().toString();
    }

    public List<Vaccine> getAllVaccines() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference collection = dbFirestore.collection("vaccines");

        ApiFuture<QuerySnapshot> future = collection.get();
        QuerySnapshot document = future.get();

        if (!document.isEmpty()) {
            List<Vaccine> vaccines;
            vaccines = document.toObjects(Vaccine.class);
            return vaccines;
        }
        return null;
    }

    public Vaccine getVaccineById(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("vaccines").document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        Vaccine vaccine;
        if (document.exists()) {
            vaccine = document.toObject(Vaccine.class);
            return vaccine;
        }
        return null;
    }

    public List<Vaccine> getVaccineByName(String name) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        // Query collection = dbFirestore.collection("vaccines").whereEqualTo("name",
        // name);
        Query collection = dbFirestore.collection("vaccines").whereGreaterThanOrEqualTo("name", name);

        ApiFuture<QuerySnapshot> docs = collection.get();
        QuerySnapshot queryResult = docs.get();

        if (!queryResult.isEmpty()) {
            List<Vaccine> vaccines;
            vaccines = queryResult.toObjects(Vaccine.class);
            return vaccines;
        }
        return null;
    }

    public String updateVaccine(Vaccine vaccine) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("vaccines").document(vaccine.getId()).set(vaccine);

        return writeResult.get().getUpdateTime().toString();
    }

    public String deleteVaccine(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("vaccines").document(id).delete();

        String result = writeResult.get().toString();
        return result;
    }
    
}
