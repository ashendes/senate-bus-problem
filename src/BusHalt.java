import java.util.concurrent.Semaphore;

/**
 *  Class for keeping track of arrived riders and
 *  for controlling semaphore distribution
 */
public class BusHalt {

    // Singleton instance of BusHalt
    private static volatile BusHalt busHalt = null;

    // Number of riders waiting to board a bus
    private int waitingCount;

    // Mutex for controlling updates of waitingCount
    private final Semaphore mutex = new Semaphore(1);

    // Semaphore to indicating arrival of bus
    private final Semaphore bus = new Semaphore(0);

    // Semaphore to indicate that bus has finished boarding
    private final Semaphore boarded = new Semaphore(0);

    private BusHalt(int count){
        this.waitingCount = count;
    }

    public static BusHalt getBusHalt(int count){
        if (busHalt == null) {
            busHalt = new BusHalt(count);
        }
        return busHalt;
    }


    public int getWaitingCount() {
        return waitingCount;
    }

    public void setWaitingCount(int waitingCount) {
        this.waitingCount = waitingCount;
    }

    public void incrementWaitingCount(){
        waitingCount++;
    }

    public Semaphore getMutex() {
        return mutex;
    }

    public Semaphore getBus() {
        return bus;
    }

    public Semaphore getBoarded() {
        return boarded;
    }
}
