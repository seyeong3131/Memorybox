package com.memorybox.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="saveQue")
public class SaveQue extends BaseEntity{
    @Id
    @Column(name="saveQue_id")
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "saveQueBook_Id")
    private SaveQueBook saveQueBook;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "que_id")
    private Question question;

    public static SaveQue createSaveQue(SaveQueBook saveQueBook, Question question){
        SaveQue saveQue = new SaveQue();
        saveQue.setSaveQueBook(saveQueBook);
        saveQue.setQuestion(question);
        return saveQue;
    }

}
