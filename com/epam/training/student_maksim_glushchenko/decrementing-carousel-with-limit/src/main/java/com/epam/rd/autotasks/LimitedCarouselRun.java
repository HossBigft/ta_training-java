package com.epam.rd.autotasks;

import static com.epam.rd.autotasks.DecrementingCarousel.carousel;
import static com.epam.rd.autotasks.DecrementingCarouselWithLimitedRun.runState;

public class LimitedCarouselRun extends CarouselRun {
        private int pos = 0;
        private final int actionLimit;
        private int currAction=0;

        public LimitedCarouselRun(int actionLimit){
            this.actionLimit=actionLimit;
        }
        public int next() {

            if(isFinished() || currAction>=actionLimit ){
                return  -1;
            } else{
                while(carousel[pos % carousel.length]<=0){
                    pos++;
                }
            }
            pos=pos % carousel.length;
            int currValue= carousel[pos];
            carousel[pos]--;
            pos++;
            currAction++;
            return  currValue;
        }

        public boolean isFinished() {
            if( currAction>=actionLimit){
                return  true;
            }
            for(int i=0; i<carousel.length; i++){
                if(carousel[i]>0){
                    return false;
                }
            }
            return true;
        }

}
