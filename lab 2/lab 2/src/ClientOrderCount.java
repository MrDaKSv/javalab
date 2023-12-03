public class ClientOrderCount {
    private Client client;
    private int orderCount;

    public ClientOrderCount(Client client, int orderCount) {
        this.client = client;
        this.orderCount = orderCount;
    }

    public Client getClient() {
        return client;
    }

    public int getOrderCount() {
        return orderCount;
    }
}

