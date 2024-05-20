package flights.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight implements Serializable {
    private int id;
    private String name;
    private String from;
    private String to;
    private double price;

    public Flight(String name, String from, String to, double price) {
        this.id = 0;
        this.name = name;
        this.from = from;
        this.to = to;
        this.price = price;
    }
}
