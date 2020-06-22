package domain.course;

import domain.Level;

public class BeginnerCourse extends Course{
    @Override
    public Level getLevel() {
        return Level.BEGINNER;
    }
}
