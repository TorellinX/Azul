package de.lmu.ifi.sosylab.view;

import java.awt.Color;

public final class ColorSchemes {

  private ColorSchemes() {
  }

  public record ColorScheme(Color yellow,
                            Color red,
                            Color black,
                            Color green,
                            Color blue,
                            Color penalty,
                            Color penaltyText,
                            Color activePlayer,
                            Color inactivePlayer,
                            Color wallBackground,
                            Color menu,
                            Color table,
                            Color plateFill,
                            Color plateBorder,
                            Color floorlineFrame,
                            Color scoreText,
                            Color patternlineFrame,
                            Color playerboard,
                            Color playingView,
                            String boardBackgroundImage) {

  }

  public static ColorScheme classic = new ColorScheme(
      new Color(227, 200, 0),
      new Color(229, 20, 0),
      new Color(51, 0, 26),
      new Color(109, 135, 100),
      new Color(83, 103, 183),
      Color.lightGray,
      Color.black,
      new Color(88, 191, 88),
      new Color(204, 201, 199),
      new Color(224, 224, 224),
      new Color(155, 175, 255),
      new Color(230, 217, 161),
      Color.white,
      Color.DARK_GRAY,
      new Color(139, 0, 139),
      Color.BLACK,
      Color.BLACK,
      new Color(255, 255, 255),
      new Color(245, 240, 212),
      "/back_yellow.png"
  );
  public static ColorScheme cosmic = new ColorScheme(
      new Color(248, 255, 73),
      new Color(255, 56, 200),
      new Color(255, 255, 255),
      new Color(119, 255, 45),
      new Color(113, 68, 255),
      Color.lightGray,
      Color.black,
      new Color(214, 127, 255),
      new Color(204, 201, 199),
      new Color(160, 160, 160),
      new Color(155, 175, 255),
      new Color(128, 128, 128),
      new Color(160, 160, 160),
      new Color(224, 224, 224),
      new Color(224, 224, 224),
      new Color(224, 224, 224),
      new Color(224, 224, 224),
      new Color(33, 0, 74),
      new Color(27, 8, 50),
      "/back_sky.png"
  );
  public static ColorScheme candy = new ColorScheme(
      new Color(255, 239, 0),
      new Color(255, 127, 182),
      new Color(94, 79, 121),
      new Color(182, 255, 0),
      new Color(181, 231, 251),
      Color.lightGray,
      Color.black,
      new Color(255, 216, 0),
      new Color(189, 179, 194),
      Color.WHITE,
      new Color(155, 175, 255),
      new Color(255, 204, 204),
      new Color(189, 179, 194),
      new Color(199, 199, 199),
      new Color(139, 0, 139),
      Color.BLACK,
      Color.BLACK,
      new Color(248, 255, 209),
      new Color(202, 245, 196),
      "/back_pastel.png"
  );
}
