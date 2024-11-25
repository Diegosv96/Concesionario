package modelo;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Coche implements Serializable {
    @Serial
    private final static long serialVersionUID = 12345678L;
    private int id;
    private String matricula, marca, modelo, color;
}
