package HotelBase;
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
					break;
				case "room_cost", "rc", "room_c":
					changeRoomCost(hotel);
					break;			
				case "save_data", "save_d":
					saveData(hotel);
					break;
				case "load_data", "load_d":
					loadData(hotel);
					break;
				default:
					System.out.println("Command \"" + input + "\" not recognized.");
					
				}
			}
				
		}

	}
	
	private static void loadData(Hotel h) {
		h.loadHotelData();
	}
	
	private static void saveData(Hotel h) {
		try {
			h.saveHotelData();	
			System.out.println("Hotel data successfully saved, and backed up!");
		}
		catch (Exception ex) {
			System.out.println("There was an issue trying to save the hotel data.  Please contact your system admin.");
		}
		
	}
	
	private static void changeRoomCost(Hotel h) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter a room number: ");
		int roomNumber = scanner.nextInt();
		
		try {
			System.out.println(h.getRoomById(roomNumber).toString());
			System.out.println("Enter a new room cost: ");
			int newCost = scanner.nextInt();
			
			h.changeRoomCost(roomNumber, newCost);
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}			
		
	}
	
	private static void listRooms(Hotel h) {
		System.out.println("--------------------------------------------------\n\n");
		System.out.println(h.listRooms());
		System.out.println("--------------------------------------------------");			
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
		System.out.println("(1) NORMAL");
		System.out.println("(2) SUITE");
		System.out.println("(3) EXECECUTIVE_SUITE");
		System.out.println("(4) PRESIDENTIAL_SUITE");
		System.out.println("(5) PENTHOUSE");
		String rTypeInput = "";
		Room.RoomType roomType;
		while (!isValidRoomType(rTypeInput)) {
			System.out.println("Room Type: ");
			rTypeInput = roomScanner.nextLine().toUpperCase();
		}
		
		roomType = getRoomType(rTypeInput);
		
		System.out.println("");
		System.out.println("Please confirm the following room details (Y/N): ");
		System.out.println("Full beds: " + fullBedCount);
		System.out.println("Queen beds: " + queenBedCount);
		System.out.println("King beds: " + kingBedCount);
		System.out.println("Bathrooms: " + bathroomCount);
		System.out.println("Room Type: " + roomType.name());
		System.out.println("");
		
		String confirm = "";
		
		while (!confirm.equals("Y") && !confirm.equals("N"))
			confirm = roomScanner.nextLine().toUpperCase();
		
		if (confirm.equals("Y")) {
			ArrayList<Bed> beds = getNewBeds(fullBedCount, queenBedCount, kingBedCount);
			Room newRoom;
			
			try {
				newRoom = new Room(beds, bathroomCount, roomType);
				h.addRoom(newRoom);				
				
				System.out.println("");
				System.out.println("Room successfully created!");
				System.out.println("---------------------------------------------------");
				System.out.println(newRoom.toString());
			}
			catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

			

		}
		else {
			System.out.println("Room creation process cancelled.  Please run \"add_room\" again if you would like to restart");
		}
		
		
		
		
	}
	
	private static Room.RoomType getRoomType(String rType) {
		switch (rType.toUpperCase()) {
		case "NORMAL", "1":
			return Room.RoomType.NORMAL;
		case "SUITE", "2":
			return Room.RoomType.SUITE;			
		case "EXECECUTIVE_SUITE", "3":
			return Room.RoomType.EXECECUTIVE_SUITE;
		case "PRESIDENTIAL_SUITE", "4":
			return Room.RoomType.PRESIDENTIAL_SUITE;			
		case "PENTHOUSE", "5":
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
		return rType.equals("1") || rType.equals("2") || rType.equals("3") || rType.equals("4") || rType.equals("5") ||
		rType.equals("NORMAL") || rType.equals("SUITE") || rType.equals("EXECUTIVE_SUITE") || rType.equals("PRESIDENTIAL_SUITE") || rType.equals("PENTHOUSE");
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
		System.out.println("room_cost");
		System.out.println("employee <id>");
		System.out.println("booking <id>");
		System.out.println("room <id>");	
		System.out.println("save_data");
		System.out.println("load_data");
	}

}
