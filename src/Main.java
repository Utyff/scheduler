
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        Scheduler sch = new Scheduler();
        sch.start();
        Creator cre = new Creator(sch);
        cre.start();

        s.nextLine();

        sch.exit();
        cre.exit();
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
