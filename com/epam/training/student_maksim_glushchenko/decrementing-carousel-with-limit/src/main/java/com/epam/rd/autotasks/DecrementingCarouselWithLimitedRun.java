package com.epam.rd.autotasks;

public class DecrementingCarouselWithLimitedRun extends DecrementingCarousel{
    final int actionLimit;
    static boolean runState;
    public DecrementingCarouselWithLimitedRun(final int capacity, final int actionLimit) {
        super(capacity);
        runState=super.isRunFinished();
        this.actionLimit=actionLimit;
    }
    public LimitedCarouselRun run(){
        if(isRunFinished()){
            runState=isRunFinished();
            return  null;
        }
        setRunState(true);
        runState=isRunFinished();
        return new LimitedCarouselRun(actionLimit);

    }

}
