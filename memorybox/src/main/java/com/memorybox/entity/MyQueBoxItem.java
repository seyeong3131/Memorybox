package com.memorybox.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "my_que_box_item")
public class MyQueBoxItem extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name="my_que_box_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "my_que_box_id")
    private MyQueBox myQueBox;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    public static MyQueBoxItem createMyQueBoxItem(MyQueBox myQueBox, Question question){
        MyQueBoxItem myQueBoxItem = new MyQueBoxItem();
        myQueBoxItem.setMyQueBox(myQueBox);
        myQueBoxItem.setQuestion(question);
        return myQueBoxItem;
    }
}
