/*
 *  Program made by Artem Hrechka
 *
 *  Reader_v1.1, a simple reader to
 *  read ints, strings and chars from
 *  console
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static String readLine(){
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int readInt(){
        int res = 0;
        boolean correctInput = false;
        while(!correctInput){
            try {
                String line = readLine();
                res = Integer.parseInt(line);
                correctInput = true;
            }catch (Exception e){
                System.out.print("Incorrect input. Try again: ");
            }
        }
        return res;
    }

    public static int readInt(int low, int high){
        int res = 0;
        boolean correctInput = false;
        while(!correctInput){
            res = readInt();
            if(res >= low && res <= high)
                correctInput = true;
            else
                System.out.print("Incorrect input. Try again: ");
        }
        return res;
    }

    public static char readChar(){
        try {
            return br.readLine().charAt(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}