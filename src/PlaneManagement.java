import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PlaneManagement {
    public static void main(String[] args) {



        System.out.println("Welcome to the Plane Management application\n");

        while (true){
            showMenu();
            if(exit==1){
                break;
            }

        }
//        for (Ticket i:tickets){
//            System.out.println(i.getRow()+i.getSeat());
//            System.out.println(i.getPrice());
//        }


    }



    static Scanner scanner = new Scanner(System.in);

    static int[] seatRowA = new int[14];
    static int[] seatRowB = new int[12];
    static int[] seatRowC = new int[12];
    static int[] seatRowD = new int[14];

    static int exit = 0;

    static Person person;
    static Ticket ticket;

    static Ticket[] tickets =new Ticket[52];


    static int ticket_count = 0;


    public static void showMenu() {
        System.out.println
                (""" 
                **********************************
                *         MENU OPTIONS      *
                **********************************
                                
                    1) Buy a seat
                    2) Cancel a seat
                    3) Find first available seat
                    4) Show seating plan
                    5) Print tickets information and total sales
                    6) Search Ticket
                    0) Quit
                """);

        int input = 0 ;

        try {
            input = scanner.nextInt();
            if (input > 6 || input < 0) {
                System.err.println("Please enter valid option");
                showMenu();
            }
        }catch (Exception e){
            System.err.println("Please enter valid option ");
            showMenu();
        }



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
            case 0:
                exit = 1;

        }


    }

    public static void buy_seat() {

        System.out.println("Enter row letter");

        String row = scanner.next().toUpperCase();

        if (row.equals("A")) {
            buy_process("A",seatRowA);


        } else if (row.equals("B")) {
           buy_process("B",seatRowB);

        } else if (row.equals("C")) {
           buy_process("C",seatRowC);

        } else if (row.equals("D")) {
            buy_process("D",seatRowC);

        } else {
            System.out.println("Enter correct row");
            buy_seat();
        }
    }

    public static void cancel_seat(){

        System.out.println("Enter row letter");

        String row= scanner.next().toUpperCase();

        if(row.equals("A")){
            cancel_process();

        } else if (row.equals("B")) {
            cancel_process();

        } else if (row.equals("C")) {
            cancel_process();

        }else if (row.equals("D")){
            cancel_process();

        }else {
            System.out.println("Enter correct row");
            cancel_seat();
        }


    }

    public static void find_first_available(){
        int seat_num = 0;
        int flag = 1;


        for(int x : seatRowA){
            seat_num=seat_num+1;
            if (x==0){
                flag =0;
                System.out.println("Row A seat number "+seat_num+" is available");
                break;
            }
        }

        seat_num=0;

        if (flag==1) {
            for (int x : seatRowB) {
                seat_num = seat_num + 1;
                if (x == 0) {
                    flag = 2;
                    System.out.println("Row B seat number " + seat_num + " is available");
                    break;
                }
            }
        }

        if(flag==1) {
            seat_num = 0;
            for (int x : seatRowC) {
                seat_num = seat_num + 1;
                if (x == 0) {
                    flag = 0;
                    System.out.println("Row C seat number " + seat_num + " is available");
                    break;
                }
            }
        }

        if(flag==1) {
            seat_num = 0;
            for (int x : seatRowD) {
                seat_num = seat_num + 1;
                if (x == 0) {
                    System.out.println("Row D seat number " + seat_num + " is available");
                    break;
                }
            }
        }

        if(flag == 1) {
            System.out.println("There no available seats");
        }





    }

    public static void show_seating_plan(){
        for (int i : seatRowA){
            if(i==0){
                System.out.print("O");
            }
            else{
                System.out.print("X");
            }
        }
        System.out.println();

        for (int i : seatRowB){
            if(i==0){
                System.out.print("O");
            }
            else{
                System.out.print("X");
            }
        }
        System.out.println();

        for (int i : seatRowC){
            if(i==0){
                System.out.print("O");
            }
            else{
                System.out.print("X");
            }
        }

        System.out.println();
        for (int i : seatRowD){
            if(i==0){
                System.out.print("O");
            }
            else{
                System.out.print("X");
            }
        }
        System.out.println("\n\n");
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

    }

    public static void search_ticket() {
        int index = 0;

        System.out.println("Enter row letter");
        String row = scanner.next().toUpperCase();

        if(row.equals("A")||row.equals("D")){
            System.out.println("Enter seat number");
            int seat = scanner.nextInt();
            if(seat<=14 && seat>=1) {

                while (index < tickets.length && !tickets[index].getRow().equals(row) && tickets[index].getSeat() != seat) {
                    index++;
                }
                if (index == tickets.length) {
                    System.out.println("This seat is available");
                } else {
                    System.out.println(tickets[index].getPerson().getName());
                }
            }else
            {
                System.out.println("Enter seat number between 1-14");
            }


        } else if (row.equals("C")||row.equals("B")) {

            System.out.println("Enter seat number");
            int seat = scanner.nextInt();
            if(seat<=12 && seat>=1) {

                while (index < tickets.length && !tickets[index].getRow().equals(row) && tickets[index].getSeat() != seat) {
                    index++;
                }
                if (index == tickets.length) {
                    System.out.println("This seat is available");
                } else {
                    System.out.println(tickets[index].getPerson().getName());
                }
            }else
            {
                System.out.println("Enter seat number between 1-12");
            }

        }else {
            System.out.println("Enter correct row letter");
        }


    }

    public static Person get_person_details() {
        System.out.println("Enter your name");
        String name = scanner.next();

        System.out.println("Enter your surname");
        String surname = scanner.next();

        System.out.println("Enter your Email");
        String email = scanner.next();

        person = new Person(name,surname,email);

        return person;

    }


    public static void buy_process(String row,int[] seat_row) {

        System.out.println("Enter seat number");
        int ticket_price;
        if (scanner.hasNextInt()) {
        int seatNum = scanner.nextInt();

        try {
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

                tickets[ticket_count++] = ticket;

                save(row+seatNum,ticket);

                System.out.println("seat booked!!");
            } else {
                System.out.println("seat has been sold");
            }
        }catch (Exception e) {
            if (row.equals("A")||row.equals("D")){
                System.out.println("Enter seat number between 1-14");}
            else {
                System.out.println("Enter seat number below 1-12");
            }
        }

        } else {
        System.out.println("enter valid input");
        buy_seat();
    }
}

    public static void cancel_process(){
        if(scanner.hasNextInt()){
            int seatNum = scanner.nextInt();
            if (seatRowA[seatNum-1]==1){
                seatRowA[seatNum-1]=0;
                System.out.println("seat cancelled");

            }else{
                System.out.println("Already free");
            }
        }else{
            System.out.println("enter valid input");
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




}