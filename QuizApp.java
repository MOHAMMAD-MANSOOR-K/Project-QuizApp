import java.util.Scanner;

public class QuizApp {
	   public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        System.out.println(	"*********" +" welcome to QuizGame" +"********");
	        System.out.print("Enter player name: ");
	        String name = sc.nextLine();

	        Player player = new Player(name);
	        Game game = new Game();
	        game.start(player);
	    }
}
