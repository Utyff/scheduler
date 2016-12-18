
import java.util.Date;

/**
 * Created by Utyf on 19.12.2016.
 *
 */

public class Creator extends Thread {
    private Scheduler sch;

    Creator(Scheduler sch) {
        this.sch = sch;
    }

    @Override
    public void run() {
        long timeout, startTime;

        while( true ) {
            timeout = (long)(10000.0*Math.random());
            System.out.println(" - creator: " + timeout);
            try {
                sleep(timeout);
            } catch (InterruptedException e) {
                //e.printStackTrace();
                break;
            }

            startTime = System.currentTimeMillis() + (long)(10000.0*Math.random());
            sch.add(new Date(startTime), null);
        }
    }
}
