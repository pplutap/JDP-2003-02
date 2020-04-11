package com.kodilla.ecommercee.domain;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
public class GroupDto {
    private Long id;
    private String name;
}
