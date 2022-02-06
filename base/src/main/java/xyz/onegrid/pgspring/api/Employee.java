package xyz.onegrid.pgspring.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
}
