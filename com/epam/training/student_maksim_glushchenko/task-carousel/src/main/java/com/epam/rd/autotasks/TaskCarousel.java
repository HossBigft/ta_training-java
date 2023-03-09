package com.epam.rd.autotasks;

public class TaskCarousel {
    private final int capacity;
    private Task[] tasks;
    private int taskCounter=0;
    private int currTaskIndex=0;
    public TaskCarousel(int capacity) {
        this.capacity=capacity;
        tasks= new Task[capacity];
    }

    public boolean addTask(Task task) {
        if(task==null || task.isFinished()){
            return false;
        } else if ( isFull()) {
            return false;
        }
        while(tasks[currTaskIndex]!=null){
            currTaskIndex++;
            currTaskIndex%=capacity;
        }
        tasks[currTaskIndex]=task;
        currTaskIndex=0;
        taskCounter++;
        return true;
    }

    public boolean execute() {
        if(isEmpty()){
            return false;
        } else if(tasks[currTaskIndex]==null ) {
            while (tasks[currTaskIndex] == null) {
                currTaskIndex++;
                currTaskIndex%=capacity;
            }
        }
        tasks[currTaskIndex].execute();
        if(tasks[currTaskIndex].isFinished()){
            tasks[currTaskIndex]=null;
            taskCounter--;
        }
        currTaskIndex++;
        currTaskIndex%=capacity;
        return  true;
    }

    public boolean isFull() {
        return taskCounter==capacity;
    }

    public boolean isEmpty() {
        return taskCounter==0;
    }

}
