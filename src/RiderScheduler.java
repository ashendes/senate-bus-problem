import java.util.Random;

public class RiderScheduler extends Thread {

    private static final long riderMeanArrivalTime = 500;
    private BusHalt busHalt;
    private long nextArrivalTime;
    private Random random;

    public  RiderScheduler(BusHalt busHalt){
        this.busHalt = busHalt;
        random = new Random();
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            nextArrivalTime = calculateNextArrivalTime(riderMeanArrivalTime);

            try {
                Thread.sleep(nextArrivalTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Rider rider = new Rider(busHalt);
            rider.start();
        }
    }

    // Calculating a randomized time based on mean arrival time
    private long calculateNextArrivalTime(long meanArrivalTime){
//        float lambda = 1 / riderMeanArrivalTime;
//        return Math.round(-Math.log(1 - random.nextFloat()) / lambda);
        return meanArrivalTime;

    }
}