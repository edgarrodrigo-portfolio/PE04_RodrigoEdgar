import java.util.InputMismatchException;
import java.util.Scanner;

public class PE04_RodrigoEdgar {

    //Declarar Llums de manera Global
        static boolean livingroomLight = false;
        static boolean restroomLight = false;
        static boolean kitchenLight = false;


    public static void main(String[] args) {
        boolean menu = true;
        while (menu) {
            System.out.println();
            System.out.println("Domotic House Menu:");
            System.out.println("1) Lights");
            System.out.println("2) Intelligent Doors");
            System.out.println();
            System.out.print("Select an option: ");
            Scanner askOption = new Scanner(System.in);
            int choosedOption = askOption.nextInt();
            try{
            if (choosedOption == 1){
                //Limpiar Scanner
                askOption.nextLine();
                //Menu Llums
                
                System.out.println("1) Living Room");
                System.out.println("2) Rest Room");
                System.out.println("3) Kitchen");
                System.out.println("4) All House");

                System.out.println();
                System.out.print("Choose One Option: ");
                choosedOption = askOption.nextInt();
                try{
                    if (choosedOption == 1) {
                        toggleLight("livingroom", livingroomLight);
                    }
                    else if(choosedOption == 2){
                        toggleLight("restroom", restroomLight);
                    }
                    else if(choosedOption == 3){
                        toggleLight("kitchen", kitchenLight);
                    }
                    else if(choosedOption == 4){
                        toggleLight("houseOnOff", true);
                    }
                    else if(choosedOption == 5){
                        continue;
                    }
                    else{
                        System.out.println("Please enter valid number option.");
                    }
                }catch(InputMismatchException e){
                    System.out.println("Please enter a number to choose an option.");
                }catch (Exception e){
                    System.out.println("Unexpected error, please restart.");
                }
            }
            else if (choosedOption == 2){
                
            }
            }catch(InputMismatchException e){
                System.out.println("Please enter a number to choose an option.");
            }catch (Exception e){
                System.out.println("Unexpected error, please restart.");
            }
        }
        
    }
    public static void toggleLight(String room, boolean toggle){
        String state;
        if (!toggle){
            state = "ON";
        }
        else{
            state = "OFF";
        }
        switch (room) {
            case "livingroom":
                livingroomLight = !toggle;
                
                System.out.println();
                System.out.println("The lights of the living room are "+state);
                break;
            case "restroom":
                restroomLight = !toggle;
                System.out.println();
                System.out.println("The lights of the rest room are "+state);
                break;
            case "kitchen":
                kitchenLight = !toggle;
                System.out.println();
                System.out.println("The lights of the kitchen are "+state);
                break;
            
            case "houseOnOff":
                boolean onOff = !(livingroomLight && restroomLight && kitchenLight);
                livingroomLight = onOff;
                restroomLight = onOff;
                kitchenLight = onOff;
                if (onOff){
                    state = "ON";
                }
                else{
                    state = "OFF";
                }
                System.out.println();
                System.out.println("The lights of the house are "+ state);
                break;
            
            default:
                break;
        }
    }
}
