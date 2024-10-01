package cinema.model;

import java.util.UUID;

public class Ticket {

    String token;
    Seat ticket;


    public Ticket(Seat s){
        token = UUID.randomUUID().toString();
        this.ticket = s;
    }

    public Seat getTicket() {
        return ticket;
    }

    public String getToken() {
        return token;
    }

    public void setTicket(Seat seat) {
        this.ticket = seat;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

