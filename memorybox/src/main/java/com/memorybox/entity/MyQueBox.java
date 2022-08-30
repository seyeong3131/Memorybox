package com.memorybox.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="my_que_box")
@Getter
@Setter
@ToString
public class MyQueBox {
    @Id
    @Column(name="my_que_box_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static MyQueBox createMyQueBox(Member member){
        MyQueBox myQueBox = new MyQueBox();
        myQueBox.setMember(member);
        return myQueBox;
    }
}
