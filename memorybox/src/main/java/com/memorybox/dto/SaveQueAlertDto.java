package com.memorybox.dto;

import com.memorybox.entity.SaveQue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveQueAlertDto {
    private Long id;
    private Long saveQueBookId;

    public SaveQueAlertDto(SaveQue saveQue){
        this.id = saveQue.getId();
        this.saveQueBookId = saveQue.getSaveQueBook().getId();
    }
}
