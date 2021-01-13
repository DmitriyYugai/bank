package bank.models;

import java.util.Objects;

public class Account {
    private int number;
    private int sum;
    private int clientId;

    public Account() {
    }

    public Account(int number, int sum, int clientId) {
        this.number = number;
        this.sum = sum;
        this.clientId = clientId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return number == account.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "Account{" +
                "number=" + number +
                ", sum=" + sum +
                ", clientId=" + clientId +
                '}';
    }
}
