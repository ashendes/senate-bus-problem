import java.util.Random;

public class BusScheduler extends Thread {

    private static final long busMeanArrivalTime = 50 * 1000;
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
            Bus bus = new Bus(busHalt);
            bus.start();

            nextArrivalTime = calculateNextArrivalTime();

            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private long calculateNextArrivalTime(){
        float lambda = 1 / busMeanArrivalTime;
        return Math.round(-Math.log(1 - random.nextFloat()) / lambda);
    }
}
