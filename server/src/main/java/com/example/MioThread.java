package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

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
            String stringRead = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            do {
                stringRead = in.readLine();
                conta ++;
                int x = Integer.parseInt(stringRead);
                if(x > numero){
                    out.writeBytes(">" + '\n');
                }
                if(x < numero){
                    out.writeBytes("<" + '\n');
                }
                if(x == numero){
                    out.writeBytes("=" + '\n');
                    out.writeBytes(Integer.toString(conta) + '\n');
                    s.close();
                }else{
                    out.writeBytes("Not valid number" + '\n');

                }


            out.writeBytes(stringRead + '\n');
            } while (true);
        } catch (IOException e) {
            System.out.println("ERROR");
            System.exit(1);
        }
    }
}