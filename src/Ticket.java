public class Ticket {
    String row;



    int seat;
    double price;
    Person person;

    public Ticket(String row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }
    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void ticket_detail(){
        String row = " Row  : "+getRow();
        String seat=" Seat : "+ getSeat();
        String price= " price: £"+getPrice();
        String name = "Name: "+person.name;
        String surname ="Surname: "+person.getSurname();
        String email = "Email: "+person.getEmail();
        int longestString = Math.max(row.length(), Math.max(seat.length(), Math.max(price.length(),Math.max(name.length(),Math.max(surname.length(),email.length())))));
        int width = longestString + 4;

        printLine(width);
        printText(row, width);
        printText(seat, width);
        printText(price, width);
        printText(name,width);
        printText(email,width);
        printLine(width);


    }

    private void printLine(int width) {
        System.out.print("+"); // Corner
        for (int i = 0; i < width - 2; i++) {
            System.out.print("-");
        }
        System.out.println("+"); // Corner
    }
    private void printText(String text, int width) {
        System.out.print("|");

        int padding = width - 2 - text.length();
        int paddingBefore = padding / 2;
        int paddingAfter = padding - paddingBefore;


        for (int i = 0; i < paddingBefore; i++) {
            System.out.print(" ");
        }


        System.out.print(text);


        for (int i = 0; i < paddingAfter; i++) {
            System.out.print(" ");
        }

        System.out.println("|");
    }
}
