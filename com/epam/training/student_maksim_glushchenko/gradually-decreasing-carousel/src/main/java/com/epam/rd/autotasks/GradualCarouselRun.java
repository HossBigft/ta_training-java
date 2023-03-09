package com.epam.rd.autotasks;

import java.util.Arrays;

import static com.epam.rd.autotasks.DecrementingCarousel.carousel;

public class GradualCarouselRun extends CarouselRun {

    private int pos = 0;
    private int[] decrementArr = new int[carousel.length];

    {
        Arrays.fill(decrementArr, 1);
    }
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
        carousel[pos]-=decrementArr[pos];
        if(carousel[pos]<0){
            carousel[pos]=0;
        }
        decrementArr[pos]++;
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

    public int[] getDecrementArr() {
        return decrementArr;
    }
}
