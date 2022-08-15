package com.memorybox.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "saveQueBook")
@ToString
public class SaveQueBook{
    @Id
    @Column(name = "saveQueBook_Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static SaveQueBook createSaveQueBook(Member member){
        SaveQueBook saveQueBook = new SaveQueBook();
        saveQueBook.setMember(member);
        return saveQueBook;
    }


}
