package com.example.codingevents.controllers;


import com.example.codingevents.data.EventData;
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




    @GetMapping
    public String displayAllEvents(Model model) {


        model.addAttribute("events", EventData.getAll());
        return "/events/index";
    }

    //  at /events/create
    @GetMapping("create")
    public String renderCreateEventForm() {
        return "/events/create";
    }
    //  at /events/create
    @PostMapping("create")
    public String createEvent(@ModelAttribute Event newEvent) {
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

}
