import java.util.concurrent.Semaphore;

public class BusHalt {

    private static BusHalt busHalt = null;

    private int waitingCount;

    private final Semaphore mutex = new Semaphore(1);
    private final Semaphore bus = new Semaphore(0);
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
