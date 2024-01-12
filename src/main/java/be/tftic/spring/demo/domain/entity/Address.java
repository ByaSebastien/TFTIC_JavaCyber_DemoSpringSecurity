package be.tftic.spring.demo.domain.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Address {

    @Column(name = "address_number")
    private int number;
    @Column(name = "address_street")
    private String street;
    private int zipcode;
    private String city;
    private String country;

}
