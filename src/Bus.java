import java.util.concurrent.Semaphore;

public class Bus extends Thread {
    private BusHalt busHalt;
    private Semaphore bus;
    private Semaphore boarded;
    private Semaphore mutex;

    // Number of total buses spawned
    private static volatile int spawnedBusCount = 0;
    private int busId;

    // Number of riders boarded
    private int passengerCount;

    public Bus(BusHalt busHalt) {
        this.busHalt = busHalt;
        this.bus = busHalt.getBus();
        this.boarded = busHalt.getBoarded();
        this. mutex = busHalt.getMutex();
        passengerCount = 0;
    }

    @Override
    public void run() {
        busId = spawnedBusCount++;

        try {
            mutex.acquire();

            System.out.println("Bus " + busId + " has arrived at the bus halt");

            passengerCount = Math.min(busHalt.getWaitingCount(), 50);

            for (int i = 0; i < passengerCount; i++) {
                bus.release();
                try {
                    boarded.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            busHalt.setWaitingCount(Math.max(busHalt.getWaitingCount() - 50, 0));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mutex.release();
        }

        depart();
    }

    private void depart() {
        System.out.format("Bus %d has departed with %d passengers\n", busId, passengerCount);
        System.out.format("Number of passengers waiting: %d \n", busHalt.getWaitingCount());
    }
}
