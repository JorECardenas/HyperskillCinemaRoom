package cinema.model;

import cinema.model.DTOs.SeatDTO;

import java.util.ArrayList;
import java.util.List;

public class Room {

    int rows;

    int columns;

    List<Seat> seats;

    public Room(int r, int c){

        this.rows = r;
        this.columns = c;

        this.seats = new ArrayList<>();



        for(int i = 1; i <= r; i++){
            for (int j = 1; j <= c; j++){

                if(i <= 4){
                    this.seats.add(new Seat(i,j, 10));
                }else {
                    this.seats.add(new Seat(i,j, 8));
                }

            }
        }

    }

    public Room(int r, int c, List<Seat> s){
        this.seats = s;
        this.columns = c;
        this.rows = r;
    }


    public Room findAvailable(){
        List<Seat> available = this.seats.stream().filter(x -> !x.isPurchased()).toList();

        return new Room(this.rows, this.columns, available);
    }



    public Seat purchase(SeatDTO seat){
        for(Seat s: seats){
            if(s.getRow() == seat.getRow() && s.getColumn() == seat.getColumn()){

                if(s.isPurchased()){
                    return null;
                }

                s.purchase();
                return s;

            }
        }

        return null;
    }

    public void refund(Seat seat){
        for(Seat s: seats){
            if(s.getRow() == seat.getRow() && s.getColumn() == seat.getColumn()){

                s.refund();
                return;

            }
        }
    }


    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public List<Seat> getSeats() {
        return seats;
    }


}
