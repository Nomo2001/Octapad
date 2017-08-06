package de.kraemer_press.lena.octapad;

/**
 * Created by Lena on 06/08/2017.
 */

class StackDS {
    private int x = 0;
    public static int sArr[] = new int[10];

    public void push(int i){
        sArr[x] = i;
        x++;
    }

    public void pop(){
        for(int s = 9; s >= 0; s--){
            if(sArr[s] != 0);
            sArr[s] = 0;
            x = s;
            break;
        }
    }

    public void peek(){
        for(int s = 9; s >= 0; s--){
            if(sArr[s] != 0){
                System.out.println(sArr[s]);
            }
        }
    }
}



