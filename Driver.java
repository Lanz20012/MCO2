
/**
 * The Driver class serves as the entry point for the Hotel Management System application.
 * It initializes the model, view, and controller components, and sets up their interactions.
 */
public class Driver {
    public static void main(String[] args) {
        // Create model
        HotelTracker model = new HotelTracker();

        // Create GUI
        HotelGUI view = new HotelGUI();

        // Create controller
        HotelController controller = new HotelController(view, model);

        // Set controller in the view
        view.setController(controller);
    }
}
