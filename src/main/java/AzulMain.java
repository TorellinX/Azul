import java.util.ArrayList;
import java.util.Arrays;
import model.AzulModel;
import model.ColorTile;

public class AzulMain {

  public static void main(String[] args) {
    ArrayList<String> testPlayers = new ArrayList<>();
    testPlayers.add("Player1");
    testPlayers.add("Player2");

    AzulModel model = new AzulModel(testPlayers);
    System.out.println(model.getPlayers());
    System.out.println(model.getState());
    System.out.println(Arrays.deepToString(model.getTable().getPlates()));
    System.out.println(model.getTable().pickSameColorTiles(model.getTable().getPlates()[0],((ColorTile) model.getTable().getPlates()[0].get(0)).getColor()));

  }
}
