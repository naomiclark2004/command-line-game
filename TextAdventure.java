import java.util.Timer;
import java.util.TimerTask;

public class TextAdventure {
    static int interval;
    static Timer timer;

    public static void main(String[] args) {
        // first the timer will start with 2 minnutes left at the start
        String secs = "120";
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        interval = Integer.parseInt(secs);
        int seconds = Integer.parseInt(secs);
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                if (interval == 1) {
                    // if timer gets to 1 end timer and display that time is over and user has lost
                    // game
                    timer.cancel();
                    System.out.println("Time's up! GAME OVER : (");
                    System.exit(0);
                } else if (interval == seconds / 2) {
                    // if time is half way done / there is one minute left display time warning
                    System.out.println("01:00 minute left");
                }

                --interval;

            }
        }, delay, period);

        // then start game
        Game game = new Game();
        game.startGame();
    }

}