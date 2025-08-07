import java.util.List;
import java.util.Scanner;

public class DriverAPP {

    private final Scanner sc = new Scanner(System.in);
    private final UserService userService = new UserService();
    private final BookingService bookingService = new BookingService();

    public static void main(String[] args) {
        new DriverAPP().start();
    }

    public void start()
    {
        while(true)
        {
            System.out.println("\nWelcome to IRCTC APP\n");
            if(!userService.isLoggedIn())
            {
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("\nEnter your choice : ");
                int choice = sc.nextInt();
                switch(choice){
                    case 1 -> register();
                    case 2 -> login();
                    case 3 -> exit();
                    default -> System.out.println("Invalid Choice");
                }
            }
            else{
                showUserMenu();
            }
        }
    }

    public void register()
    {
        System.out.print("Enter username : ");
        String userName = sc.next();
        System.out.print("Enter password : ");
        String password = sc.next();
        System.out.print("Enter Full Name : ");
        sc.nextLine();
        String fullName = sc.nextLine();
        System.out.print("Enter Phone Number : ");
        String phoneNo = sc.next();

        userService.registerUser(userName,password,fullName,phoneNo);
    }

    public void login()
    {
        System.out.print("Enter Username : ");
        String userName = sc.next();
        System.out.print("Enter password : ");
        String password = sc.next();
        userService.login(userName,password);
    }


    private void showUserMenu()
    {
        while(userService.isLoggedIn()){
            System.out.println("\n------User Menu-------\n");
            System.out.println("1. Search Trains");
            System.out.println("2. Book Tickets");
            System.out.println("3. View My Ticket");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. View All Trains");
            System.out.println("6. Logout");
            System.out.print("Enter your choice : ");
            int choice = sc.nextInt();
            switch(choice){
                case 1 -> searchTrain();
                case 2 -> bookTicket();
                case 3 -> viewMyTicket();
                case 4 -> cancelTicket();
                case 5 -> bookingService.listAllTrains();
                case 6 -> userService.logOutUser();
                default -> System.out.println("Invalid Choice!");
            }
        }
    }

    private void searchTrain()
    {
        System.out.print("Enter Source Station : ");
        String source = sc.next();
        System.out.print("Enter Destination Station : ");
        String destination = sc.next();

        List<Train> trains = bookingService.searchTrain(source,destination);
        if(trains.isEmpty()){
            System.out.println("No Trains Found between "+source+" and "+destination);
            return;
        }
        System.out.println("Trains Found : ");
        for(Train train : trains){
            System.out.println(train);
        }

        System.out.print("Do you want to book ticket? (yes/no) : ");
        String choice = sc.next();
        if(choice.equalsIgnoreCase("yes")){
            System.out.print("Enter Train ID to book : ");
            int trainId = sc.nextInt();
            System.out.print("Enter number of seats : ");
            int seats = sc.nextInt();

            Ticket ticket = bookingService.bookTicket(userService.getCurrentUser(),trainId,seats);
            if(ticket!=null)
            {
                System.out.println("Booking Successful!");
                System.out.println(ticket);
            }
        }
        else {
            System.out.println("Returning to USER MENU...");
        }
    }

    private void bookTicket(){
        System.out.print("Enter Source Station : ");
        String source = sc.next();
        System.out.print("Enter Destination Station : ");
        String destination = sc.next();

        List<Train> trains = bookingService.searchTrain(source,destination);
        if(trains.isEmpty()){
            System.out.println("No Trains available for booking");
            return;
        }
        System.out.println("Available Trains : ");
        for(Train train : trains){
            System.out.println(train);
        }

        System.out.print("Enter Train ID to book : ");
        int trainId = sc.nextInt();
        System.out.print("Enter number of seats : ");
        int seats = sc.nextInt();

        Ticket ticket = bookingService.bookTicket(userService.getCurrentUser(),trainId,seats);
        if(ticket!=null)
        {
            System.out.println("Booking Successful!");
            System.out.println(ticket);
        }
    }

    private void viewMyTicket(){
        List<Ticket> ticketByUser = bookingService.getTicketByUser(userService.getCurrentUser());
        if(ticketByUser.isEmpty()){
            System.out.println("No ticket is booked yet");
        }
        else{
            System.out.println("Your tickets : ");
            for(Ticket ticket : ticketByUser){
                System.out.println(ticket);
            }
        }
    }

    private void cancelTicket(){
        System.out.print("Enter Ticket ID to cancel : ");
        int ticketId = sc.nextInt();
        bookingService.cancelTicket(ticketId,userService.getCurrentUser());
    }

    public void exit(){
        System.out.println("Thank you for using IRCTC App");
        System.exit(0);
    }
}
