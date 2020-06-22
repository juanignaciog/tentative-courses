package domain.course;

import domain.Level;

public class AdvancedCourse extends Course{
    @Override
    public Level getLevel() {
        return Level.ADVANCED;
    }
}
