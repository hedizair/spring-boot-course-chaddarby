package com.hzair.springboot.cruddemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;

@Entity
@Table(name = "instructor_detail")
public class InstructorDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // * let the db handle the id generation
    @Column(name = "id")
    private int id;

    @Column(name = "youtube_channel")
    private String youtubeChannel;

    @Column(name = "hobby")
    private String hobby;

    @OneToOne(mappedBy="instructorDetail", cascade=CascadeType.ALL) // * In that way, Spring will use Instructor.instructorDetail to find the @JoinColumn(name = "instructor_detail_id") and find the good Instructor
    private Instructor instructor;

    public InstructorDetail() {
    }; // No args constructor for autwired// No args constructor for autwired

    public InstructorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }

    public int getId() {
        return id;
    }

    public String getHobby() {
        return hobby;
    }

    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", youtubeChannel='" + getYoutubeChannel() + "'" +
            ", hobby='" + getHobby() + "'" +
            "}";
    }

}
