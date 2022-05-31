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
import com.infosaude.infosaude.entities.Event;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    public List<Event> getAllEvents() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference collection = dbFirestore.collection("events");
        
        ApiFuture<QuerySnapshot> future = collection.get();
        QuerySnapshot document = future.get();

        if (!document.isEmpty()) {
            List<Event> events;
            events = document.toObjects(Event.class);
            //toObject(Event[].class);

            return events;
        }
        return null;
    }

    public Event getEventById(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("events").document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        Event event;
        if (document.exists()) {
            event = document.toObject(Event.class);
            return event;
        }
        return null;
    }

    public List<Event> getEventsByType(String type) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Query docs = dbFirestore.collection("events").whereEqualTo("type", type);
        // select("type", type);
        ApiFuture<QuerySnapshot> future = docs.get();
        QuerySnapshot document = future.get();
        
        if (!document.isEmpty()) {
            List<Event> events;
            events = document.toObjects(Event.class);
            // toObject(Event[].class);

            return events;
        }
        return null;
    }

    public List<Event> getEventsByStudentId(String studentId) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Query docs = dbFirestore.collection("events").whereEqualTo("studentId", studentId);
        // select("type", type);
        ApiFuture<QuerySnapshot> future = docs.get();
        QuerySnapshot document = future.get();

        if (!document.isEmpty()) {
            List<Event> events;
            events = document.toObjects(Event.class);
            // toObject(Event[].class);

            return events;
        }
        return null;
    }

    public String createEvent(Event event) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        String id = dbFirestore.collection("events").document().getId();
        event.setId(id);
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("events").document(id).set(event);

        //.add(event); => direto na collection, ou:
        // dbFirestore.collection("events").document(id).set(event);

                // create(event);
        return collectionsApiFuture.get().toString();
    }

    public String updateEvent(Event event) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("events").document(event.getId()).set(event);

        return writeResult.get().getUpdateTime().toString();
    }

    public String deleteEvent(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("events").document(id).delete();

        String result = writeResult.get().toString();
        return result;
    }
    


}
