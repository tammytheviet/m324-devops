package com.example.demo.requests;

public class TodoRequest {
    
    private String taskdescription;

    public void setTaskdescription(int id, String taskdescription){
        this.taskdescription = taskdescription;
    }

    public String getTaskdescription(){
        return taskdescription;
    }

}
