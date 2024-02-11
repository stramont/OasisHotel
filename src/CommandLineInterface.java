import java.util.ArrayList;
import java.util.Scanner;

public class CommandLineInterface {

	
	
	public static void main(String[] args) {
		
		Hotel hotel = new Hotel(1);
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Activating Oasis Hotel Management System...");
		
		try {
			Thread.sleep(500);
			System.out.println(".");
			Thread.sleep(600);
			System.out.println(".");
			Thread.sleep(800);
			System.out.println(".");
			Thread.sleep(500);

		}
		catch (Exception ex){
			
		}
		
		System.out.println("All Systems: OK");
		System.out.println("Good morning, [NAME]!");
	
		
		while (true) {
			System.out.print("$> ");
			String input = scanner.nextLine();
			
			if (!input.equals("")) {
				switch (input) {
				case "help", "h":
					help();
					break;
				case "list_employees", "list_e":
					//listEmployees();
					break;
				case "list_rooms", "list_r":
					listRooms(hotel);
					break;
				case "list_bookings", "list_b":
					//listBookings();
					break;
				case "address":
					address(hotel);
					break;
				case "add_booking", "add_b":
					//addBooking();
					break;
				case "add_employee", "add_e":
					//addEmployee();
					break;
				case "add_room", "add_r":
					addRoom(hotel);
					listRooms(hotel);
					break;
				default:
					System.out.println("Command \"" + input + "\" not recognized.");
					
				}
			}
				
		}
		

	}
	
	private static void listRooms(Hotel h) {
		System.out.println(h.listRooms());
	}
	
	private static void addRoom(Hotel h) {
		Scanner roomScanner = new Scanner(System.in);
		System.out.println("How many FULL beds are in this room? ");
		int fullBedCount = roomScanner.nextInt();

		System.out.println("How many QUEEN beds are in this room? ");
		int queenBedCount = roomScanner.nextInt();
		
		System.out.println("How many KING beds are in this room? ");
		int kingBedCount = roomScanner.nextInt();
		
		System.out.println("How many bathrooms are in this room? ");
		int bathroomCount = roomScanner.nextInt();
		
		System.out.println("What type of room is this? Options: ");
		System.out.println("NORMAL");
		System.out.println("SUITE");
		System.out.println("EXECECUTIVE_SUITE");
		System.out.println("PRESIDENTIAL_SUITE");
		System.out.println("PENTHOUSE");
		String rTypeInput = "";
		while (!isValidRoomType(rTypeInput)) {
			System.out.println("Room Type: ");
			rTypeInput = roomScanner.nextLine();
		}
		
		System.out.println("");
		System.out.println("Please confirm the following room details (Y/N): ");
		System.out.println("Full beds: " + fullBedCount);
		System.out.println("Queen beds: " + queenBedCount);
		System.out.println("King beds: " + kingBedCount);
		System.out.println("Bathrooms: " + bathroomCount);
		System.out.println("Room Type: " + rTypeInput.toUpperCase());
		System.out.println("");
		
		String confirm = "";
		
		while (!confirm.equals("Y") && !confirm.equals("N"))
			confirm = roomScanner.nextLine().toUpperCase();
		
		if (confirm.equals("Y")) {
			ArrayList<Bed> beds = getNewBeds(fullBedCount, queenBedCount, kingBedCount);
			
			Room newRoom = new Room(beds, bathroomCount, getRoomType(rTypeInput));
			
			h.addRoom(newRoom);
			
			
			System.out.println("");
			System.out.println("Room successfully created!");
		}
		else {
			System.out.println("Room creation process cancelled.  Please run \"add_room\" again if you would like to restart");
		}
		
		
		
		roomScanner.close();
	}
	
	private static Room.RoomType getRoomType(String rType) {
		switch (rType.toUpperCase()) {
		case "NORMAL":
			return Room.RoomType.NORMAL;
		case "SUITE":
			return Room.RoomType.SUITE;			
		case "EXECECUTIVE_SUITE":
			return Room.RoomType.EXECECUTIVE_SUITE;
		case "PRESIDENTIAL_SUITE":
			return Room.RoomType.PRESIDENTIAL_SUITE;			
		case "PENTHOUSE":
			return Room.RoomType.PENTHOUSE;
		default:
			return Room.RoomType.NORMAL;
		}
	}
	
	private static ArrayList<Bed> getNewBeds(int fullCount, int queenCount, int kingCount) {
		ArrayList<Bed> newBeds = new ArrayList<Bed>();
		
		for (int i = 0; i < fullCount; i++)
			newBeds.add(new Bed(Bed.BedType.FULL));
		
		for (int i = 0; i < queenCount; i++)
			newBeds.add(new Bed(Bed.BedType.QUEEN));
		
		for (int i = 0; i < kingCount; i++)
			newBeds.add(new Bed(Bed.BedType.KING));
		
		
		return newBeds;
	}
	
	
	
	private static boolean isValidRoomType(String rType) {
		return rType.equals("NORMAL") || rType.equals("SUITE") || rType.equals("EXECUTIVE_SUITE") || rType.equals("PRESIDENTIAL_SUITE") || rType.equals("PENTHOUSE");
	}
	
	private static void address(Hotel h) {
		System.out.println(h.getAddress());
	}
	
	private static void help() {
		System.out.println("Available Commands: ");
		System.out.println("help");
		System.out.println("list_employees");
		System.out.println("list_rooms");
		System.out.println("list_bookings");
		System.out.println("address");
		System.out.println("add_booking");
		System.out.println("add_employee");
		System.out.println("add_room");
		System.out.println("employee <id>");
		System.out.println("booking <id>");
		System.out.println("room <id>");
		
		
	}

}
