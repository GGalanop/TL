public class TicketMachineInterface {
    private TicketSelectionMenu ticketSelectionMenu;

    // Constructor
    public TicketMachineInterface() {
        this.ticketSelectionMenu = new TicketSelectionMenu();
    }

    // Method to receive input from user
    public void receivesInput() {
        TicketIssuingController controller = new TicketIssuingController();
        boolean ticketIssued = false;
        
        while (!ticketIssued) {
            String ticketType = ticketSelectionMenu.displayMenu();
            ticketIssued = controller.registerSpace(ticketType);
        }
    }

    // Main method for testing the TicketMachineInterface
    public static void main(String[] args) {
        TicketMachineInterface machine = new TicketMachineInterface();
        machine.receivesInput();
    }
}

