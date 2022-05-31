package com.infosaude.infosaude.controllers;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.infosaude.infosaude.entities.Event;
import com.infosaude.infosaude.services.EventService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/events")
public class EventController {

    public EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/create")
    public String createEvent(@RequestBody Event event) throws InterruptedException, ExecutionException {
        return eventService.createEvent(event);
    }

    @GetMapping("/")
    public List<Event> getAllEvents() throws InterruptedException, ExecutionException {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable(value = "id") String id) throws InterruptedException, ExecutionException {
        System.out.println(id);
        return eventService.getEventById(id);
    }

    @GetMapping("/getEventsByType/{type}")
    public List<Event> getEventByType(@PathVariable(value = "type") String type)
            throws InterruptedException, ExecutionException {
        return eventService.getEventsByType(type);
    }

    @GetMapping("/getByStudentId/{studentId}")
    // public Event[] getEventByStudentId(@RequestParam int studentId) throws
    // InterruptedException, ExecutionException {
    public List<Event> getEventByStudentId(
            @PathVariable(value = "studentId") String studentId) throws InterruptedException, ExecutionException {
        return eventService.getEventsByStudentId(studentId);
    }

    @PutMapping("/update")
    // public String updateEvent(@RequestParam Event event) throws
    // InterruptedException, ExecutionException {
    public String updateEvent(@RequestBody Event event) throws InterruptedException, ExecutionException {
        return eventService.updateEvent(event);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEvent(@PathVariable(value = "id") String id) throws InterruptedException, ExecutionException {
        return eventService.deleteEvent(id);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testGetEndPoint() {
        return ResponseEntity.ok("Test get end-point is working.");
    }

}
