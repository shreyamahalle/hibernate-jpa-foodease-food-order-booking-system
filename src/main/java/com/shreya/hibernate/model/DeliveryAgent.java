package com.shreya.hibernate.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeliveryAgent {
    public int mobileNo;
    private int id;
    private String name;
    private String city;

    public DeliveryAgent(int id, String name, String city, int mobileNo) {
    }
}
