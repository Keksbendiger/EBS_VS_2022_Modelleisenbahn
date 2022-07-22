package util;

//--------------------------------------------------//
// author:   Keksbendiger <keksbendiger@gmail.com>
// project:  EBS_VS_2022_Modelleisenbahn
// created:  21.07.2022
//--------------------------------------------------//
public class Logger {
    static String YELLOW = "\033[0;33m";
    //static String YELLOW = "\033[1;33m";
    static String RED = "\033[0;31m";
    static String RESET = "\033[0m";
    static String CYAN = "\033[36m";

    public static void log(String text) {
        System.out.println(YELLOW + text + RESET);
    }

    public static void err(String text) {
        System.out.println(RED + text + RESET);
    }

    public static void mqtt(String text) {
        System.out.println(CYAN + text + RESET);
    }
}
