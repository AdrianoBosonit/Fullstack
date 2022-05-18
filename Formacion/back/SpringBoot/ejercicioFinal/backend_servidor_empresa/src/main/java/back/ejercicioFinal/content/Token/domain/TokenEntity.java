package back.ejercicioFinal.content.Token.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "token")
@NoArgsConstructor
public class TokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idToken;

    @Column
    private String content;

    @Column
    private String header;

    public TokenEntity(String content, String header) {
        this.content = content;
        this.header = header;
    }

    @Override
    public String toString() {
        return header + " " + content;
    }
}
