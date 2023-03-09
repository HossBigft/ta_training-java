package com.epam.rd.autotasks;

public class GraduallyDecreasingCarousel extends DecrementingCarousel{

    public GraduallyDecreasingCarousel(final int capacity) {
        super(capacity);

    }

    public GradualCarouselRun run(){
        if(isRunFinished()){
            return  null;
        }
        setRunState(true);
        return new GradualCarouselRun();

    }

}
