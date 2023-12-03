import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


class Transaction {
    private String category;
    private double amount;

    public Transaction(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }
    public boolean isIncome() {
        return "Дохід".equals(category);
    }

}

public class Main {
    public static void main(String[] args) {


        // Припустимо, у вас є список операцій з категоріями та сумами
        List<Transaction> transactions = List.of(
                new Transaction("Дохід", 100.0),
                new Transaction("Витрати", 50.0),
                new Transaction("Дохід", 200.0),
                new Transaction("Витрати", 75.0)

        );
        Map<Boolean, Double> result = transactions.stream()
                .collect(Collectors.partitioningBy(
                        Transaction::isIncome,
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        System.out.println("Дохід: " + result.get(true));
        System.out.println("Витрати: " + result.get(false));
    }
}
