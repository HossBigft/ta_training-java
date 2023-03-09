package com.epam.rd.autotasks;

public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {
        this.ships = ships;
    }

    public boolean shoot(String shot) {
        setBitTrue(getIndex1D(shot));
        return getBitValue(getIndex1D(shot), ships) & getBitValue(getIndex1D(shot), shots);
    }

    public void setBitTrue(int index){
        shots |= 1L << (63-index);
    }
    public boolean getBitValue(int index, Long target){
        Long value =Long.valueOf(target &(1L << (63-index)));
        String tBinary =String.format("%64s", Long.toBinaryString(target)).replace(' ', '0');
        String vBinary =Long.toBinaryString(value);
        return value!=0;
    }

    public void setBitFalse(int index){
        shots &= ~(1L << (63-index));
    }
    public int  getIndex1D(String shot){
        String[] splitted = shot.split("");
        String colStr ="";

        switch (splitted[0]){
            case "A":
                colStr+=0;
                break;
            case "B":
                colStr+=1;
                break;
            case "C":
                colStr+=2;
                break;
            case "D":
                colStr+=3;
                break;
            case "E":
                colStr+=4;
                break;
            case "F":
                colStr+=5;
                break;
            case "G":
                colStr+=6;
                break;
            case "H":
                colStr+=7;
                break;
        }
        int col=Integer.parseInt(colStr);
        int row=Integer.parseInt(splitted[1]);
        row=row>0 ? row-1 : 0;
        int length_of_row=8;
        int index1D = (row * length_of_row) + col;
        return index1D;
    }
    public String state() {
        final int FIELD_SIZE = 64;
        StringBuilder sb = new StringBuilder();
        String shipsBinary = Long.toBinaryString(ships);
        String shotsBinary =String.format("%64s", Long.toBinaryString(shots)).replace(' ', '0');
        for(int i=0; i<64;i++){
            boolean shipCell= getBitValue(i, ships);
            boolean shotCell= getBitValue(i, shots);
            if(i%8==0 & i>0){
                sb.append("\n");
            }
            if(shipCell & shotCell){
                sb.append("☒");
            } else if(!shipCell & !shotCell){
                sb.append(".");
            } else if(!shipCell & shotCell){
                sb.append("×");
            } else if(shipCell & !shotCell){
                sb.append("☐");
            }

        }
        return sb.toString();
    }
}
