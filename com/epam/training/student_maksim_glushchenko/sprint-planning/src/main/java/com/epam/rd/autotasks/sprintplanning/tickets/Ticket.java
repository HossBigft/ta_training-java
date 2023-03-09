package com.epam.rd.autotasks.sprintplanning.tickets;

import java.util.Objects;

public class Ticket {
    private int id;
    private String name;
    private boolean completed=false;
    private int estimate;
    public Ticket(int id, String name, int estimate) {
        this.id=id;
        this.name=name;
        this.estimate=estimate;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return  name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && estimate == ticket.estimate && name.equals(ticket.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, estimate);
    }

    public boolean isCompleted() {
        return  completed;
    }

    public void complete() {
        completed=true;
    }

    public int getEstimate() {
        return estimate;
    }
}
