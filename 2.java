//assessment-1
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
//model
class Task {
    private String description;
    private int priority;
    private boolean isCompleted;

    public Task(String description, int priority) {
        this.description = description;
        this.priority = priority;
        this.isCompleted = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
//view
class TaskManagerView {
    public void displayTaskList(List<Task> tasks) {
        System.out.println("Task List:");
        System.out.println("------------------------");

        for (Task task : tasks) {
            String status = task.isCompleted() ? "Completed" : "Not Completed";
            System.out.println("Description: " + task.getDescription());
            System.out.println("Priority: " + task.getPriority());
            System.out.println("Status: " + status);
            System.out.println("------------------------");
        }
    }

    public Task getTaskInfoFromUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        System.out.print("Enter task priority (1 - High, 2 - Medium, 3 - Low): ");
        int priority = scanner.nextInt();

        return new Task(description, priority);
    }

    public int getTaskIndexFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the index of the task: ");
        return scanner.nextInt();
    }
}
//controller
import java.util.ArrayList;
import java.util.List;

public class TaskManagerController {
    private List<Task> tasks;
    private TaskManagerView view;

    public TaskManagerController(TaskManagerView view) {
        tasks = new ArrayList<>();
        this.view = view;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void markTaskAsCompleted(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setCompleted(true);
        }
    }

    public void displayTasks() {
        view.displayTaskList(tasks);
    }

    public void run() {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            System.out.println("Task Manager Application");
            System.out.println("------------------------");
            System.out.println("1. Add Task");
            System.out.println("2. Mark Task as Completed");
            System.out.println("3. Display Tasks");
            System.out.println("4. Quit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Task newTask = view.getTaskInfoFromUser();
                    addTask(newTask);
                    break;

                case 2:
                    view.displayTaskList(tasks);
                    int index = view.getTaskIndexFromUser();
                    markTaskAsCompleted(index);
                    break;

                case 3:
                    displayTasks();
                    break;

                case 4:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        }
    }
}
public class Main {
    public static void main(String[] args) {
        TaskManagerView view = new TaskManagerView();
        TaskManagerController controller = new TaskManagerController(view);
        controller.run();
    }
}
