
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        Scheduler sch = new Scheduler();
//        sch.test(); System.exit(0);
        sch.start();

        Creator cre1 = new Creator(sch, "1");
        cre1.start();
        Creator cre2 = new Creator(sch, "2");
        cre2.start();

        s.nextLine(); // wait for exit

        cre1.exit();
        cre2.exit();
        sch.exit();
    }

    static String date2String() {
        return date2String(new Date());
    }

    static String date2String(long dt) {
        return date2String(new Date(dt));
    }

    static String date2String(Date dt) {
        if( dt==null )
            dt = new Date();

        SimpleDateFormat fmt = new SimpleDateFormat("mm:ss.SSS");
        return fmt.format(dt);
    }
}
