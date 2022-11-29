package com.example.codingevents.models.dto;

import com.example.codingevents.models.Event;
import com.example.codingevents.models.Tag;

import javax.validation.constraints.NotNull;

public class EventTagDTO {

    @NotNull
    private Event event;
    private Tag tag;

    public EventTagDTO() {}

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public com.example.codingevents.models.Tag getTag() {
        return tag;
    }

    public void setTag(com.example.codingevents.models.Tag tag) {
        this.tag = tag;
    }
}
