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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "queBook_id")
    private SaveQueBook saveQueBook;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "que_id")
    private Question question;

    private  int count;

    public static SaveQue createSaveQue(SaveQueBook saveQueBook, Question question,int count){
        SaveQue saveQue = new SaveQue();
        saveQue.setSaveQueBook(saveQueBook);
        saveQue.setQuestion(question);
        saveQue.setCount(count);
        return saveQue;
    }
    public void addCount(int count){
        this.count += count;
    }

    public void updateCount(int count) { this.count = count; }

}
