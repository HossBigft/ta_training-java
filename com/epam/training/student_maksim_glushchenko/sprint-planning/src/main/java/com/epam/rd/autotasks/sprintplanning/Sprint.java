package com.epam.rd.autotasks.sprintplanning;

import com.epam.rd.autotasks.sprintplanning.tickets.Bug;
import com.epam.rd.autotasks.sprintplanning.tickets.Ticket;
import com.epam.rd.autotasks.sprintplanning.tickets.UserStory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Sprint {
    private  final int timeCapacity;
    private  int currTimeCapacity;
    private final int ticketsLimit;
    private Ticket[]  tickets;
    private  int ticketAmount;

    public Sprint(int capacity, int ticketsLimit) {
        this.timeCapacity =capacity;
        currTimeCapacity=0;
        this.ticketsLimit=ticketsLimit;
        tickets=new Ticket[ticketsLimit];
        ticketAmount=0;

    }

    public boolean addUserStory(UserStory userStory) {

        if(userStory==null || userStory.isCompleted() || ticketAmount>=ticketsLimit || currTimeCapacity+ userStory.getEstimate()>timeCapacity){
            return false;
        } else if(userStory.getDependencies().length>0 && !isDependenciesAdded(userStory)){
            return false;
        }

        tickets[ticketAmount]=userStory;
        currTimeCapacity+=userStory.getEstimate();
        ticketAmount++;
        return true;
    }

    public boolean addBug(Bug bugReport) {
        if(bugReport==null|| bugReport.isCompleted() || ticketAmount>=ticketsLimit || currTimeCapacity+ bugReport.getEstimate()>timeCapacity){
            return false;
        }
        tickets[ticketAmount]=bugReport;
        currTimeCapacity+=bugReport.getEstimate();
        ticketAmount++;
        return true;
    }

    public Ticket[] getTickets() {
        return Arrays.copyOf(tickets, ticketAmount);

    }

    public int getTotalEstimate() {
        int totalEstimate=0;
        for(int i=0; i<ticketAmount;i++){
            totalEstimate+=tickets[i].getEstimate();
        }
        return totalEstimate;
    }
    public boolean isDependenciesAdded(UserStory userStory){
        Ticket[] dependencies = userStory.getDependencies();
        if(dependencies==null || isDependenciesCompleted(userStory)){
            return  false;
        }
        int addedStoriesCounter = 0;
        for (Ticket dependency : dependencies) {

                for (int i = 0; i < ticketAmount; i++) {
                    if (tickets[i].equals(dependency)) {
                        addedStoriesCounter++;
                    }
                }
        }
        if(addedStoriesCounter==dependencies.length){
            return true;
        }

        return  false;
    }
    public boolean isDependenciesCompleted(UserStory userStory){
        Ticket[] dependencies = userStory.getDependencies();
        for(Ticket dependency : dependencies){

            if(dependency.isCompleted()){
                return  true;
            }
        }
        return  false;
    }
}
