import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PlaneManagement {
    public static void main(String[] args) {
        System.out.println("\n\nWelcome to the Plane Management application\n");
        System.out.print("""
                **********************************
                *         MENU OPTIONS      *
                **********************************
                """);
        do {
            show_menu();
        } while (exit != 1);
    }

    static Scanner scanner = new Scanner(System.in);
    static int[] seatRowA = new int[14];
    static int[] seatRowB = new int[12];
    static int[] seatRowC = new int[12];
    static int[] seatRowD = new int[14];
    static int[][] allRows = {seatRowA,seatRowB,seatRowC,seatRowD};
    static int exit = 0;
    static Person person;
    static Ticket[] tickets =new Ticket[52];
    static int ticketCount = 0;

    public static void show_menu() {
        System.out.println
                ("""
                
                
                    1) Buy a seat
                    2) Cancel a seat
                    3) Find first available seat
                    4) Show seating plan
                    5) Print tickets information and total sales
                    6) Search Ticket
                    0) Quit
                --------------------------------------------------
                """);

        int input = get_input();
        switch (input) {
            case 1:
                buy_seat();
                break;
            case 2:
                cancel_seat();
                break;
            case 3:
                find_first_available();
                break;
            case 4:
                show_seating_plan();
                break;
            case 5:
                print_tickets_info();
                break;
            case 6:
                search_ticket();
                break;
            case 0:
                exit = 1;

        }
    }

    public static void buy_seat() {

        System.out.print("Enter row letter(A/B/C/D): ");
        String row = scanner.next().toUpperCase();

        switch (row) {
            case "A" -> buy_process("A", seatRowA);
            case "B" -> buy_process("B", seatRowB);
            case "C" -> buy_process("C", seatRowC);
            case "D" -> buy_process("D", seatRowD);
            default -> {
                System.out.println("Enter correct row");
                buy_seat();
            }
        }
    }

    public static void cancel_seat(){

        System.out.println("Enter row letter");
        String row= scanner.next().toUpperCase();

        switch (row) {
            case "A" -> cancel_process(seatRowA,row);
            case "B" -> cancel_process(seatRowB,row);
            case "C" -> cancel_process(seatRowC,row);
            case "D" -> cancel_process(seatRowD,row);
            default -> {
                System.out.println("Enter correct row");
                cancel_seat();
            }
        }
    }

    public static void find_first_available(){
        int rowName = 0;
        for(int[] row : allRows) {
            int seatNum = 1;
            String[] rowNames = {"A","B","C","D"};
            for(int i : row){
                if(i==0){
                    System.out.println("Row:"+ rowNames[rowName] + " seat number:"+seatNum+" is free");
                    show_menu();
                    return;
                }
                seatNum++;
                if(row==seatRowD && seatNum==14){
                    System.out.println("No seats are free");
                    show_menu();
                    return;
                }
            }
            rowName++;
        }
    }

    public static void show_seating_plan(){
        int sIndex=0;
        for(int[] r : allRows){

            for(int i : r){
                if(i==0){
                    System.out.print("O");
                }
                else{
                    System.out.print("X");
                }
                if(sIndex==25){
                    System.out.println();
                }
                if (sIndex == 13 || sIndex== 25|| sIndex == 37||sIndex == 52){
                    System.out.println();
                }
                sIndex++;
            }
        }
        show_menu();
    }

    public static void  print_tickets_info(){
        double total_amount=0;

        try {
            for (Ticket t : tickets) {
                System.out.print(t.getRow() + t.getSeat()+", ");
                total_amount = total_amount+t.getPrice();
            }
        }catch (Exception e){
            System.out.println();
        }
        System.out.println("Total amount is: "+total_amount);
        show_menu();
    }

    public static void search_ticket() {
        scanner.nextLine();
        int index = 0;
        System.out.println("Enter row(A/B/C/D):");
        String row = scanner.next().toUpperCase();

        if (row.equals("A") || row.equals("D")) {
            int seat ;
            System.out.println("Enter seat number(1-14):");
            try {
                 seat = scanner.nextInt();
            }catch (Exception e){
                System.out.println("Enter valid number");
                search_ticket();
                return;
            }

            try {
                if (seat <= 14 && seat >= 1) {
                    search_process(index, row, seat);
                } else {
                    System.out.println("Enter seat number between 1-14");
                    search_ticket();
                }
            }
            catch (Exception e) {
                System.out.println("This seat is available for booking");
            }

        } else if (row.equals("C") || row.equals("B")) {
            int seat;
            System.out.println("Enter seat number(1-12):");
            try {
                seat = scanner.nextInt();
            }catch (Exception e){
                System.out.println("Enter valid number");
                search_ticket();
                return;
            }

            try {
                if (seat <= 12 && seat >= 1) {
                    search_process(index, row, seat);
                } else {
                    System.out.print("Enter seat number between 1-12: ");
                    search_ticket();
                }
            }catch (Exception e){
                System.out.println("This seat is available for booking");
            }

        } else {
            System.out.println("Enter correct row letter(A/B/C/D)");
            search_ticket();
        }
        show_menu();
    }

    public static void search_process(int index, String row, int seat) {
        while (index < 51 ) {
            if(tickets[index].getRow()==null || !tickets[index].getRow().equals(row) || tickets[index].getSeat() != seat){
                index++;
            }else{
                break;
            }
        }

        if (index == 51) {
            System.out.println("This seat is available for booking");
        } else {
            tickets[index].ticket_detail();
        }
    }

    public static Person get_person_details() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();

        System.out.println("Enter your surname: ");
        String surname = scanner.nextLine();

        System.out.println("Enter your Email: ");
        String email = scanner.nextLine();

        person = new Person(name,surname,email);

        return person;

    }

    public static void buy_process(String row,int[] seat_row) {

        System.out.print("Enter seat number: ");
        int ticket_price;
        scanner.nextLine();
        if (scanner.hasNextInt()) {
        try {
            int seatNum = scanner.nextInt();
            if (seat_row[seatNum - 1] == 0 && seatNum <= 14) {
                seat_row[seatNum - 1] = 1;
                person = get_person_details();

                if(seatNum<=5){
                    ticket_price = 200;
                } else if (seatNum<10) {
                    ticket_price= 150;
                }else {
                    ticket_price = 180;
                }

                Ticket ticket = new Ticket(row, seatNum, ticket_price, person);
                tickets[ticketCount++] = ticket;
                save(row+seatNum,ticket);
                System.out.println("seat booked!!");
                show_menu();
            } else {
                System.out.println("seat has been sold");
                show_menu();
            }

        }catch (Exception e){
            if (row.equals("A")||row.equals("D")){
                System.out.print("Enter seat number between 1-14: ");
            }
            else {
                System.out.print("Enter seat number below 1-12: ");
            }
        }
        } else {
            scanner.nextLine();
            System.out.println("************ INVALID INPUT! TRY AGAIN ************\n");
            buy_seat();
        }
}

    public static void cancel_process(int[] seat_row,String row){
        scanner.nextLine();
        System.out.println("Enter seat number");

        if(scanner.hasNextInt()){
            int seatNum = scanner.nextInt();

            if (seat_row[seatNum-1]==1){
                seat_row[seatNum-1]=0;
                System.out.println("seat cancelled");

                try {
                    for(int i=0; i<tickets.length; i++){
                        if (tickets[i].getRow().equals(row) && tickets[i].getSeat()==seatNum){
                           tickets[i] = null;
                        }
                    }
                }catch (Exception e){
                    System.out.println();
                }

            }else{
                System.out.println("Already free");
                show_menu();
            }
        }else{
            System.out.println("enter valid input");
            scanner.nextLine();
            cancel_seat();
        }
    }

    public static void save(String x,Ticket t){
    try {
        File myObj = new File(x+".txt");
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
        } else {
            System.out.println("File already exists.");
        }
    } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }

    try {
        FileWriter myWriter = new FileWriter(x+".txt");
        myWriter.write("Row:"+t.getRow()+" Seat:"+t.getSeat()+" Price:"+t.getPrice()+" Person name:"+t.getPerson().getName()+" Surname:"+t.getPerson().getSurname()+" Email:"+t.getPerson().getEmail());
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }

    }

    public static int get_input(){
        int input =0;
        try {
            input = scanner.nextInt();

            if (input > 6 || input < 0) {
                System.err.println("Please enter option between 1-6 or 0");
                show_menu();
            }
        }catch (Exception e)
        {   scanner.nextLine();
            System.err.println("invalid input please enter input between 1-6 or 0");
            show_menu();
        }
        return input;
    }
}