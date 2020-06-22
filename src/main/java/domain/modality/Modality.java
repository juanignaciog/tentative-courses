package domain.modality;

import domain.Student;

import java.util.List;

public abstract class Modality {
    public abstract int maxGroupSize();
	
	public abstract boolean canAddToGroup(List<Student> groupFormed);
}
