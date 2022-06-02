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
import com.infosaude.infosaude.entities.VaccineTaken;

import org.springframework.stereotype.Service;

@Service
public class VaccineTakenService {
    public List<VaccineTaken> getAllVaccineTakens() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference collection = dbFirestore.collection("vaccines-taken");

        ApiFuture<QuerySnapshot> future = collection.get();
        QuerySnapshot document = future.get();

        if (!document.isEmpty()) {
            List<VaccineTaken> vaccinetakens;
            vaccinetakens = document.toObjects(VaccineTaken.class);
            // toObject(VaccineTaken[].class);

            return vaccinetakens;
        }
        return null;
    }

    public VaccineTaken getVaccineTakenById(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("vaccines-taken").document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        VaccineTaken vaccinetaken;
        if (document.exists()) {
            vaccinetaken = document.toObject(VaccineTaken.class);
            return vaccinetaken;
        }
        return null;
    }

    // public List<VaccineTaken> getVaccineTakensByType(String type) throws InterruptedException, ExecutionException {
    //     Firestore dbFirestore = FirestoreClient.getFirestore();
    //     Query docs = dbFirestore.collection("vaccines-taken").whereEqualTo("type", type);
    //     // select("type", type);
    //     ApiFuture<QuerySnapshot> future = docs.get();
    //     QuerySnapshot document = future.get();

    //     if (!document.isEmpty()) {
    //         List<VaccineTaken> vaccinetakens;
    //         vaccinetakens = document.toObjects(VaccineTaken.class);
    //         // toObject(VaccineTaken[].class);

    //         return vaccinetakens;
    //     }
    //     return null;
    // }

    public List<VaccineTaken> getVaccineTakensByStudentId(String studentId) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Query docs = dbFirestore.collection("vaccines-taken").whereEqualTo("studentId", studentId);
        // select("type", type);
        ApiFuture<QuerySnapshot> future = docs.get();
        QuerySnapshot document = future.get();

        if (!document.isEmpty()) {
            List<VaccineTaken> vaccinetakens;
            vaccinetakens = document.toObjects(VaccineTaken.class);
            // toObject(VaccineTaken[].class);

            return vaccinetakens;
        }
        return null;
    }

    public String createVaccineTaken(VaccineTaken vaccinetaken) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        String id = dbFirestore.collection("vaccines-taken").document().getId();
        vaccinetaken.setId(id);
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("vaccines-taken").document(id).set(vaccinetaken);

        // .add(vaccinetaken); => direto na collection, ou:
        // dbFirestore.collection("vaccinetakens").document(id).set(vaccinetaken);

        // create(vaccinetaken);
        return collectionsApiFuture.get().toString();
    }

    public String updateVaccineTaken(VaccineTaken vaccinetaken) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("vaccines-taken").document(vaccinetaken.getId()).set(vaccinetaken);

        return writeResult.get().getUpdateTime().toString();
    }

    public String deleteVaccineTaken(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("vaccines-taken").document(id).delete();

        String result = writeResult.get().toString();
        return result;
    }
    
}
