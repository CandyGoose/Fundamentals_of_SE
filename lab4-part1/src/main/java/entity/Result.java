package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@PersistenceUnit(
        name = "ResultUnit",
        unitName = "ResultUnit"
)
@PersistenceContext()
public class Result implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_generator")
    @SequenceGenerator(name = "result_generator", sequenceName = "result_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "x", nullable = false)
    private Double x;
    @Column(name = "y", nullable = false)
    private Double y;
    @Column(name = "r", nullable = false)
    private Double r;
    @Column(name = "hit", nullable = false)
    private Boolean hit;

    public Result(Result sourceResult) {
        this.id = sourceResult.id;
        this.x = sourceResult.getX();
        this.y = sourceResult.getY();
        this.r = sourceResult.getR();
        this.hit = checkHit();
    }

    public boolean checkHit() {
        boolean circle = (x <= 0) && (y >= 0) && (x * x + y * y <= (r / 2) * (r / 2));
        boolean triangle = (x >= 0) && (y >= 0) && (y <= r - 2 * x);
        boolean rectangle = (x >= 0) && (y <= 0) && (x <= r) && (y >= -r / 2);
        return circle || triangle || rectangle;
    }

    public String getStringSuccess() {
        return hit ? "Hit" : "Miss";
    }

    public String getClassSuccess() {
        return hit ? "hit" : "miss";
    }
}