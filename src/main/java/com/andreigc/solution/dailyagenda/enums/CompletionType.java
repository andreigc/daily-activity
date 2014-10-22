package com.andreigc.solution.dailyagenda.enums;

import java.util.HashMap;
import java.util.Map;

public enum CompletionType {

    ALL(0), COMPLETED(1), UNFINISHED(2);

    private CompletionType(int ordinal) {
	this.ordinal = ordinal;
    }

    public static CompletionType getCompletionTypeById(int id) {
	return map.get(id);
    }

    private static Map<Integer, CompletionType> map = new HashMap<Integer, CompletionType>();
    static {
	for (CompletionType value : CompletionType.values()) {
	    map.put(value.ordinal, value);
	}
    }

    private int ordinal;
}
