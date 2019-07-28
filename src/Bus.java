import java.util.concurrent.Semaphore;

public class Bus extends Thread {
    private BusHalt busHalt;
    private Semaphore bus;
    private Semaphore boarded;
    private Semaphore mutex;
    private long busId;

    public Bus(BusHalt busHalt) {
        this.busHalt = busHalt;
        this.bus = busHalt.getBus();
        this.boarded = busHalt.getBoarded();
        this. mutex = busHalt.getMutex();
        busId = Thread.currentThread().getId();
    }

    @Override
    public void run() {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Bus " + busId + " has arrived at the bus halt");
        int n = Math.min(busHalt.getWaitingCount(), 50);

        for (int i = 0; i < n; i++) {
            bus.release();
            try {
                boarded.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        busHalt.setWaitingCount(Math.max(busHalt.getWaitingCount() - 50, 0));
        mutex.release();

        depart();

    }

    private void depart() {
        System.out.println("Bus " + busId + " has departed");
    }

}
