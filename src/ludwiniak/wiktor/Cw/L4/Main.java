package ludwiniak.wiktor.Cw.L4;

import java.util.Random;

public class Main {
    private static Random random = new Random();
    public static void main(String[] args) {
        TwoWayLinkedListQueue<Client> clients = generateClientsQueue(20);
        Department department = new Department();
        System.out.println("Wszystko zajęło " + department.unloadQueue(clients));
    }

    private static TwoWayLinkedListQueue<Client> generateClientsQueue(int i) {
        TwoWayLinkedListQueue<Client> clients = new TwoWayLinkedListQueue<>(i);
        for (int j = 0; j < i; j++) {
            Client client = new Client(random.nextInt(90) + 10, j);

            try {
                clients.enqueue(client);
            }catch (Exception e) {

            }
        }
        return clients;
    }
}
