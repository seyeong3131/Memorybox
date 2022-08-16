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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // JoinColumn 매핑할 외래키를 지정합니다. 외래키 이름을 설정
    // name을 명시하지 않으면 JPA가 알아서 ID를 찾지만, 원하는 이름이 아닐 수도 있습니다.
    //멤버에 있는 멤버아이디랑 쪼인
    private Member member;

/*    private  int count;*/

    public static SaveQue createSaveQue(SaveQueBook saveQueBook, Question question/*,int count*/){
        SaveQue saveQue = new SaveQue();
        saveQue.setSaveQueBook(saveQueBook);
        saveQue.setQuestion(question);
/*        saveQue.setCount(count);*/
        return saveQue;
    }
/*    public void addCount(int count){
        this.count += count;
    }

    public void updateCount(int count) { this.count = count; }*/

}
