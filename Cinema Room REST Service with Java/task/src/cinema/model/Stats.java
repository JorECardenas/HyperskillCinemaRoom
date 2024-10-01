package cinema.model;

public class Stats {

    int income;
    int available;
    int purchased;

    public Stats(int seats){
        this.available = seats;
        this.income = 0;
        this.purchased = 0;
    }

    public void addPurchase(int price){
        this.income += price;
        this.available -= 1;
        this.purchased += 1;
    }

    public void addRefund(int price){
        this.income -= price;
        this.available += 1;
        this.purchased -= 1;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void setPurchased(int purchased) {
        this.purchased = purchased;
    }

    public int getAvailable() {
        return available;
    }

    public int getIncome() {
        return income;
    }

    public int getPurchased() {
        return purchased;
    }
}
