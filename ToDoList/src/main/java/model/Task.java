package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString

public class Task {
    private String taskname;

    public Task(String taskname){
        this.taskname=taskname;

    }
}
