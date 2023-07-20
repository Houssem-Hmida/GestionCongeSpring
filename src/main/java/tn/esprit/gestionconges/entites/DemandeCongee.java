package tn.esprit.gestionconges.entites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class DemandeCongee  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDemande ;

    private String Reason ;
    @Enumerated(EnumType.STRING)
    private  TypeConge typeConge ;
    @Enumerated(EnumType.STRING)
    private Status Satuts ;

    @Temporal(TemporalType.DATE)
    private Date date_debut ;

    @Temporal(TemporalType.DATE)
    private Date date_fin ;



    @Temporal(TemporalType.DATE)
    private Date  date_creation ;


    @Temporal(TemporalType.DATE)
    private Date date_validation ;

    private String username;

    @ManyToOne
    private User user ;
}

