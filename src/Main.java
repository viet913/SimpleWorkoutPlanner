import java.util.*;

class Exercise {
    private String name;
    private String category;
    private List<String> daysOfWeek;
    private int sets;
    private int reps;

    public Exercise(String name, String category, List<String> daysOfWeek, int sets, int reps) {
        this.name = name;
        this.category = category;
        this.daysOfWeek = daysOfWeek;
        this.sets = sets;
        this.reps = reps;
    }

    public List<String> getDaysOfWeek() {
        return daysOfWeek;
    }

    @Override
    public String toString() {
        return name + " (" + category + ") - Sets: " + sets + ", Reps: " + reps;
    }
}

class WorkoutPlanner {
    private Map<String, Map<String, List<Exercise>>> workoutPlan;

    public WorkoutPlanner() {
        this.workoutPlan = new HashMap<>();
        this.workoutPlan.put("monday", new HashMap<>());
        this.workoutPlan.put("tuesday", new HashMap<>());
        this.workoutPlan.put("wednesday", new HashMap<>());
        this.workoutPlan.put("thursday", new HashMap<>());
        this.workoutPlan.put("friday", new HashMap<>());
        this.workoutPlan.put("saturday", new HashMap<>());
        this.workoutPlan.put("sunday", new HashMap<>());
    }

    public void addExercise(List<String> daysOfWeek, String category, String name, int sets, int reps) {
        for (String dayOfWeek : daysOfWeek) {
            if (workoutPlan.containsKey(dayOfWeek)) {
                if (!workoutPlan.get(dayOfWeek).containsKey(category)) {
                    workoutPlan.get(dayOfWeek).put(category, new ArrayList<>());
                }
                Exercise exercise = new Exercise(name, category, daysOfWeek, sets, reps);
                workoutPlan.get(dayOfWeek).get(category).add(exercise);
            } else {
                System.out.println("Invalid day of the week: " + dayOfWeek);
            }
        }
    }

    public void removeExercise(String dayOfWeek, String category, Exercise exercise) {
        if (workoutPlan.containsKey(dayOfWeek) && workoutPlan.get(dayOfWeek).containsKey(category)) {
            workoutPlan.get(dayOfWeek).get(category).remove(exercise);
        } else {
            System.out.println("Invalid day of the week or category.");
        }
    }

    public Map<String, Map<String, List<Exercise>>> getWorkoutPlan() {
        return workoutPlan;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WorkoutPlanner planner = new WorkoutPlanner();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add Exercise");
            System.out.println("2. Remove Exercise");
            System.out.println("3. View Workout Plan");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter exercise name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter category (Push, Pull, or Legs): ");
                    String category = scanner.nextLine().toLowerCase();
                    System.out.print("Enter days of the week to add (comma-separated, e.g., Monday,Tuesday): ");
                    String[] daysArray = scanner.nextLine().toLowerCase().split(",");
                    List<String> daysOfWeek = Arrays.asList(daysArray);
                    System.out.print("Enter number of sets: ");
                    int sets = scanner.nextInt();
                    System.out.print("Enter number of reps: ");
                    int reps = scanner.nextInt();
                    planner.addExercise(daysOfWeek, category, name, sets, reps);
                    break;
                case 2:
                    System.out.print("Enter exercise name: ");
                    String removeName = scanner.nextLine();
                    System.out.print("Enter category (Push, Pull, or Legs): ");
                    String removeCategory = scanner.nextLine().toLowerCase();
                    System.out.print("Enter day of the week (Monday-Sunday): ");
                    String removeDayOfWeek = scanner.nextLine().toLowerCase();
                    Exercise removeExercise = new Exercise(removeName, removeCategory, Collections.singletonList(removeDayOfWeek), 0, 0);
                    planner.removeExercise(removeDayOfWeek, removeCategory, removeExercise);
                    break;
                case 3:
                    System.out.println("Workout Plan:");
                    for (Map.Entry<String, Map<String, List<Exercise>>> dayEntry : planner.getWorkoutPlan().entrySet()) {
                        System.out.println(dayEntry.getKey() + ":");
                        for (Map.Entry<String, List<Exercise>> categoryEntry : dayEntry.getValue().entrySet()) {
                            System.out.println("\t" + categoryEntry.getKey() + ": ");
                            for (Exercise e : categoryEntry.getValue()) {
                                if (e.getDaysOfWeek().contains(dayEntry.getKey())) {
                                    System.out.println("\t\t- " + e);
                                }
                            }
                        }
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
