import java.util.Timer;
import java.util.TimerTask;

public class Stopwatch {
    static int interval;
    static Timer timer;

    public static void main(String[] args) {
        String secs = "60";
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        interval = Integer.parseInt(secs);
        System.out.println(secs);

        int seconds = Integer.parseInt(secs);
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                if (interval == 1) {
                    timer.cancel();
                    System.out.println(interval);
                } else if (interval == seconds / 2) {
                    System.out.println("Times half done");
                } else if (interval < 11) {
                    System.out.println(interval);
                }

                --interval;

            }
        }, delay, period);
    }
}