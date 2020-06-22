package domain.modality;

import domain.Student;

import java.util.List;

public class Group extends Modality{
    private static final int maxGroupSize = 6;

    @Override
    public int maxGroupSize() {
        return maxGroupSize;
    }
    
    @Override
    public boolean canAddToGroup(List<Student> groupFormed) {
        return maxGroupSize() >= groupFormed.size();
    }
}
