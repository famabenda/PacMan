package utils;

public class Logger {


    public static final String RESET = "\033[0m";
    private static final String RED = "\033[0;31m";
    private static final String YELLOW = "\033[0;33m";
    public static final String CYAN = "\033[0;36m";


    public static void log(String log) {
        System.out.println("[LOG] : " + log);
    }


    public static void warning(String warning) {
        System.out.println(YELLOW + "[WARNING] : " + warning +
                RESET);
    }

    public static void error(String error) {
        System.out.println(RED + "[ERROR] : " + error +
                RESET);
    }

    public static void debug(String debugLog) {
        System.out.println(CYAN + "[DEBUG] : " + debugLog + RESET);
    }
}
