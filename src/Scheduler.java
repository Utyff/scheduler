
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.concurrent.Callable;

/**
 * Created by Utyf on 17.12.2016.
 *
 */

public class Scheduler extends Thread {
    private final LinkedList<Record> list = new LinkedList<>();
    private boolean exit;

    @SuppressWarnings("SameParameterValue")
    void add(Date runAt, Callable call) {
        Record recNew = new Record(runAt, call);

        synchronized( list ) {
            ListIterator<Record> iterator = list.listIterator(list.size());

            while (iterator.hasPrevious()) {
                if (recNew.compareTo(iterator.previous()) >= 0) {
                    insert(iterator, recNew);
                    this.interrupt();
                    return;
                }
            }

            list.addFirst(recNew);
            this.interrupt();
        }
    }

    private void insert(ListIterator<Record> iterator, Record rec) {
        if( iterator.hasNext() ) {
            iterator.next();
            iterator.add(rec);
        }
        else list.addLast(rec);
    }

    @Override
    public void run() {

        while(true) {
            try {
                Thread.sleep(getSleepTime());
                runJobs();
            } catch (InterruptedException e) {
                if( exit ) {
                    System.out.println(Main.date2String() + " - Scheduler exit -");
                    break;
                }
            }
        }
    }

    private long getSleepTime() {
        if( list.size()==0 )
            return 100000; // too long sleep
        long timeout = list.get(0).runAt.getTime() - System.currentTimeMillis();
        return timeout<0 ? 0:timeout;
    }

    void exit() {
        exit = true;
        this.interrupt();
    }

    private void runJobs() {
        Date now = new Date();

        while( list.size()>0 ) {
            Record rec = list.getFirst();

            if( rec.runAt.compareTo(now)==1 )
                return;

            rec.start();
            list.removeFirst();
        }
    }

/*    void test() {
        long start = System.currentTimeMillis();
        for (int i=0; i<20; i++) {
            long tt = (long)(10000*Math.random());
            this.add(new Date(start+tt),null);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.printJobs();
    }


    private void printJobs() {
        for(Record rec : list)
            try {
                rec.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }//*/
}
