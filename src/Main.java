
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
        /*while( !s.nextLine().equals("1")) {
            sch.add( new Date(),null );
        } //*/

        sch.exit();
    }
}
