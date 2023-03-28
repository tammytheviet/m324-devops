package com.example.demo;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/** the simplest task 
 * 
 * @author luh
 */

@Entity
@Table(name="/task")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Nonnull
	private String taskdescription; // must have the EXACT name as his React state property and may not be ignored!

	public String getTaskdescription() { // do not apply camel-case here! Its a Bean!
		return taskdescription;
	}

	public Task(Integer id, String taskdescription) {
		this.id = id;
		this.taskdescription = taskdescription;
	}

	public Task(String taskdescription){
		this.taskdescription = taskdescription;
	}

	public void setTaskdescription(String taskdescription) { // do not apply camel-case here! Its a Bean!
		this.taskdescription = taskdescription;
	}

}