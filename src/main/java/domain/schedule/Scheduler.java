package domain.schedule;

import java.util.List;

public class Scheduler {

    public static List<Schedule> findCommonSchedule(List<List<Schedule>> listOfSchedules) {
        List<Schedule> finalScheduleList = listOfSchedules.get(0);
        listOfSchedules.stream().reduce(finalScheduleList, (list1, list2) -> {
            list1.retainAll(list2);
            return list1;
        });
        return finalScheduleList;
    }
}
