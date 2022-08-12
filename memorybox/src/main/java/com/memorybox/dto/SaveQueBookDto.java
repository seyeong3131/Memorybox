package com.memorybox.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SaveQueBookDto {

    private Long saveQueId;

    private List<SaveQueBookDto> saveQueBookDtoList;
}
