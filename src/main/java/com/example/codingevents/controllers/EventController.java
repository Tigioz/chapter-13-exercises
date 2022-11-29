package com.example.codingevents.controllers;


import com.example.codingevents.data.EventCategoryRepository;
import com.example.codingevents.data.EventRepository;
import com.example.codingevents.data.TagRepository;
import com.example.codingevents.models.Event;
import com.example.codingevents.models.EventCategory;
import com.example.codingevents.models.Tag;
import com.example.codingevents.models.dto.EventTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    public String displayAllEvents(@RequestParam(required = false) Integer categoryId, Model model) {
        if(categoryId == null) {
            model.addAttribute("events", eventRepository.findAll());
        } else {
           Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);
           if(result.isEmpty()) {
               model.addAttribute("title", "Invalid Category ID: " + categoryId);
           } else {
               EventCategory category = result.get();
               model.addAttribute("title", "Events in category: " + category.getName());
               model.addAttribute("events", category.getEvents());
           }
        }

        return "/events/index";
    }

    //  at /events/create
    @GetMapping("create")
    public String renderCreateEventForm(Model model) {
        Event event = new Event();
        model.addAttribute(event);
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "/events/create";
    }

    //  at /events/create
    @PostMapping("create")
    public String createEvent(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            model.addAttribute("errorMsg", "invalid data");
            return "events/create";
        }

        eventRepository.save(newEvent);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventRepository.findAll());
        return "/events/delete";

    }

    @PostMapping("delete")
    public String processDeleteEventForm(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
        for (int id: eventIds) {
            eventRepository.deleteById(id);
        }}
        return "redirect:";
    }

    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer eventId, Model model) {
        Optional<Event> result = eventRepository.findById(eventId);
        Event event = result.get();
        model.addAttribute("title", "Add tag to: " + event.getName());
        model.addAttribute("tags", tagRepository.findAll());
        model.addAttribute("event", event);
        model.addAttribute("eventTag", new EventTagDTO());

        return "events/add-tag.html";
    }


    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid EventTagDTO eventTag, Model model, Errors errors) {
        if (!errors.hasErrors()) {
            Event event = eventTag.getEvent();
            Tag tag = eventTag.getTag();
            if (!event.getTags().contains(tag)) {
                event.addTag(tag);
                eventRepository.save(event);
            }
            return "redirect:";
        }
            return "events/add-tag.html";
    }
}
