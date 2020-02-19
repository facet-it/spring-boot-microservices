package com.lightspeed.restaurant.usage;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDetails {

    private int oid;
    private String uuid;
    private String username;
    private String type;

}
