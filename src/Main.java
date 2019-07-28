import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        BusHalt busHalt = BusHalt.getBusHalt(0);

//        System.out.print("Enter the number of riders: ");
        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();

        System.out.println("Senate Bus initiated...");

        new RiderScheduler(busHalt).start();

        new BusScheduler(busHalt).start();

        // Program Termination with a user input
        int _;
        while(true){
            _ = sc.nextInt();
            if(_ != 0)
                System.exit(0);
        }

    }
}
