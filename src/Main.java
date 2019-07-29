import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        BusHalt busHalt = BusHalt.getBusHalt(0);

        Scanner sc = new Scanner(System.in);

        System.out.println("Senate Bus initiated...");
        System.out.println("Press Enter to exit at any time.");

        new RiderScheduler(busHalt).start();

        new BusScheduler(busHalt).start();

        // Program Termination with a user input
        while(true){
            if(sc.nextLine().equals(""))
                System.exit(0);
        }
    }
}
