package com.example.demo;

public class Group {
	
	private String group; // must have the EXACT name as his React state property and may not be ignored!

	public Group() {
    }

	public String group() { // do not apply camel-case here! Its a Bean!
		return group;
	}

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

}
