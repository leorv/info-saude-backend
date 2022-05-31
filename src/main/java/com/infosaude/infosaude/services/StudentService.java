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
import com.infosaude.infosaude.entities.Student;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public String createStudent(Student student) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        String id = dbFirestore.collection("students").document().getId();
        student.setId(id);
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("students").document(id).set(student);

        return collectionsApiFuture.get().toString();
    }

    public List<Student> getAllStudents() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference collection = dbFirestore.collection("students");

        ApiFuture<QuerySnapshot> future = collection.get();
        QuerySnapshot document = future.get();

        if (!document.isEmpty()) {
            List<Student> students;
            students = document.toObjects(Student.class);
            return students;
        }
        return null;
    }

    public Student getStudentById(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("students").document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        Student student;
        if (document.exists()) {
            student = document.toObject(Student.class);
            return student;
        }
        return null;
    }

    public List<Student> getStudentByName(String name) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        // Query collection = dbFirestore.collection("students").whereEqualTo("name", name);
        Query collection = dbFirestore.collection("students").whereGreaterThanOrEqualTo("name", name);

        ApiFuture<QuerySnapshot> docs = collection.get();
        QuerySnapshot queryResult = docs.get();

        if (!queryResult.isEmpty()) {
            List<Student> students;
            students = queryResult.toObjects(Student.class);
            return students;
        }
        return null;
    }

    public String updateStudent(Student student) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("students").document(student.getId()).set(student);

        return writeResult.get().getUpdateTime().toString();
    }

    public String deleteStudent(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("students").document(id).delete();

        String result = writeResult.get().toString();
        return result;
    }

   
    
}
