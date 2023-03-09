package com.epam.rd.autotasks;

public class DecrementingCarousel {
    private final int capacity;
    static int[] carousel;
    private int currentIndex;
    private boolean isRunFinished;
    public DecrementingCarousel(int capacity) {
        this.capacity=capacity;
        this.carousel= new int[capacity];
        this.currentIndex =0;
        isRunFinished =false;
    }

    public boolean addElement(int element){
        if( element>0  && currentIndex <=capacity-1 && !isRunFinished){
            carousel[currentIndex]=element;
            currentIndex++;
            return  true;

        }
        return  false;
    }

    public CarouselRun run(){
        if(isRunFinished){
            return  null;
        }
        isRunFinished=true;
        return new CarouselRun();

    }

    public int getCapacity() {
        return capacity;
    }
    public int getElement(int index){
        return carousel[index];
    }
    public void decrementElement(int index){
        carousel[index]--;
    }


    public void setCurrentIndex(int value){
        currentIndex=value;
    }

    public int[] getCarousel() {
        return carousel;
    }
    public boolean isRunFinished(){
        return  isRunFinished;
    }
    public void setRunState(boolean isRunFinished){
        this.isRunFinished= isRunFinished;
    }
}
