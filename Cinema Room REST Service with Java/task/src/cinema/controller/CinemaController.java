package cinema.controller;

import cinema.model.DTOs.RefundDTO;
import cinema.model.DTOs.TicketDTO;
import cinema.model.Room;
import cinema.model.Seat;
import cinema.model.DTOs.SeatDTO;
import cinema.model.Stats;
import cinema.model.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class CinemaController {


    private final Room room;

    private final Map<String, Seat> soldSeats;

    private final Stats stats;

    private final int LINE_NUMBER = 9;

    private final String SECRET_KEY = "super_secret";


    public CinemaController(){


        this.room = new Room(LINE_NUMBER,LINE_NUMBER);

        this.soldSeats = new ConcurrentHashMap<>();

        this.stats = new Stats(LINE_NUMBER * LINE_NUMBER);

    }


    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody SeatDTO seat){

        if(seat.getColumn() < 1 || seat.getRow()  < 1 ){
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        }

        if(seat.getColumn() > room.getColumns() || seat.getRow() > room.getRows()){
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        }


        Seat purchase = room.purchase(seat);

        if(purchase == null){
            return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
        }

        Ticket ticket = new Ticket(purchase);

        soldSeats.put(ticket.getToken(), ticket.getTicket());

        stats.addPurchase(purchase.getPrice());

        return new ResponseEntity<>(ticket, HttpStatus.OK);



    }

    @PostMapping("/return")
    public ResponseEntity<?> refund(@RequestBody TicketDTO ticket){
        Seat seat = soldSeats.get(ticket.getToken());

        if(seat != null){
            room.refund(seat);

            soldSeats.remove(ticket.getToken());

            stats.addRefund(seat.getPrice());

            return new ResponseEntity<>(new RefundDTO(seat), HttpStatus.OK);

        }

        return new ResponseEntity<>(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);



    }

    @GetMapping("/stats")
    public ResponseEntity<?> info(@RequestParam Optional<String> password){

        if(password.isPresent() && SECRET_KEY.equals(password.get())){

            return new ResponseEntity<>(stats, HttpStatus.OK);

        }

        return new ResponseEntity<>(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);

    }


    @GetMapping("/seats")
    public ResponseEntity<Room> getSeats(){



        return new ResponseEntity<>(room.findAvailable(), HttpStatus.OK);
    }

}
