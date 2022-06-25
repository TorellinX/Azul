package de.lmu.ifi.sosylab;

import de.lmu.ifi.sosylab.model.AzulModel;
import de.lmu.ifi.sosylab.model.ColorTile;
import de.lmu.ifi.sosylab.model.PenaltyTile;
import de.lmu.ifi.sosylab.model.PlayerBoard;
import de.lmu.ifi.sosylab.model.State;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import de.lmu.ifi.sosylab.model.Color;
import de.lmu.ifi.sosylab.model.Tile;

/**
 * This class provides the utility to interactively play Azul from a shell.
 */
class Shell {

  private static final String PROMPT = "azul> ";
  private AzulModel model;
  private State state = null;
  ArrayList<String> playerList;

  int players = 0;
  int playerIndex = 0;

  private static final String HELP = """
      Azul - possible commands:
      forfeit      calculate current scores and end game
      help         print this help
      new x        (re)start a game with x players (min 2, max 4)
      pick s:x y   pick tile x (color) from source s and set to row y
                   -> colors: be-blue, bk-black, yw-yellow, rd-red, we-white
                   -> sources: n-factory number n (n = 1..9), c-table center
                   -> factory example: 2 bl be we we
                   -> table center example: c <p> rd rd we bk rd yw yw
                   -> row 1..5
      quit         quit the program
      """;


  /**
   * The main loop that handles the shell interaction. It takes commands from the user and executes
   * them.
   *
   * @throws IOException thrown when reading from stdin fails
   */
  void run() throws IOException {
    List<String> noParaCommands = Arrays.asList("forfeit", "help", "quit");
    List<String> paraCommands = Arrays.asList("new", "pick");
    List<String> colorCodes = Arrays.asList("be", "bk", "rd", "we", "yw");

    Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

    String input = "";

    while (!input.equals("quit")) {

      players = 0;
      playerIndex = 0;

      do {
        // Get valid input in the course of the game.
        // Only commands and representatives available at this point are supposed to come through.
        do {
          System.out.print(PROMPT);
          input = scanner.nextLine();
        } while (!inputValidation(input, noParaCommands, paraCommands, colorCodes, players));

        // Process commands while game is active.
        switch (input) {
          case "quit":
            System.out.println("bye bye");
            break;
          case "help":
            System.out.println(HELP);
            break;
          case "forfeit":
            // TODO. -> right now just repeats current printout
            // This command maybe obsolete, as it seems not useful for testing the model.
            break;
          default:
            String[] tokens = input.trim().split("\\s+");
            switch (tokens[0]) {
              case "new":
                players = Integer.parseInt(tokens[1]);
                playerList = new ArrayList<>();
                for (int i = 0; i < players; i++) {
                  playerList.add("Player" + (i + 1));
                }
                this.model = new AzulModel(playerList);
                playerIndex = 0;
                break;
              case "pick":
                String[] pickArgTok = tokens[1].trim().split(":");
                if (pickArgTok[0].equals("c")) {
                  System.out.println("pick from table center not yet implemented");
                } else {
                  int plateNumber = Integer.parseInt(pickArgTok[0]) - 1;
                  int rowNumber =Integer.parseInt(tokens[2]) - 1;
                  var plate = model.getTable().getPlates().get(plateNumber);
                  boolean pickCheck = true;
                  for (Tile tile : plate) {
                    ColorTile colorTile = (ColorTile) tile;
                    if (colorTile.getColor() == getColorFromCode(pickArgTok[1])) {
                      pickCheck = false;
                    }
                  }
                  if (pickCheck) {
                    System.out.println("Error! No such color on plate.");
                    break;
                  }
                  ArrayList<ColorTile> pickedTiles = model.getTable().pickSameColorTiles(plate, getColorFromCode(pickArgTok[1]));
                  model.getTable().getBoards()[playerIndex].setPatternLine(rowNumber, pickedTiles.size(),pickedTiles.get(0));
                  for (Tile tile : plate) {
                    ColorTile colorTile = (ColorTile) tile;
                    if (colorTile.getColor() != getColorFromCode(pickArgTok[1])) {
                      model.getTable().addTileToTableCenter(tile);
                    }
                  }
                  model.getTable().clearPlate(plateNumber);
                }

                // TODO. -> right now just steps the player asked to pick and set.
                if ((playerIndex + 1) < playerList.size()) {
                  playerIndex++;
                } else {
                  playerIndex = 0;
                }
                break;
               default:
                // do nothing
            }

        }

        if (!(input.equals("quit") || input.equals("help"))) {
          printPlayingField(model);
          System.out.print("\n");
          if (model.getState().equals(State.RUNNING)) {
            System.out.println("Player" + (playerIndex + 1) + " to pick and set a tile!");
          }
        }
        // if not quit - Print Playingfield
        // if not quit or forfeit - Step player and prompt for action

      } while (!input.equals("quit") && !input.equals("forfeit"));

      // end frame while loop
    }
  }

   /**
   * Checks validity of input during game run and prints error messages.
   *
   * @param input first input string to check for allowed commands
   * @param noParaCommands all commands representatives not accompanied by some parameter
   * @param paraCommands all command representatives accompanied by parameters
   * @param players number of players - no active game: 0
   * @return boolean indicating if input is valid (then true)
   */
  private boolean inputValidation(String input,
                                 List<String> noParaCommands,
                                 List<String> paraCommands,
                                 List<String> colorCodes,
                                 int players) {
    String[] tokens = input.trim().split("\\s+");
    int tokenCount = tokens.length;
    if (!(noParaCommands.contains(tokens[0]) || paraCommands.contains(tokens[0]))) {
      System.out.println("Error! Invalid command!");
      return false;
    } else if (noParaCommands.contains(tokens[0]) && (tokenCount > 1)) {
      System.out.println("Error! Invalid arguments: command does not take arguments!");
      return false;
    } else if (paraCommands.contains(tokens[0])) {
      if (tokens[0].equals("new") && tokenCount != 2) {
        System.out.println("Error! Command expects one argument.");
        return false;
      } else if (tokens[0].equals("new") && !(tokens[1].matches("[2-4]"))) {
        System.out.println("Error! Only 2, 3, or 4 players are possible.");
        return false;
      }
      if (!tokens[0].equals("new") && (players <= 0)) {
        System.out.println("Error! Command requires active game.");
        return false;
      } else {
        if (tokens[0].equals("pick") && tokenCount != 3) {
          System.out.println("Error! Command expects two arguments.");
          return false;
        } else if (tokens[0].equals("pick")) {
          String[] firstArgTok = tokens[1].trim().split(":");
          String factories = String.valueOf(2 * players + 1);
          if (firstArgTok.length != 2) {
            System.out.println("Error! Wrong format of first argument.");
            return false;
          } else if (!(firstArgTok[0].equals("c") ||
              firstArgTok[0].matches("[1-" + factories + "]"))) {
            System.out.println("Error! Wrong argument format or no such tile source.");
            return false;
          } else if (!colorCodes.contains(firstArgTok[1])) {
            System.out.println("Error! Wrong argument format or no such color code.");
            return false;
          }
          if (!(tokens[2].matches("[1-5]"))) {
            System.out.println("Error! Please choose a row number out of 1..5 to set tile.");
            return false;
          }
          return true;
        }
        return true;
      }
    } else {
      return true;
    }
  }

  private Color getColorFromCode(String colorCode) {
    switch (colorCode) {
      case "bk":
        return Color.BLACK;
      case "be":
        return Color.BLUE;
      case "rd":
        return Color.RED;
      case "we":
        return Color.WHITE;
      case "yw":
        return Color.YELLOW;
      default:
        return null;
    }
  }

  private void printPlayingField(AzulModel model) {
    // factories and center
    var plates = model.getTable().getPlates();
    ArrayList<Tile> pool = model.getTable().getTableCenter();
    System.out.print("Factories: ");
    for (int i = 0; i < plates.size(); i++) {
      System.out.print((i + 1) + ": " + plates.get(i).toString() + "; ");
      if (i == 4) {
        System.out.println("");
      }
    }
    System.out.print("\n");
    System.out.println("Center pool: " + pool.toString());
    System.out.print("\n");

    // rows and wall
    ArrayList<String> playerList = model.getPlayers();
    int k = 0;
    for (String player : playerList) {
      boolean booleanWall[][] = model.getTable().getBoards()[k].getWall();
       System.out.println(player + ":");
      // player board rows
      for (int i = 0; i < 5; i++) {
        // pattern line
        int occupied = model.getTable().getBoards()[k].getPatternLines()[i].getOccupancy();
        for (int m = 0; m < 4 - i; m++) {
          System.out.print("    ");
        }
        for (int m = 4 - i; m < 5; m++) {
          if (m < (5 - occupied)) {
            System.out.print("(  )");
          } else {
            System.out.print("(" + getColorCodeFromColorTile(model.getTable().getBoards()[k].getPatternLines()[i].getColorTile()) + ")");
          }
        }
        // wall line
        for (int j = 0; j < 5; j++) {
          if (booleanWall[i][j]) {
            System.out.print("[" + colorPatchedWall()[i][j] + "]");
          } else {
            System.out.print(" " + colorPatchedWall()[i][j] + " ");
          }
        }
        System.out.print("\n");
      }
      // floor line
      if (model.getTable().getBoards()[k].getFloorLine() == null) {
        for (int i = 0; i < 7; i++) {
          if (i < 2) {
            System.out.print("{-1}");
            System.out.print("(  )");
          } else if ((i >= 2) && (i < 5)) {
            System.out.print("{-2}");
            System.out.print("(  )");
          } else {
            System.out.print("{-3}");
            System.out.print("(  )");
          }
        }
      } else {
        for (int i = 0; i < model.getTable().getBoards()[k].getFloorLine().size(); i++) {
          Tile floorLineTile = model.getTable().getBoards()[k].getFloorLine().get(i);
          if (i < 2) {
            System.out.print("{-1}");
            if (floorLineTile instanceof PenaltyTile) {
              System.out.print("(-1)");
            } else if (floorLineTile instanceof ColorTile) {
              System.out.print("(" + getColorCodeFromColorTile((ColorTile) floorLineTile) + ")");
            }
          } else if ((i >= 2) && (i < 5)) {
            System.out.print("{-2}");
            System.out.print("(" + getColorCodeFromColorTile((ColorTile) floorLineTile) + ")");
          } else {
            System.out.print("{-3}");
            System.out.print("(" + getColorCodeFromColorTile((ColorTile) floorLineTile) + ")");
          }
        }
        for (int i = 7 - model.getTable().getBoards()[k].getFloorLine().size(); i < 7; i++) {
          System.out.print("(  )");
        }
      }
      System.out.println("\n");
      k++;
    }
  }


  private String[][] colorPatchedWall () {
    String[][] wall = new String[5][5];
    String wallAsMultilineString = """
        be yw rd bk we
        we be yw rd bk
        bk we be yw rd
        rd bk we be yw
        yw rd bk we be
        """;
    String[] lines = wallAsMultilineString.split("\\r?\\n");
    for (int i = 0; i < 5; i++) {
      String line = lines[i];
      String[] lineTokens = line.split("\\s+");
      for (int j = 0; j < 5; j++) {
        wall[i][j] = lineTokens[j];
      }
    }
    return wall;
  }

  private String getColorCodeFromColorTile(ColorTile colorTile){
    Color color = colorTile.getColor();
    switch (color) {
      case BLACK:
        return "bk";
      case BLUE:
        return "be";
      case RED:
        return "rd";
      case WHITE:
        return "we";
      case YELLOW:
        return "yw";
      default:
        return "";
    }

  }

  // end class
}

