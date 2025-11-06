import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PE04_RodrigoEdgar {

    // ===== Variables Globals =====
    static boolean wholeLights = false;
    static boolean livingroomLight = false;
    static boolean restroomLight = false;
    static boolean kitchenLight = false;

    static boolean biometricSensor = true;
    static String biometricFaces = ""; // substitueix l’ArrayList

    static Scanner askOption = new Scanner(System.in);

    static boolean wholeThermostat = false;
    static boolean livingroomThermostat = false;
    static boolean restroomThermostat = false;
    static boolean kitchenThermostat = false;

    static double wholeThermostatTemp = 0.0;
    static double livingroomThermostatTemp = 0.0;
    static double restroomThermostatTemp = 0.0;
    static double kitchenThermostatTemp = 0.0;

    static String powerTemp = "";

    static boolean wholeMusic = false;
    static boolean livingroomMusic = false;
    static boolean restroomMusic = false;
    static boolean kitchenMusic = false;

    static String songWhole = "";
    static String songLiving = "";
    static String songRestroom = "";
    static String songKitchen = "";

    // ===== Main =====
    public static void main(String[] args) {

        boolean menu = true;
        while (menu) {
            System.out.println();
            System.out.println("Domotic House Menu:");
            System.out.println("1) Lights");
            System.out.println("2) Intelligent Doors");
            System.out.println("3) Thermostat");
            System.out.println("4) Music System");
            System.out.println("5) House Status");
            System.out.println("6) Exit");
            System.out.println();
            System.out.print("Select an option: ");

            int optionMenu = readIntSafe();

            // ===== LLUMS =====
            if (optionMenu == 1) {
                boolean lightsMenu = true;
                while (lightsMenu) {
                    System.out.println();
                    System.out.println("Lights Menu:");
                    System.out.println("1) Living Room");
                    System.out.println("2) Rest Room");
                    System.out.println("3) Kitchen");
                    System.out.println("4) All House (toggle all)");
                    System.out.println("5) Exit");
                    System.out.println("6) Program 24h");
                    System.out.print("Choose One Option: ");

                    int optionLights = readIntSafe();

                    if (optionLights == 1) {
                        livingroomLight = toggleOption(livingroomLight, livingroomLight);
                        wholeLights = (livingroomLight && restroomLight && kitchenLight);
                    } else if (optionLights == 2) {
                        restroomLight = toggleOption(restroomLight, restroomLight);
                        wholeLights = (livingroomLight && restroomLight && kitchenLight);
                    } else if (optionLights == 3) {
                        kitchenLight = toggleOption(kitchenLight, kitchenLight);
                        wholeLights = (livingroomLight && restroomLight && kitchenLight);
                    } else if (optionLights == 4) {
                        boolean newState = houseOnOff(livingroomLight, restroomLight, kitchenLight);
                        livingroomLight = newState;
                        restroomLight = newState;
                        kitchenLight = newState;
                        wholeLights = newState;
                    } else if (optionLights == 5) {
                        lightsMenu = false;
                    } else if (optionLights == 6) {
                        System.out.println();
                        System.out.println("Program 24h target in Lights:");
                        System.out.println("1) Living Room Light");
                        System.out.println("2) Rest Room Light");
                        System.out.println("3) Kitchen Light");
                        System.out.println("4) Whole Lights");
                        System.out.print("Choose One Option: ");

                        int targetLight = readIntSafe();
                        if (targetLight == 1) {
                            livingroomLight = program24h(livingroomLight, "Living Room Light");
                        } else if (targetLight == 2) {
                            restroomLight = program24h(restroomLight, "Rest Room Light");
                        } else if (targetLight == 3) {
                            kitchenLight = program24h(kitchenLight, "Kitchen Light");
                        } else if (targetLight == 4) {
                            boolean scheduled = program24h(wholeLights, "Whole Lights");
                            livingroomLight = scheduled;
                            restroomLight = scheduled;
                            kitchenLight = scheduled;
                            wholeLights = scheduled;
                        }
                    } else {
                        System.out.println("Please enter valid number option.");
                    }
                }
            }

            // ===== PORTES INTEL·LIGENTS =====
            else if (optionMenu == 2) {
                boolean doorMenu = true;
                while (doorMenu) {
                    System.out.println();
                    System.out.println("Intelligent Doors Menu:");
                    System.out.println("1) Toggle Sensors");
                    System.out.println("2) Registered Biometric Faces");
                    System.out.println("3) New Biometric Face");
                    System.out.println("4) About This Function");
                    System.out.println("5) Exit");
                    System.out.println("6) Program 24h (Biometric Sensor)");
                    System.out.print("Choose One Option: ");

                    int optionDoors = readIntSafe();

                    if (optionDoors == 1) {
                        biometricSensor = toggleSensor(biometricSensor, biometricSensor);
                    } else if (optionDoors == 2) {
                        showCurrentBiometrics();
                    } else if (optionDoors == 3) {
                        newBiometric();
                    } else if (optionDoors == 4) {
                        System.out.println("This is only to set up biometric settings and toggle biometric sensors (On/Off).");
                    } else if (optionDoors == 5) {
                        doorMenu = false;
                    } else if (optionDoors == 6) {
                        biometricSensor = program24h(biometricSensor, "Biometric Sensor");
                    } else {
                        System.out.println("Please enter valid number option.");
                    }
                }
            }

            // ===== TERMOSTAT =====
            else if (optionMenu == 3) {
                boolean thermostatMenu = true;
                while (thermostatMenu) {
                    System.out.println();
                    System.out.println("Thermostat Menu:");
                    System.out.println("1) Whole House Settings");
                    System.out.println("2) Room Settings");
                    System.out.println("3) Exit");
                    System.out.println("4) Program 24h");
                    System.out.print("Choose One Option: ");

                    int optionThermostat = readIntSafe();

                    if (optionThermostat == 1) {
                        boolean newState = houseOnOff(livingroomThermostat, restroomThermostat, kitchenThermostat);
                        livingroomThermostat = newState;
                        restroomThermostat = newState;
                        kitchenThermostat = newState;
                        wholeThermostat = newState;
                    } else if (optionThermostat == 2) {
                        int room = askRoom();
                        if (room == 1) {
                            livingroomThermostat = toggleOption(livingroomThermostat, livingroomThermostat);
                        } else if (room == 2) {
                            restroomThermostat = toggleOption(restroomThermostat, restroomThermostat);
                        } else if (room == 3) {
                            kitchenThermostat = toggleOption(kitchenThermostat, kitchenThermostat);
                        }
                    } else if (optionThermostat == 3) {
                        thermostatMenu = false;
                    } else if (optionThermostat == 4) {
                        System.out.println();
                        System.out.println("Program 24h target in Thermostat:");
                        System.out.println("1) Living Room Thermostat");
                        System.out.println("2) Rest Room Thermostat");
                        System.out.println("3) Kitchen Thermostat");
                        System.out.println("4) Whole Thermostat");
                        System.out.print("Choose One Option: ");
                        int targetThermo = readIntSafe();
                        if (targetThermo == 1) {
                            livingroomThermostat = program24h(livingroomThermostat, "Living Room Thermostat");
                        } else if (targetThermo == 2) {
                            restroomThermostat = program24h(restroomThermostat, "Rest Room Thermostat");
                        } else if (targetThermo == 3) {
                            kitchenThermostat = program24h(kitchenThermostat, "Kitchen Thermostat");
                        } else if (targetThermo == 4) {
                            boolean scheduled = program24h(wholeThermostat, "Whole Thermostat");
                            wholeThermostat = scheduled;
                            livingroomThermostat = scheduled;
                            restroomThermostat = scheduled;
                            kitchenThermostat = scheduled;
                        }
                    }
                }
            }

            // ===== MÚSICA =====
            else if (optionMenu == 4) {
                System.out.println("Music not programmable yet (soon).");
            }

            // ===== ESTAT CASA =====
            else if (optionMenu == 5) {
                printHouseStatus();
            }

            // ===== SORTIR =====
            else if (optionMenu == 6) {
                System.out.println("Bye!");
                menu = false;
            }
        }
        askOption.close();
    }

    // ===== Lectors segurs =====
    public static int readIntSafe() {
        int value = -1;
        try {
            value = askOption.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number.");
        } finally {
            askOption.nextLine();
        }
        return value;
    }

    public static double readDoubleSafe() {
        double value = 0.0;
        try {
            value = askOption.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number.");
        } finally {
            askOption.nextLine();
        }
        return value;
    }

    // ===== Altres mètodes =====
    public static int askRoom() {
        System.out.println();
        System.out.println("Please select a room:");
        System.out.println("1) Living Room");
        System.out.println("2) Rest Room");
        System.out.println("3) Kitchen");
        System.out.print("Choose One Option: ");
        return readIntSafe();
    }

    public static boolean toggleOption(boolean room, boolean toggle) {
        String state;
        if (!toggle) {
            state = "ON";
        } else {
            state = "OFF";
        }
        room = !toggle;
        System.out.println("Set to " + state);
        return room;
    }

    public static boolean houseOnOff(boolean o1, boolean o2, boolean o3) {
        boolean onOff = !(o1 && o2 && o3);
        if (onOff) {
            System.out.println("Whole set to: ON");
        } else {
            System.out.println("Whole set to: OFF");
        }
        return onOff;
    }

    public static boolean toggleSensor(boolean sensor, boolean toggle) {
        sensor = !toggle;
        if (sensor) {
            System.out.println("Sensor: ON");
        } else {
            System.out.println("Sensor: OFF");
        }
        return sensor;
    }

    public static void newBiometric() {
        System.out.print("Enter name for new Biometric: ");
        try {
            String newName = askOption.nextLine();
            if (!biometricFaces.isEmpty()) {
                biometricFaces = biometricFaces + ", " + newName;
            } else {
                biometricFaces = newName;
            }
            System.out.println("Added Biometric: " + newName);
        } catch (NoSuchElementException e) {
            System.out.println("Please enter a valid name.");
        } catch (Exception e) {
            System.out.println("Unexpected error.");
        }
    }

    public static void showCurrentBiometrics() {
        System.out.println("Registered Biometric Faces:");
        if (biometricFaces.isEmpty()) {
            System.out.println("--> None");
        } else {
            System.out.println("--> " + biometricFaces);
        }
    }

    public static void printHouseStatus() {
        System.out.println("//////// HOUSE STATE ////////");
        System.out.println("Lights - Whole House: " + wholeLights + " | Living Room: " + livingroomLight + " | Rest Room: " + restroomLight + " | Kitchen: " + kitchenLight);
        System.out.println("Thermostat - Whole House: " + wholeThermostat + " | Living Room: " + livingroomThermostat + " | Rest Room: " + restroomThermostat + " | Kitchen: " + kitchenThermostat);
        System.out.println("Biometric Sensor: " + biometricSensor);
        int count = biometricFaces.isEmpty() ? 0 : biometricFaces.split(",").length;
        System.out.println("Registered Faces: " + count);
    }

    // ===== Programació 24h (Universal) =====
    public static boolean program24h(boolean currentState, String targetName) {
        System.out.println();
        System.out.println("=== Program 24h for " + targetName + " ===");

        int targetHours = -1;
        int targetMinutes = -1;
        int targetSeconds = -1;

        while (targetHours < 0 || targetHours > 23) {
            System.out.print("Enter target hour (0-23): ");
            targetHours = readIntSafe();
        }
        while (targetMinutes < 0 || targetMinutes > 59) {
            System.out.print("Enter target minute (0-59): ");
            targetMinutes = readIntSafe();
        }
        while (targetSeconds < 0 || targetSeconds > 59) {
            System.out.print("Enter target second (0-59): ");
            targetSeconds = readIntSafe();
        }

        System.out.println("Clock count until " + targetHours + ":" + targetMinutes + ":" + targetSeconds + " ...");

        boolean triggerActivated = false;

        for (int h = 0; h < 24 && !triggerActivated; h++) {
            for (int m = 0; m < 60 && !triggerActivated; m++) {
                for (int s = 0; s < 60 && !triggerActivated; s++) {
                    if (h == targetHours && m == targetMinutes && s == targetSeconds) {
                        System.out.println("Time reached for " + targetName + "!");
                        if (currentState) {
                            currentState = false;
                            System.out.println(targetName + " set to OFF");
                        } else {
                            currentState = true;
                            System.out.println(targetName + " set to ON");
                        }
                        triggerActivated = true;
                    }
                }
            }
        }

        System.out.println("Clock time finished for " + targetName);
        return currentState;
    }
}
