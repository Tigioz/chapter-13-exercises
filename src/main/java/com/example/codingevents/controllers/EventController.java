package com.example.codingevents.controllers;


import com.example.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    public static HashMap<Event, String> events = new HashMap();



    @GetMapping
    public String displayAllEvents(Model model) {
        events.put(new Event("Menteaship"),"A fun meetup for connecting with mentors");
        events.put(new Event("Code With Pride"),"A fun meetup sponsored by LaunchCode");
        events.put(new Event("Javascripty"), "An imaginary meetup for Javascript developers");

        model.addAttribute("events", events);
        return "/events/index";
    }

    //  at /events/create
    @GetMapping("create")
    public String renderCreateEventForm() {
        return "/events/create";
    }
    //  at /events/create
    @PostMapping("create")
    public String createEvent(@RequestParam String eventName, @RequestParam String eventDescription) {
        events.put(new Event(eventName), eventDescription);
        return "redirect:";
    }


}
