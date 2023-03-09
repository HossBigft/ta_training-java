package com.epam.rd.autotasks;

public class CompleteByRequestTask implements Task {
    private boolean state;
    private boolean isFinished;

    @Override
    public void execute() {
        isFinished=state==true;
    }

    @Override
    public boolean isFinished() {
        return  isFinished;
    }

    public void complete() {
        state=true;
    }
}
