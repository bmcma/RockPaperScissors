package rockpaperscissors;

/**
 * @author Brian McMahon
 * Simple Rock Paper Scissors game where the users selections is played against a random selection for the computer.
 * The game layout class sets the layout and features of the game using javafx
 * Sounds provided by Mike Koenig and Joe Lamb from www.soundbible.com
 * Images provided by www.openclipart.org
 */

import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameLayout extends Application {

	private Button rock, paper, scissors, playAgain, displayRules, okButton;
	private Scene play, displayResult, rulesScene;
	private Text welcomeMessage, rulesMessage, startMessage, resultMessage;
	private static final AudioClip win = new AudioClip(GameLayout.class
			.getResource("/Ta Da-SoundBible.com-Mike Koenig.wav").toString());
	private static final AudioClip lose = new AudioClip(GameLayout.class
			.getResource("/Sad_Trombone-Joe_Lamb-soundbible.com.wav")
			.toString());
	private int userSelection;
	private int compSelection;
	private String result;
	private String player1Pick;
	private String computerPick;

	// launch the javafx gui
	public static void main(String[] args) {
		launch(args);
	}

	// sets the gui layout on startup
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Rock - Paper - Scissors");

		// sets the texts for the main scene
		welcomeMessage = new Text("Welcome to Rock Paper Scissors!");
		welcomeMessage.setFont(Font.font("", FontPosture.ITALIC, 18));
		rulesMessage = new Text(
				"Remember that Rock beats Scissors, Paper beats Rock and Scissors beats Paper.");
		startMessage = new Text("Please make your selection below:");
		startMessage.setFont(Font.font("", FontPosture.ITALIC, 12));
		resultMessage = new Text();
		resultMessage.setFont(Font.font("", FontPosture.ITALIC, 18));

		Image rockImage = new Image("rock.png");
		Image paperImage = new Image("paper.png");
		Image scissorsImage = new Image("scissors.png");

		// details of play scene
		// set up the buttons for the main scene
		displayRules = new Button("Rules");
		displayRules.setPrefWidth(550);
		rock = new Button("  Rock  ");
		rock.setGraphic(new ImageView(rockImage));
		paper = new Button("  Paper  ");
		paper.setGraphic(new ImageView(paperImage));
		scissors = new Button("Scissors");
		scissors.setGraphic(new ImageView(scissorsImage));
		// sets the VBox with message details
		VBox vbox = new VBox();
		vbox.getChildren().addAll(welcomeMessage, rulesMessage, startMessage);
		vbox.setAlignment(Pos.CENTER);
		// sets the HBox with the buttons
		HBox hbox1 = new HBox();
		hbox1.getChildren().addAll(rock, paper, scissors);
		hbox1.setAlignment(Pos.CENTER);
		hbox1.setPadding(new Insets(5, 5, 5, 5));
		hbox1.setSpacing(15);
		// new main layout to contain the vbox and hbox1 layouts and rules
		// button
		VBox main = new VBox(5);
		main.getChildren().addAll(vbox, hbox1, displayRules);
		main.setAlignment(Pos.CENTER);
		main.setPadding(new Insets(15, 15, 15, 15));
		// sets the containing layout
		BorderPane border = new BorderPane();
		border.setCenter(main);

		// details of displayResults scene
		playAgain = new Button("Play Again");
		BorderPane border1 = new BorderPane();
		VBox results = new VBox(10);
		results.getChildren().addAll(resultMessage, playAgain);
		results.setAlignment(Pos.CENTER);
		border1.setCenter(results);

		// sets the two scenes to equal sizes
		play = new Scene(border, 600, 220);
		displayResult = new Scene(border1, 600, 220);

		// action events on button clicks
		rock.setOnAction(e -> {
			userSelection = 1;
			player1Pick = "Rock";
			displayResults();
			primaryStage.setScene(displayResult);
		});

		paper.setOnAction(e -> {
			userSelection = 2;
			player1Pick = "Paper";
			displayResults();
			primaryStage.setScene(displayResult);
		});

		scissors.setOnAction(e -> {
			userSelection = 3;
			player1Pick = "Scissors";
			displayResults();
			primaryStage.setScene(displayResult);
		});

		playAgain.setOnAction(e -> {
			primaryStage.setScene(play);
		});

		displayRules.setOnAction(e -> {
			displayRulesList();
		});

		// set the scene and display the gui
		primaryStage.setScene(play);
		primaryStage.show();

	}

	// sets a random number for the computer selection
	public int getCompSelection() {
		Random random = new Random();
		compSelection = random.nextInt(3) + 1;
		if (compSelection == 1) {
			computerPick = "Rock";
		} else if (compSelection == 2) {
			computerPick = "Paper";
		} else if (compSelection == 3) {
			computerPick = "Scissors";
		}
		return compSelection;
	}

	// applies the game logic
	public String runGame() {
		getCompSelection();
		if ((userSelection == 1 && compSelection == 3)
				|| (userSelection == 2 && compSelection == 1)
				|| (userSelection == 3 && compSelection == 2)) {
			result = "Congratulations. You win!!";
			GameLayout.win.play();
		} else if ((userSelection == 1 && compSelection == 2)
				|| (userSelection == 2 && compSelection == 3)
				|| (userSelection == 3 && compSelection == 1)) {
			result = "The computer wins. Better luck next time!";
			GameLayout.lose.play();
		} else {
			result = "The game was a tie! Please try again.";
			GameLayout.lose.play();
		}
		return result;
	}

	// displays the outcome of the game
	public void displayResults() {
		runGame();
		resultMessage.setText("You picked " + player1Pick
				+ " and the computer picked " + computerPick + ".\n" + result);
	}

	// displays the alert box to show the game rules and instructions
	public void displayRulesList() {
		Stage ruleList = new Stage();
		// keeps focus on alert box
		ruleList.initModality(Modality.APPLICATION_MODAL);
		ruleList.setTitle("Rules of the Game");

		Label rules = new Label();
		rules.setText("The rules of Rock, Paper, Scissors are simple. \n"
				+ "Rock beats Scissors, Paper beats Rock and Scissors beats Paper. \n"
				+ "In order to make your choice you simply click on the relevant button. \n"
				+ "You are playing against the computer and the computer's \n"
				+ "choice is randomly selected. \n" + "Enjoy the game!!");
		rules.setFont(Font.font("", FontPosture.ITALIC, 15));
		okButton = new Button("OK");

		// closes the alert box
		okButton.setOnAction(e -> ruleList.close());

		VBox setRules = new VBox(5);
		setRules.getChildren().addAll(rules, okButton);
		setRules.setAlignment(Pos.CENTER);
		setRules.setPadding(new Insets(5, 5, 5, 5));

		rulesScene = new Scene(setRules);
		ruleList.setScene(rulesScene);
		ruleList.sizeToScene();
		// displays until window is closed
		ruleList.showAndWait();
	}

}
