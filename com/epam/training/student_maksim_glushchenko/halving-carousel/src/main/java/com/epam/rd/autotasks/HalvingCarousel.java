package com.epam.rd.autotasks;

public class HalvingCarousel extends DecrementingCarousel {

    public HalvingCarousel(final int capacity) {
        super(capacity);
    }
    public HalvingCarouselRun run(){
        if(isRunFinished()){
            return  null;
        }
        setRunState(true);
        return new HalvingCarouselRun();
    }


}
