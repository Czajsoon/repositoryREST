package com.comarch.repo.user.domain;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@EqualsAndHashCode
public class DefaultUser implements UserInterface {
    private String id;
    private String login;
    private String firstName;
    private String lastName;
    private String password;
    private boolean admin;
}
