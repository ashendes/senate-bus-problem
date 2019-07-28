import java.util.concurrent.Semaphore;

public class Rider extends Thread {
    private BusHalt busHalt;
    private Semaphore bus;
    private Semaphore boarded;
    private Semaphore mutex;
    private static volatile int spawnedRiderCount = 0;
    private int riderId;

    public Rider(BusHalt busHalt){
        this.busHalt = busHalt;
        this.bus = busHalt.getBus();
        this.boarded = busHalt.getBoarded();
        this. mutex = busHalt.getMutex();
    }

    @Override
    public void run(){
        riderId = spawnedRiderCount++;
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        busHalt.incrementWaitingCount();
        mutex.release();

        try {
            bus.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        board();
        boarded.release();
    }

    private void board(){
        System.out.println("Rider " + riderId + " is boarding");
    }
}
