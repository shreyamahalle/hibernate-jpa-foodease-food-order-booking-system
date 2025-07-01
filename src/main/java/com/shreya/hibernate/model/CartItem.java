package com.shreya.hibernate.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CartItem {
    private Long id;
    private Long customer_id;
    private Long menu_item_id;
    private Integer quantity;
}

