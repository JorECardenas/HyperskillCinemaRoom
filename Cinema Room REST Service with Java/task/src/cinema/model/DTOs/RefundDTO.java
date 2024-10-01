package cinema.model.DTOs;

import cinema.model.Seat;

public class RefundDTO {

    Seat ticket;


    public RefundDTO(Seat s){
        this.ticket = s;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
}
