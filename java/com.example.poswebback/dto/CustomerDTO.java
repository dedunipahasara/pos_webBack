package com.example.poswebback.dto;

import com.example.poswebback.entity.Customer;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class CustomerDTO extends Customer implements Serializable {
    private String id;
    private String name;
    private String address;
    private double salary;
}
