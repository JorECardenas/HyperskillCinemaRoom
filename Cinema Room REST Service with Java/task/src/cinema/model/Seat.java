package cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Seat {

    int row;
    int column;
    int price;

    @JsonIgnore
    boolean purchased;

    public Seat(int r, int c, int p){
        this.column = c;
        this.row = r;
        this.price = p;
        this.purchased = false;
    }

    public void purchase(){
        this.purchased = true;
    }

    public void refund() { this.purchased = false; }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public int getPrice() {
        return price;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }
}
