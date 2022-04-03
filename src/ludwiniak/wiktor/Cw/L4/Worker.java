package ludwiniak.wiktor.Cw.L4;

import java.util.Objects;

public class Worker {
    String id;
    int busyTill = 0;
    Client client;

    public Worker(String id) {
        this.id = id;
    }

    public boolean isBusy(int time) {
        return time < busyTill;
    }

    public void addTime(int time) {
        busyTill += time;
    }

    public void addClient(Client client) {
        this.client = client;
        addTime(client.time);
    }

    public boolean hasClient() {
        return !Objects.isNull(client);
    }

    public void clearClient() {
        client = null;
    }
}
