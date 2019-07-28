import java.util.Random;

public class RiderScheduler extends Thread {

    private static final long riderMeanArrivalTime = 1 * 1000;
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
            Rider rider = new Rider(busHalt);
            rider.start();

            nextArrivalTime = calculateNextArrivalTime();

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private long calculateNextArrivalTime(){
        float lambda = 1 / riderMeanArrivalTime;
        return Math.round(-Math.log(1 - random.nextFloat()) / lambda);
    }
}