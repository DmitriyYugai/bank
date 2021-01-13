package bank.models;

import java.util.Objects;

public class Transaction {
    private int id;
    private Type type;
    private int sum;
    private Client client;
    private int number;
    private int exchNumber;

    public Transaction() {
    }

    public Transaction(Type type, int sum, Client client, int number, int exchNumber) {
        this.type = type;
        this.sum = sum;
        this.client = client;
        this.number = number;
        this.exchNumber = exchNumber;
    }

    public Transaction(int id, Type type, int sum, Client client, int number, int exchNumber) {
        this.id = id;
        this.type = type;
        this.sum = sum;
        this.client = client;
        this.number = number;
        this.exchNumber = exchNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getExchNumber() {
        return exchNumber;
    }

    public void setExchNumber(int exchNumber) {
        this.exchNumber = exchNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type=" + type +
                ", sum=" + sum +
                ", client=" + client +
                ", number=" + number +
                ", exchNumber=" + exchNumber +
                '}';
    }
}
