package com.epam.rd.autotasks;

import static com.epam.rd.autotasks.DecrementingCarousel.carousel;

public class CarouselRun {
    private int pos = 0;


    public int next() {

        if(isFinished()){
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
        return  currValue;
    }

    public boolean isFinished() {
        for(int i=0; i<carousel.length; i++){
            if(carousel[i]>0){
                return false;
            }
        }
        return true;
    }

}
