
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
            System.out.println(Main.date2String() +" - creator sleep for: "+ timeout);
            try {
                sleep(timeout);
            } catch (InterruptedException e) {
                System.out.println(Main.date2String() +" - Creator exit -");
                break;
            }

            startTime = System.currentTimeMillis() + (long)(10000.0*Math.random());
            System.out.println(Main.date2String() +" - create new: " + Main.date2String(startTime));
            sch.add(new Date(startTime), null);
        }
    }

    void exit() {
        this.interrupt();
    }
}
