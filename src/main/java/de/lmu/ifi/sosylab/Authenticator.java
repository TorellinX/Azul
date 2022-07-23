package de.lmu.ifi.sosylab;

import java.util.Random;

public class Authenticator {

  private String authenticator = "";
  private final int lengthOfAuthenticator = 10;
  private final String LOWER = "abcdefghijklmnopqrstuvwxyz";
  private final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private final String DIGITS = "0123456789";
  private final String PUNCTUATION = "!@#$%&*()_+-=[]|,./?><";


  public Authenticator() {
    createAuthenticator();
  }

  private void createAuthenticator() {
    Random random = new Random();

    for (int i = 0; i < lengthOfAuthenticator; i++) {

      switch (random.nextInt(4)) {
        case 1:
          authenticator.concat(Character.toString(LOWER.charAt(random.nextInt(LOWER.length()))));
          break;
        case 2:
          authenticator.concat(Character.toString(UPPER.charAt(random.nextInt(UPPER.length()))));
          break;
        case 3:
          authenticator.concat(Character.toString(DIGITS.charAt(random.nextInt(DIGITS.length()))));
          break;
        case 4:
          authenticator.concat(
              Character.toString(PUNCTUATION.charAt(random.nextInt(PUNCTUATION.length()))));
          break;
      }
    }
  }

  public String getAuthenticator() {
    return authenticator;
  }

}
