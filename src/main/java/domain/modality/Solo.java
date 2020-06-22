package domain.modality;

import domain.Student;

import java.util.List;

public class Solo extends Modality {
    @Override
    public int maxGroupSize() {
        return 1;
    }
    
    @Override
    public boolean canAddToGroup(List<Student> groupFormed) {
        return false;
    }
}
