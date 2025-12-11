package oncall.model;

import java.util.*;

/**
 * 비상 근무표, 근무자 배정 로직 담당
 */
public class Scheduler {
    private final Calendar calendar;
    private final List<Worker> workSchedule;

    private Scheduler(Calendar calendar, List<Worker> workSchedule) {
        this.calendar = calendar;
        this.workSchedule = workSchedule;
    }

    public static Scheduler of(Calendar calendar, Workers weekdayWorkers, Workers holidayWorkers) {
        List<Worker> workSchedule = new ArrayList<>();
        workSchedule.add(null);
        LinkedList<Worker> weekdayWorkersNotAssigned = getWorkers(weekdayWorkers);
        LinkedList<Worker> holidayWorkersNotAssigned = getWorkers(holidayWorkers);
        for (int i = 1; i <= calendar.getLastDateOfMonth(); i++) {
            DayOfWeekKOR dayOfWeek = calendar.getDayOfMonth(i);
            Worker worker = null;
            boolean isHoliday = dayOfWeek.isHoliday() || calendar.isHoliday(i);
            if (!isHoliday) {
                worker = assignWorker(weekdayWorkersNotAssigned, weekdayWorkers);
            }
            if (isHoliday) {
                worker = assignWorker(holidayWorkersNotAssigned, holidayWorkers);
            }
            if (i > 1) {
                if (worker.isSame(workSchedule.get(i - 1))) {
                    Worker workerOnDutyInARow = worker;
                    if (!isHoliday) {
                        worker = assignWorker(weekdayWorkersNotAssigned, weekdayWorkers);
                        weekdayWorkersNotAssigned.addFirst(workerOnDutyInARow);
                    }
                    if (isHoliday) {
                        worker = assignWorker(holidayWorkersNotAssigned, holidayWorkers);
                        holidayWorkersNotAssigned.addFirst(workerOnDutyInARow);
                    }
                }
            }
            workSchedule.add(worker);
        }
        return new Scheduler(calendar, workSchedule);
    }

    public Worker getWorkerOnDuty(int date) {
        return workSchedule.get(date);
    }

    private static LinkedList<Worker> getWorkers(Workers workers) {
        LinkedList<Worker> workersNotAssigned = new LinkedList<>();
        for (int i = 0; i < workers.size(); i++) {
            workersNotAssigned.add(workers.get(i));
        }
        return workersNotAssigned;
    }

    private static Worker assignWorker(LinkedList<Worker> workersNotAssigned, Workers workers) {
        if (workersNotAssigned.isEmpty()) {
            workersNotAssigned = getWorkers(workers);
        }
        return workersNotAssigned.pollFirst();
    }
}
