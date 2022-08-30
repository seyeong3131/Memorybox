package com.memorybox.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SaveQueBookDto {

    private Long saveQueId;

    public SaveQueBookDto(Long saveQueId){
        this.saveQueId = saveQueId;
    }

  //  private List<SaveQueBookDto> saveQueBookDtoList;
}
