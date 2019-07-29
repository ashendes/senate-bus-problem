import java.util.Random;

public class BusScheduler extends Thread {

    private static final long busMeanArrivalTime = 20 * 1000;
    private BusHalt busHalt;
    private long nextArrivalTime;
    private Random random;

    public  BusScheduler(BusHalt busHalt){
        this.busHalt = busHalt;
        random = new Random();
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            nextArrivalTime = calculateNextArrivalTime(busMeanArrivalTime);
            System.out.println(nextArrivalTime);
            try {
                Thread.sleep(nextArrivalTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Bus bus = new Bus(busHalt);
            bus.start();
        }
    }

    // Calculating a randomized time based on mean arrival time
    private long calculateNextArrivalTime(long meanArrivalTime) {
//        float lambda = 1 / busMeanArrivalTime;
//        return Math.round(-Math.log(1 - random.nextFloat()) / lambda);
        return meanArrivalTime;
    }
}
