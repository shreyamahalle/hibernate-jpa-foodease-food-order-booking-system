package com.shreya.hibernate.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Notification {
    private Long id;
    private Long customer_id;
    private String message;
    private boolean read;
    private String timestamp;
}
