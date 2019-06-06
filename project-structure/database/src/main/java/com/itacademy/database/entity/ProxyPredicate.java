package com.itacademy.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProxyPredicate {

    private String resource;
    private String category;
    private Integer price;

    public ProxyPredicate(String resource, String category) {
        this.resource = resource;
        this.category = category;
    }
}
