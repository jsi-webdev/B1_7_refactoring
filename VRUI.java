public class VRUI {
	private final VRLogic VRLogic = new VRLogic();

	public static void main(String[] args) {
		VRUI ui = new VRUI() ;

		boolean quit = false ;
		while ( ! quit ) {
			int command = ui.showCommand() ;
			switch ( command ) {
				case 0: quit = true ; break ;
				case 1: ui.VRLogic.listCustomers() ; break ;
				case 2: ui.VRLogic.listVideos() ; break ;
				case 3: ui.VRLogic.register("customer") ; break ;
				case 4: ui.VRLogic.register("video") ; break ;
				case 5: ui.VRLogic.rentVideo() ; break ;
				case 6: ui.VRLogic.returnVideo() ; break ;
				case 7: ui.VRLogic.getCustomerReport() ; break;
				case 8: ui.VRLogic.clearRentals() ; break ;
				case -1: ui.VRLogic.init() ; break ;
				default: break ;
			}
		}
		System.out.println("Bye");
	}

	public int showCommand() {
		System.out.println("\nSelect a command !");
		System.out.println("\t 0. Quit");
		System.out.println("\t 1. List customers");
		System.out.println("\t 2. List videos");
		System.out.println("\t 3. Register customer");
		System.out.println("\t 4. Register video");
		System.out.println("\t 5. Rent video");
		System.out.println("\t 6. Return video");
		System.out.println("\t 7. Show customer report");
		System.out.println("\t 8. Show customer and clear rentals");

		int command = VideoManager.scanner.nextInt() ;

		return command ;

	}
}
