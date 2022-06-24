package tr.edu.ege.pomodoroservice.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Pomodoro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //hangi task için
    private Long taskId;

    //kaç tane pomodoro yapılsın
    private int estimate;

    //tamamlanan pomodorolar
    private int completed;

    //tamamlanan pomodoro dakikası
    private int completedPomodoroTime;

    //pomodoro boyunca yapılan dinlenmeler
    private int breakPomodoroTime;

    @CreationTimestamp
    @CreatedDate
    @Builder.Default
    private Date createdAt = new Date();

    @UpdateTimestamp
    @LastModifiedDate
    @Builder.Default
    private Date updatedAt = new Date();
}