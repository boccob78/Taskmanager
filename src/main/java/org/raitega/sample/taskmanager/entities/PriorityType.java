package org.raitega.sample.taskmanager.entities;

/**
 * An enum of Priority values for the Task
 * @author      Farzan Zubair
 */

public enum PriorityType {

	LOW(1), NORMAL(2), HIGH(3);

	private int value;
	private PriorityType(int value){
		this.value = value;
	}
	public int getValue(){
		return value;
	}
}
