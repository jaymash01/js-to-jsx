package com.jaymash.jstojsx;

public class Main {

    public static void main(String[] args) {
        if (args.length == 2) {
            Converter converter = new Converter();
            converter.start(args[0], args[1]);
        }
    }

}