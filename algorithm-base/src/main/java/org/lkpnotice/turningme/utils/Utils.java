package org.lkpnotice.turningme.utils;

import java.util.Arrays;

public class Utils {
    static byte SEED = 0x1F;
    public static byte[] encrypt(byte[] data){
        for(int i =0 ; i< data.length ;i++ ){
            data[i] = (byte)(data[i] ^ SEED);
        }
        return data;
    }

    public static byte[] decrypt(byte[] data){
        for(int i =0 ; i< data.length ;i++ ){
            data[i] = (byte)(data[i] ^ SEED);
        }
        return data;
    }


    public static void main(String[] args){
        byte[] testdata = new byte[]{2,1,34,-1, 0, 127, -128};
        byte[] std = Arrays.copyOf(testdata,testdata.length);
        byte[] outputdata = decrypt(encrypt(testdata));

        boolean eq = Arrays.equals(std,outputdata);
        System.out.println(eq);


        String a = "sfahcxhbhdfshsdf";
        byte[] encryptedCode = encrypt(a.getBytes());;
        System.out.println("b = " + new String(encryptedCode));
        String b = new String(decrypt(encryptedCode));
        System.out.println(b.equals(a));
    }
}
