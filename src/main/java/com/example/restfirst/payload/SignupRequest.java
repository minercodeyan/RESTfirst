package com.example.restfirst.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class SignupRequest {
    @Size(min = 3, max = 20)
    @JsonProperty(value = "username")
    private String username;

    @Size(max = 50)
    @Email
    @JsonProperty(value = "email")
    private String email;

    @NotNull
    @Size(min = 6, max = 40)
    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "role")
    private Set<String> role;

}
