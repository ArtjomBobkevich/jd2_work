package com.itacademy.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor/*(onConstructor = @__(@Autowired))*/
@Component
public class Count {

    private Integer count;
}
