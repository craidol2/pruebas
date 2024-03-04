package com.java.demo;

import java.util.Scanner;

public class X01Scanner {

    public static void execute(){
        var scan = new Scanner("1\n3,5\nHola amigos de las cabras");

        Integer i = scan.nextInt();
        Double d = scan.nextDouble();
        //después de nextInt() y nextDouble() si se quiere leer nextLine() toca darle dos veces
        scan.nextLine();
        String s = scan.nextLine();
        scan.close();

        System.out.println(i);
        System.out.println(d);
        System.out.println(s);
    }
}
