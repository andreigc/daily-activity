package com.andreigc.solution.dailyagenda.enums;

import java.util.HashMap;
import java.util.Map;


public enum TaskType {
    STANDALONE(1), CONTAINER(2), SUBTASK(3);
    TaskType(int ordinal) {
	this.ordinal = ordinal;
    }

    private int ordinal;
    private static Map<Integer, TaskType> map = new HashMap<Integer, TaskType>();
    static {
	for (TaskType type : TaskType.values()) {
	    map.put(type.ordinal, type);
	}
    }
    
    public static TaskType toTaskType(int value){
	return map.get(value);
    }
    
    public boolean isTaskType(int ordinal){
	return this.ordinal == ordinal;
    }
}
