package com.mappings.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="instructor_detail")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstructorDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String youtube_channel;
    @Column
    private String hobby;

    public InstructorDetail(String youtube_channel, String hobby) {
        this.youtube_channel = youtube_channel;
        this.hobby = hobby;
    }
}
