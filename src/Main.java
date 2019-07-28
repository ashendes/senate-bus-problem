import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        BusHalt busHalt = BusHalt.getBusHalt(0);

        System.out.print("Enter the number of riders: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println("Senate Bus initiated...");

        for(int i = 0; i < n; i++){
            new Rider(busHalt);
        }

        new Bus(busHalt);

        // Program Termination with a user input
        String _;
        while(true){
            _ = sc.nextLine();
            if(_ != null)
                System.exit(0);
        }

    }
}
