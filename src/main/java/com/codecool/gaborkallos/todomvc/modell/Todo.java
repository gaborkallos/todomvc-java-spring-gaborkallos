package com.codecool.gaborkallos.todomvc.modell;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Todo {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Transient
    private boolean completed;

    public void setCompletedByStatus(){
        if (this.status.equals(Status.ACTIVE)){
            completed = false;
        }else if (this.status.equals(Status.COMPLETE)){
            completed = true;
        }else{
            //Todo errorhandling!
        }
    }


    public Todo(String title, Status status) {
        this.title = title;
        this.status = status;
    }
}
