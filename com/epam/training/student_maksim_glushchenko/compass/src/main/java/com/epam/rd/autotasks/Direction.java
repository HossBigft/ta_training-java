package com.epam.rd.autotasks;

import java.util.Optional;

public enum Direction {
    N(0), NE(45), E(90), SE(135), S(180), SW(225), W(270), NW(315);

    Direction(final int degrees) {
        this.degrees = degrees;
    }
    public int getDegrees(){
        return degrees;
    }

    private int degrees;

    public static Direction ofDegrees(int degrees) {
        int curDeg=degrees;
        while(curDeg>=360){
            curDeg-=360;
        }
        while(curDeg<0){
            curDeg+=360;
        }
        for(Direction dir : Direction.values()){
            if(curDeg==dir.getDegrees()){
                return dir;
            }
        }
        return  null;
    }

    public static Direction closestToDegrees(int degrees) {
        int curDeg=degrees;
        while(curDeg>=360){
            curDeg-=360;
        }
        while(curDeg<0){
            curDeg+=360;
        }
        int min = Integer.MAX_VALUE;
        int closest = curDeg;
        for(Direction dir : values()){
            int diff= Math.abs(dir.getDegrees()-curDeg);
                if(diff<min){
                    closest=dir.getDegrees();
                    min=diff;
                }

        }
        return  ofDegrees(closest);
    }

    public Direction opposite( ) {
        return  ofDegrees(this.getDegrees()+180);
    }

    public int differenceDegreesTo(Direction direction) {
        int diff=this.getDegrees()- direction.getDegrees();
        if(diff<0&& Math.abs(diff)>180){
            diff=360-Math.abs(diff);
        }
        return  Math.abs(diff);
    }
}
