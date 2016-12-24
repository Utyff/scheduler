
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
            e.printStackTrace();
        }
    }

    @Override
    public Object call() throws Exception {
        System.out.println( Main.date2String() +" - run at: "+ Main.date2String(runAt) + " - created: "+Main.date2String(created) );
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
