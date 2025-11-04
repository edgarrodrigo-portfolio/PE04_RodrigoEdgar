import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PE04_RodrigoEdgar {

    //Declarar Llums de manera Global
    static boolean wholeLights = false;
    static boolean livingroomLight = false;
    static boolean restroomLight = false;
    static boolean kitchenLight = false;

    //Declarar Sensors
    static boolean biometricSensor = true;

    //Scanner Global
    static Scanner askOption = new Scanner(System.in);

    //Array per emmagatzemar cares biomètriques
    static ArrayList<String> biometricFaces = new ArrayList<>();

    //Declarar Termòstat (estat per estança i global)
    static boolean wholeThermostat = false;
    static boolean livingroomThermostat = false;
    static boolean restroomThermostat = false;
    static boolean kitchenThermostat = false;

    //Temperatures del Termòstat (per mostrar l’estat)
    static double wholeThermostatTemp = 0.0;
    static double livingroomThermostatTemp = 0.0;
    static double restroomThermostatTemp = 0.0;
    static double kitchenThermostatTemp = 0.0;

    //Potència del Termòstat
    static String powerTemp = "";

    //Sistema de Música (estat i cançons)
    static boolean wholeMusic = false;
    static boolean livingroomMusic = false;
    static boolean restroomMusic = false;
    static boolean kitchenMusic = false;

    static String songWhole = "";
    static String songLiving = "";
    static String songRestroom = "";
    static String songKitchen = "";

    public static void main(String[] args) {

        boolean menu = true;
        while (menu) {

            //===== ESTAT GENERAL ÚNIC =====
            printHouseStatus();

            System.out.println();
            System.out.println("Domotic House Menu:");
            System.out.println("1) Lights");
            System.out.println("2) Intelligent Doors");
            System.out.println("3) Thermostat");
            System.out.println("4) Music System");
            System.out.println("5) Exit");
            System.out.println();
            System.out.print("Select an option: ");

            int optionMenu = readIntSafe();

            if (optionMenu == 1) {
                //Menu Llums
                boolean lightsMenu = true;
                while (lightsMenu) {
                    System.out.println();
                    System.out.println("Lights Menu:");
                    System.out.println("1) Living Room");
                    System.out.println("2) Rest Room");
                    System.out.println("3) Kitchen");
                    System.out.println("4) All House (toggle all)");
                    System.out.println("5) Exit");
                    System.out.println();
                    System.out.print("Choose One Option: ");

                    int optionLights = readIntSafe();
                    try {
                        if (optionLights == 1) {
                            livingroomLight = toggleOption(livingroomLight, livingroomLight);
                        } else if (optionLights == 2) {
                            restroomLight = toggleOption(restroomLight, restroomLight);
                        } else if (optionLights == 3) {
                            kitchenLight = toggleOption(kitchenLight, kitchenLight);
                        } else if (optionLights == 4) {
                            //Canvia totes les llums segons estat actual
                            boolean newState = houseOnOff(livingroomLight, restroomLight, kitchenLight);
                            livingroomLight = newState;
                            restroomLight = newState;
                            kitchenLight = newState;
                            wholeLights = newState;
                        } else if (optionLights == 5) {
                            lightsMenu = false; //Exit
                        } else {
                            System.out.println("Please enter valid number option.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a number to choose an option.");
                        askOption.nextLine(); //Limpiar Scanner
                    } catch (Exception e) {
                        System.out.println("Unexpected error, please restart.");
                    }
                }

            } else if (optionMenu == 2) {
                //Menu Portes Intel·ligents
                boolean doorMenu = true;
                while (doorMenu) {
                    System.out.println();
                    System.out.println("Intelligent Doors Menu:");
                    System.out.println("1) Toggle Sensors");
                    System.out.println("2) Registered Biometric Faces");
                    System.out.println("3) New Biometric Face");
                    System.out.println("4) About This Function");
                    System.out.println("5) Exit");
                    System.out.println();
                    System.out.print("Choose One Option: ");

                    int optionDoors = readIntSafe();
                    try {
                        if (optionDoors == 1) {
                            biometricSensor = toggleSensor(biometricSensor, biometricSensor);
                        } else if (optionDoors == 2) {
                            showCurrentBiometrics();
                        } else if (optionDoors == 3) {
                            newBiometric(); //NO MODIFICAR
                        } else if (optionDoors == 4) {
                            System.out.println("This is only to set up biometric settings and toggle biometric sensors (On/Off).");
                        } else if (optionDoors == 5) {
                            doorMenu = false; //Exit
                        } else {
                            System.out.println("Please enter valid number option.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a number to choose an option.");
                        askOption.nextLine(); //Limpiar Scanner
                    } catch (Exception e) {
                        System.out.println("Unexpected error, please restart.");
                    }
                }

            } else if (optionMenu == 3) {
                //Menu Termòstat
                boolean thermostatMenu = true;
                while (thermostatMenu) {
                    System.out.println();
                    System.out.println("Thermostat Menu:");
                    System.out.println("1) Whole House Settings");
                    System.out.println("2) Choose Room Settings");
                    System.out.println("3) Exit");
                    System.out.println();
                    System.out.print("Choose One Option: ");

                    int optionThermostat = readIntSafe();

                    if (optionThermostat == 1) {
                        //Configuració Tota la Casa
                        System.out.println();
                        System.out.println("Whole House Thermostat:");
                        System.out.println("1) Turn On/Off Thermostat");
                        System.out.println("2) Enter the desired temperature");
                        System.out.println("3) Set Power");
                        System.out.println("4) Exit");
                        System.out.println();
                        System.out.print("Choose One Option: ");

                        int optionWholeThermostat = readIntSafe();

                        if (optionWholeThermostat == 1) {
                            boolean newState = houseOnOff(livingroomThermostat, restroomThermostat, kitchenThermostat);
                            livingroomThermostat = newState;
                            restroomThermostat = newState;
                            kitchenThermostat = newState;
                            wholeThermostat = newState;
                            if (newState) {
                                System.out.println("Thermostat (Whole House) set to: ON");
                            } else {
                                System.out.println("Thermostat (Whole House) set to: OFF");
                            }
                        } else if (optionWholeThermostat == 2) {
                            System.out.print("Please Enter desired temperature (Cº): ");
                            double temperatureWhole = readDoubleSafe();
                            wholeThermostatTemp = temperatureWhole; //guardar per estat
                            if (wholeThermostat) {
                                livingroomThermostatTemp = temperatureWhole;
                                restroomThermostatTemp = temperatureWhole;
                                kitchenThermostatTemp = temperatureWhole;
                            }
                            System.out.println("The Thermostat has been set to " + temperatureWhole + " Cº (Whole House)");
                        } else if (optionWholeThermostat == 3) {
                            System.out.print("To set power please write (High, Medium, Low): ");
                            askOption.nextLine(); //Limpiar Scanner
                            powerTemp = askOption.nextLine();
                            powerTemp = askPower(powerTemp);
                        } else if (optionWholeThermostat == 4) {
                            //Exit submenú
                        }
                    } else if (optionThermostat == 2) {
                        //Configuració per Habitació
                        System.out.println();
                        System.out.println("Room Thermostat:");
                        System.out.println("1) Turn On/Off Thermostat");
                        System.out.println("2) Enter desired temperature");
                        System.out.println("3) Set Power");
                        System.out.println("4) Exit");
                        System.out.println();
                        System.out.print("Choose One Option: ");
                        int optionRoomThermostat = readIntSafe();

                        if (optionRoomThermostat == 1) {
                            int roomSelected = askRoom();
                            if (roomSelected == 1) {
                                livingroomThermostat = toggleOption(livingroomThermostat, livingroomThermostat);
                            } else if (roomSelected == 2) {
                                restroomThermostat = toggleOption(restroomThermostat, restroomThermostat);
                            } else if (roomSelected == 3) {
                                kitchenThermostat = toggleOption(kitchenThermostat, kitchenThermostat);
                            }
                        } else if (optionRoomThermostat == 2) {
                            int roomSelected = askRoom();
                            System.out.print("Please Enter desired temperature (Cº): ");
                            double tempSelected = readDoubleSafe();
                            if (roomSelected == 1) {
                                livingroomThermostatTemp = tempSelected;
                            } else if (roomSelected == 2) {
                                restroomThermostatTemp = tempSelected;
                            } else if (roomSelected == 3) {
                                kitchenThermostatTemp = tempSelected;
                            }
                            System.out.println("The Thermostat has been set to " + tempSelected + " Cº");
                        } else if (optionRoomThermostat == 3) {
                            System.out.print("To set power please write (High, Medium, Low): ");
                            askOption.nextLine(); //Limpiar Scanner
                            String powerSelected = askOption.nextLine();
                            powerSelected = askPower(powerSelected);
                            System.out.println("Power set to " + powerSelected);
                        } else if (optionRoomThermostat == 4) {
                            //Exit submenú
                        }
                    } else if (optionThermostat == 3) {
                        thermostatMenu = false; //Exit
                    }
                }

            } else if (optionMenu == 4) {
                //Sistema de Música (Whole o per habitació, exclusiu)
                boolean musicMenu = true;
                while (musicMenu) {
                    System.out.println();
                    System.out.println("Music System:");
                    System.out.println("1) Whole House Music");
                    System.out.println("2) Room Music");
                    System.out.println("3) Exit");
                    System.out.println();
                    System.out.print("Choose One Option: ");
                    int optionMusic = readIntSafe();

                    if (optionMusic == 1) {
                        //Whole House Music
                        System.out.println();
                        System.out.println("Whole House Music:");
                        System.out.println("1) Turn ON");
                        System.out.println("2) Turn OFF");
                        System.out.println("3) Set Song");
                        System.out.println("4) Exit");
                        System.out.println();
                        System.out.print("Choose One Option: ");
                        int optionWholeMusic = readIntSafe();

                        if (optionWholeMusic == 1) {
                            //Exclusivitat: activem Whole i desactivem habitacions
                            wholeMusic = true;
                            livingroomMusic = false;
                            restroomMusic = false;
                            kitchenMusic = false;
                            System.out.println("Whole Music: ON");
                        } else if (optionWholeMusic == 2) {
                            wholeMusic = false;
                            System.out.println("Whole Music: OFF");
                        } else if (optionWholeMusic == 3) {
                            System.out.print("Write the song name for Whole House: ");
                            askOption.nextLine(); //Limpiar Scanner
                            songWhole = askOption.nextLine();
                            System.out.println("Whole song set to: " + songWhole);
                        } else if (optionWholeMusic == 4) {
                            //Exit submenú
                        }

                    } else if (optionMusic == 2) {
                        //Room Music
                        System.out.println();
                        System.out.println("Room Music:");
                        System.out.println("1) Living Room");
                        System.out.println("2) Rest Room");
                        System.out.println("3) Kitchen");
                        System.out.println("4) Exit");
                        System.out.println();
                        System.out.print("Choose One Option: ");
                        int optionRoomMusic = readIntSafe();

                        if (optionRoomMusic == 1 || optionRoomMusic == 2 || optionRoomMusic == 3) {
                            //Exclusivitat: si activem habitació, desactivem Whole
                            wholeMusic = false;

                            System.out.println();
                            System.out.println("Room Controls:");
                            System.out.println("1) Turn ON");
                            System.out.println("2) Turn OFF");
                            System.out.println("3) Set Song");
                            System.out.println("4) Exit");
                            System.out.println();
                            System.out.print("Choose One Option: ");
                            int optionRoomMusicAction = readIntSafe();

                            if (optionRoomMusic == 1) {
                                //Living Room
                                if (optionRoomMusicAction == 1) {
                                    livingroomMusic = true;
                                    System.out.println("Living Room Music: ON");
                                } else if (optionRoomMusicAction == 2) {
                                    livingroomMusic = false;
                                    System.out.println("Living Room Music: OFF");
                                } else if (optionRoomMusicAction == 3) {
                                    System.out.print("Write the song name for Living Room: ");
                                    askOption.nextLine(); //Limpiar Scanner
                                    songLiving = askOption.nextLine();
                                    System.out.println("Living Room song set to: " + songLiving);
                                } else if (optionRoomMusicAction == 4) {
                                    //Exit submenú
                                }
                            } else if (optionRoomMusic == 2) {
                                //Rest Room
                                if (optionRoomMusicAction == 1) {
                                    restroomMusic = true;
                                    System.out.println("Rest Room Music: ON");
                                } else if (optionRoomMusicAction == 2) {
                                    restroomMusic = false;
                                    System.out.println("Rest Room Music: OFF");
                                } else if (optionRoomMusicAction == 3) {
                                    System.out.print("Write the song name for Rest Room: ");
                                    askOption.nextLine(); //Limpiar Scanner
                                    songRestroom = askOption.nextLine();
                                    System.out.println("Rest Room song set to: " + songRestroom);
                                } else if (optionRoomMusicAction == 4) {
                                    //Exit submenú
                                }
                            } else if (optionRoomMusic == 3) {
                                //Kitchen
                                if (optionRoomMusicAction == 1) {
                                    kitchenMusic = true;
                                    System.out.println("Kitchen Music: ON");
                                } else if (optionRoomMusicAction == 2) {
                                    kitchenMusic = false;
                                    System.out.println("Kitchen Music: OFF");
                                } else if (optionRoomMusicAction == 3) {
                                    System.out.print("Write the song name for Kitchen: ");
                                    askOption.nextLine(); //Limpiar Scanner
                                    songKitchen = askOption.nextLine();
                                    System.out.println("Kitchen song set to: " + songKitchen);
                                } else if (optionRoomMusicAction == 4) {
                                    //Exit submenú
                                }
                            }
                        } else if (optionRoomMusic == 4) {
                            //Exit submenú
                        }

                    } else if (optionMusic == 3) {
                        musicMenu = false; //Exit
                    }
                }

            } else if (optionMenu == 5) {
                System.out.println("Bye!");
                menu = false;
            }
        }

        askOption.close();
    }

    //===== FUNCIONS AUXILIARS =====

    //Llegeix un enter i evita errors d'entrada
    public static int readIntSafe() {
        int value = -1;
        try {
            value = askOption.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("Unexpected error.");
        } finally {
            askOption.nextLine(); //Limpiar Scanner
        }
        return value;
    }

    //Llegeix un double i evita errors d'entrada
    public static double readDoubleSafe() {
        double value = 0.0;
        try {
            value = askOption.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("Unexpected error.");
        } finally {
            askOption.nextLine(); //Limpiar Scanner
        }
        return value;
    }

    //Demana habitació per a opcions del termòstat
    public static int askRoom() {
        System.out.println();
        System.out.println("Please select a room: ");
        System.out.println("1) Living Room");
        System.out.println("2) Rest Room");
        System.out.println("3) Kitchen");
        System.out.print("Choose One Option: ");
        int roomOption = readIntSafe();
        return roomOption;
    }

    //Configura potència del termòstat
    public static String askPower(String powerValue) {
        if (powerValue.equalsIgnoreCase("High") || powerValue.equalsIgnoreCase("h")) {
            System.out.println("The Thermostat Power has been set to " + powerValue);
        } else if (powerValue.equalsIgnoreCase("Medium") || powerValue.equalsIgnoreCase("m")) {
            System.out.println("The Thermostat Power has been set to " + powerValue);
        } else if (powerValue.equalsIgnoreCase("Low") || powerValue.equalsIgnoreCase("l")) {
            System.out.println("The Thermostat Power has been set to " + powerValue);
        } else {
            System.out.println("Unexpected Error, please restart.");
        }
        return powerValue;
    }

    //Alterna estat (ON/OFF) d’una opció booleana
    public static boolean toggleOption(boolean room, boolean toggle) {
        String state;
        if (!toggle) {
            state = "ON";
        } else {
            state = "OFF";
        }
        room = !toggle;
        System.out.println();
        System.out.println("Set to " + state);
        return room;
    }

    //Calcula ON/OFF global segons estat actual
    public static boolean houseOnOff(boolean option1, boolean option2, boolean option3) {
        boolean onOff = !(option1 && option2 && option3);
        System.out.println();
        if (onOff) {
            System.out.println("Whole state will be set to: ON");
        } else {
            System.out.println("Whole state will be set to: OFF");
        }
        return onOff;
    }

    //Alterna el sensor biomètric
    public static boolean toggleSensor(boolean sensor, boolean toggleSensor) {
        sensor = !toggleSensor;
        String state;
        if (sensor) {
            state = "ON";
        } else {
            state = "OFF";
        }
        System.out.println("Sensor is now: " + state);
        return sensor;
    }

    //Metode nova dada Biometrica
    public static void newBiometric (){
        System.out.println("Your going to add a new Biometric, for Face Recognition.");
        System.out.print("Please enter a name for the new Biometric data: ");
        try{
            askOption.nextLine();
            String newName = askOption.nextLine();
            biometricFaces.add(newName);
            System.out.println("Correct! Your recently added biometric ID is: " + newName);
        }
        catch (NoSuchElementException e){
            System.out.println("Please Enter a valid name.");
        }
        catch (Exception e){
            System.out.println("An error ocurred, please try it later.");
        }
    }

    //Mostra cares biomètriques registrades
    public static void showCurrentBiometrics (){
        System.out.println("Registered Biometric Faces: ");
        for (String biometricId : biometricFaces){
            System.out.println("--> " + biometricId);
        }
    }

    //Pantalla d’estat general (una sola, al principi)
    public static void printHouseStatus() {
        System.out.println("//////// HOUSE STATE ////////");

        System.out.println("/////// LIGHTS ///////");
        System.out.print("Living Room - ");
        if (livingroomLight) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
        System.out.print("Rest Room  - ");
        if (restroomLight) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
        System.out.print("Kitchen    - ");
        if (kitchenLight) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
        System.out.print("Whole      - ");
        if (wholeLights) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }

        System.out.println();
        System.out.println("/////// THERMOSTAT ///////");
        System.out.print("Living Room - ");
        if (livingroomThermostat) {
            System.out.println("True set to " + livingroomThermostatTemp + "º");
        } else {
            System.out.println("False");
        }
        System.out.print("Rest Room  - ");
        if (restroomThermostat) {
            System.out.println("True set to " + restroomThermostatTemp + "º");
        } else {
            System.out.println("False");
        }
        System.out.print("Kitchen    - ");
        if (kitchenThermostat) {
            System.out.println("True set to " + kitchenThermostatTemp + "º");
        } else {
            System.out.println("False");
        }
        System.out.print("Whole      - ");
        if (wholeThermostat) {
            System.out.println("True set to " + wholeThermostatTemp + "º");
        } else {
            System.out.println("False");
        }
        if (powerTemp != null && powerTemp.length() > 0) {
            System.out.println("Power: " + powerTemp);
        }

        System.out.println();
        System.out.println("///// INTELLIGENT DOORS /////");
        System.out.print("Biometric Sensor - ");
        if (biometricSensor) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
        System.out.println("Registered Faces - " + biometricFaces.size());

        System.out.println();
        System.out.println("/////// MUSIC ///////");
        System.out.print("Whole Music - ");
        if (wholeMusic) {
            System.out.println("True");
            if (songWhole != null && songWhole.length() > 0) {
                System.out.println("Song: " + songWhole);
            }
        } else {
            System.out.println("False");
        }

        System.out.print("Living Room - ");
        if (livingroomMusic) {
            System.out.println("True");
            if (songLiving != null && songLiving.length() > 0) {
                System.out.println("Song: " + songLiving);
            }
        } else {
            System.out.println("False");
        }

        System.out.print("Rest Room  - ");
        if (restroomMusic) {
            System.out.println("True");
            if (songRestroom != null && songRestroom.length() > 0) {
                System.out.println("Song: " + songRestroom);
            }
        } else {
            System.out.println("False");
        }

        System.out.print("Kitchen    - ");
        if (kitchenMusic) {
            System.out.println("True");
            if (songKitchen != null && songKitchen.length() > 0) {
                System.out.println("Song: " + songKitchen);
            }
        } else {
            System.out.println("False");
        }

        System.out.println();
    }
}
