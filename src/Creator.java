
import java.util.Date;

/**
 * Created by Utyf on 19.12.2016.
 *
 */

public class Creator extends Thread {
    private Scheduler sch;

    Creator(Scheduler sch, String name) {
        this.sch = sch;
        this.setName(name);
    }

    @Override
    public void run() {
        long timeout, startTime;

        while( true ) {
            startTime = System.currentTimeMillis() + (long)(10000.0*Math.random());
            sch.add(new Date(startTime), null);

            timeout = (long)(10000.0*Math.random());
            System.out.println(Main.date2String() +" - creator "+getName()+" new: "
                             + Main.date2String(startTime)+" sleep for: "+ timeout);
            try {
                sleep(timeout);
            } catch (InterruptedException e) {
                System.out.println(Main.date2String() +" - Creator "+getName()+" exit -");
                break;
            }
        }
    }

    void exit() {
        this.interrupt();
    }
}
