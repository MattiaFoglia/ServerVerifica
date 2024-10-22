package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;

public class MioThread extends Thread {
    Socket s;
    int numero;

    public MioThread(Socket s,int numero) {
        this.s = s;
        this.numero = numero;
    }

    public void run() {
        try {
            int conta = 0;
            int x= 0;
            String stringRead = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            do {
                conta ++;
                stringRead = in.readLine();
        
                x = Integer.parseInt(stringRead);
                if(x > numero && x<=100 ){
                    out.writeBytes(">" + '\n');
                
                }
                if(x < numero && x>0){
                    out.writeBytes("<" + '\n');
                }
                if(x == numero){
                    out.writeBytes("=" + '\n');
                    out.writeBytes(Integer.toString(conta) + '\n');
                    stringRead = in.readLine();
                    if(stringRead == "n"){
                    s.close();
                    }
                    if(stringRead == "y"){
                        Random random = new Random();
                        numero = random.nextInt(100);
                    }
                }
                if(x<=0||x>100){
                    out.writeBytes("error" + '\n');
                }

            } while (true);

         

        } catch (IOException e) {
            System.out.println("ERROR");
            System.exit(1);
        }
    }
}