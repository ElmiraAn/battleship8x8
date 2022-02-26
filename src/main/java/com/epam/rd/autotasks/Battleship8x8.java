package com.epam.rd.autotasks;

import java.math.BigInteger;

public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {
        this.ships = ships;
    }

    private static char[] mapChar(long shots){

        String map = Long.toBinaryString(shots);
        char[] map_char=new char[64];
        char[] map_char_copy=map.toCharArray();
        int k = map_char_copy.length - 1;

        for (int i = 63; i>=0; i--){
            if (k>=0){
                map_char[i] = map_char_copy[k];
                k--;
            }
            else{
                map_char[i] = '0';
            }
        }
        return map_char;
    }

    public boolean shoot(String shot) {
        String map = Long.toBinaryString(ships);
        if (ships>0) map=0+map;
        char[] map_ships=map.toCharArray();
        char[] map_char=mapChar(shots);

        boolean shoot_do=false;
        char a_1 =shot.charAt(0);
        char a_2=shot.charAt(1);
        int a_2_int=Integer.parseInt(String.valueOf(a_2));

        //System.out.println(a_1+" "+a_2);
        int a1_int=0;

        switch (a_1){
            case 'A': a1_int=0; break;
            case 'B': a1_int=1; break;
            case 'C': a1_int=2;break;
            case 'D': a1_int=3;break;
            case 'E': a1_int=4;break;
            case 'F': a1_int=5;break;
            case 'G': a1_int=6;break;
            case 'H': a1_int=7;
        }
        int index_shot = (a_2_int-1)*8+a1_int;
        //System.out.println("a1_int="+a1_int+"  a_2_int="+a_2_int+" index_shot="+index_shot);

        for (int i=0; i< map_char.length; i++){
            if (i==index_shot){
                map_char[i] = '1';
                if (map_ships[i]=='1') shoot_do = true;
                break;
            }
        }

        BigInteger shotsBig= new BigInteger(String.valueOf(map_char),2);
        shots= shotsBig.longValue();

        return shoot_do;
    }

    public String state() {

        String map = Long.toBinaryString(ships);
        if (ships>0) map=0+map;

        char[] map_char=mapChar(shots);
        char[] map_ships=map.toCharArray();
        for (int i=0; i<map_ships.length;i++){
            if (map_char[i]=='0' && map_ships[i]=='0') map_ships[i]='.';
            else if (map_char[i]=='0' && map_ships[i]=='1') map_ships[i]='☐';
            else if (map_char[i]=='1' && map_ships[i]=='0') map_ships[i]='×';
            else map_ships[i]='☒';
        }

        String map_finish=String.valueOf(map_ships);
        String map_finish2=map_finish.substring(0,8)+"\n"+map_finish.substring(8,16)+"\n"+
                map_finish.substring(16,24)+"\n"+map_finish.substring(24,32)+"\n"+
                map_finish.substring(32,40)+"\n"+map_finish.substring(40,48)+"\n"+
                map_finish.substring(48,56)+"\n"+map_finish.substring(56,64);

        return map_finish2;
        }
    }

