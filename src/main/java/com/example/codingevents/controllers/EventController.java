package com.example.codingevents.controllers;


import com.example.codingevents.data.EventData;
import com.example.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Parser;
import javax.validation.Path;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {




    @GetMapping
    public String displayAllEvents(Model model) {


        model.addAttribute("events", EventData.getAll());
        return "/events/index";
    }

    //  at /events/create
    @GetMapping("create")
    public String renderCreateEventForm(Model model) {
        Event event = new Event();
        model.addAttribute(event);
        return "/events/create";
    }

    //  at /events/create
    @PostMapping("create")
    public String createEvent(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            model.addAttribute("errorMsg", "invalid data");
            return "/events/create";
        }

        EventData.add(newEvent);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {

        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());

        return "/events/delete";

    }

    @PostMapping("delete")
    public String processDeleteEventForm(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
        for (int id: eventIds) {
            EventData.remove(id);
        }}
        return "redirect:";
    }


    @GetMapping("/edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId) {
        Event editEvent = EventData.getById(eventId);
        model.addAttribute("event", editEvent);
        String title = "Edit Event " + editEvent.getName() + " (id=" + editEvent.getId() + ")";
        model.addAttribute("title", title);

        return "/events/edit";
    }


    @PostMapping("/edit")
    public String processEditForm(@Valid int eventId, String name, String description, String address) {
       Event editEvent = EventData.getById(eventId);
       editEvent.setName(name);
       editEvent.setDescription(description);
       editEvent.setAddress(address);
       return "redirect:/events";
    }
}
