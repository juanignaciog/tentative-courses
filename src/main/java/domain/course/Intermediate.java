package domain.course;

import domain.Level;

public class Intermediate extends Course{
    @Override
    public Level getLevel() {
        return Level.INTERMEDIATE;
    }
}
