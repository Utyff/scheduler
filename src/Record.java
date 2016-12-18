import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

/**
 * Created by Utyf on 17.12.2016.
 *
 */

public class Record extends Thread implements Callable, Comparable<Record> {
    Date runAt;
    private Date created;
    private Callable callable;

    Record(Date runAt, Callable call) {
        this.created = new Date();
        this.runAt   = runAt;
        this.callable = call==null ? this:call;
    }

    @Override
    public void run() {
        try {
            callable.call();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    @Override
    public Object call() throws Exception {
        SimpleDateFormat fmt = new SimpleDateFormat("mm:ss.SSS");
        System.out.println( fmt.format(created) +" - "+ fmt.format(runAt) +" - "+ fmt.format(new Date()) );
        return null;
    }

    @Override
    public int compareTo(Record r) {
        return this.runAt.compareTo(r.runAt);
/*        int compareRun = this.runAt.compareTo(r.runAt);
        if( compareRun!=0 )
            return compareRun;
        return this.created.compareTo(r.created); //*/
    }
}
