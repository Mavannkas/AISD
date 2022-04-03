package ludwiniak.wiktor.Cw.L4;

import java.util.ArrayList;
import java.util.Objects;

public class Department {
    private final ArrayList<Worker> workers = new ArrayList<>();
    private int time = 0;

    public Department() {
        workers.add(new Worker("A"));
        workers.add(new Worker("B"));
        workers.add(new Worker("C"));
    }

    public int unloadQueue(TwoWayLinkedListQueue<Client> clients) {
        try {
            while (!clients.isEmpty()) {
                jumpInTime();
                Worker worker = getFirstUnBusyWorker();
                Client client = clients.dequeue();
                System.out.printf("Klient %2d podchodzi do pracownika %1s o czasie %4d\n", client.id, worker.id, time);
                worker.addClient(client);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        unloadQueue();

        return time;
    }

    private Worker getFirstUnBusyWorker() {
        for (Worker worker : workers) {
            if (!worker.isBusy(time)) {
                return worker;
            }
        }
        return null;
    }

    private boolean jumpInTime() {
        Worker worker = getWorker();
        time = worker.busyTill;
        if(worker.hasClient()) {
            System.out.printf("Klient %2d został obsłużony przez %1s w %4d\n", worker.client.id, worker.id, worker.client.time);
            worker.clearClient();
            return true;
        }

        return false;
    }

    private void unloadQueue() {
        while (!isAllWorkersFree()) {
            time++;
            Worker worker = getFirstUnBusyWorkerWithClient();
            if(!Objects.isNull(worker)) {
                System.out.printf("Klient %2d został obsłużony przez %1s w %4d\n", worker.client.id, worker.id, worker.client.time);
                worker.clearClient();
            }
        }
    }

    private Worker getFirstUnBusyWorkerWithClient() {
        for (Worker worker : workers) {
            if (!worker.isBusy(time) && worker.hasClient()) {
                return worker;
            }
        }
        return null;
    }

    private boolean isAllWorkersFree() {
        for (Worker worker : workers) {
            if (worker.isBusy(time)) {
                return false;
            }
        }
        return true;
    }

    private Worker getWorker() {
        Worker minWorker = workers.get(0);
        for (int i = 1; i < 3; i++) {
            if (minWorker.busyTill > workers.get(i).busyTill) {
                minWorker = workers.get(i);
            }
        }

        return minWorker;
    }
}
