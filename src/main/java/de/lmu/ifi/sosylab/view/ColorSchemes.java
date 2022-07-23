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
      new Color(255, 193, 37),
      new Color(205, 50, 120),
      new Color(87, 87, 87),
      new Color(46, 139, 87),
      new Color(16, 78, 139),
      Color.lightGray,
      Color.black,
      new Color(107, 160, 255),
      new Color(211, 215, 216),
      new Color(237, 250, 255),
      new Color(133, 159, 181),
      new Color(128, 128, 128),
      Color.white,
      Color.DARK_GRAY,
      new Color(0, 79, 139),
      Color.BLACK,
      Color.BLACK,
      new Color(191, 239, 255),
      new Color(176, 226, 255),
      "back_classic.png"
  );


  public static ColorScheme beach = new ColorScheme(
      new Color(238, 221, 130),
      new Color(238, 59, 59),
      new Color(112, 112, 112),
      new Color(102, 205, 170),
      new Color(100, 149, 237),
      Color.lightGray,
      Color.black,
      new Color(219, 135, 76),
      new Color(204, 201, 199),
      new Color(242, 242, 242),
      new Color(237, 216, 192),
      new Color(241, 243, 236),
      Color.white,
      Color.DARK_GRAY,
      new Color(107, 67, 62),
      Color.BLACK,
      Color.BLACK,
      new Color(255, 255, 255),
      new Color(245, 240, 212),
      "back_beach.png"
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
      new Color(65, 19, 120),
      new Color(128, 128, 128),
      new Color(160, 160, 160),
      new Color(224, 224, 224),
      new Color(224, 224, 224),
      new Color(224, 224, 224),
      new Color(224, 224, 224),
      new Color(33, 0, 74),
      new Color(27, 8, 50),
      "back_cosmic.png"
  );
  public static ColorScheme candy = new ColorScheme(
      new Color(255, 239, 0),
      new Color(255, 127, 182),
      new Color(94, 79, 121),
      new Color(182, 255, 0),
      new Color(138, 221, 255),
      Color.lightGray,
      Color.black,
      new Color(255, 216, 0),
      new Color(238, 226, 245),
      Color.WHITE,
      new Color(196, 245, 231),
      new Color(255, 218, 218),
      new Color(173, 164, 178),
      new Color(199, 199, 199),
      new Color(139, 0, 139),
      Color.BLACK,
      Color.BLACK,
      new Color(248, 255, 209),
      new Color(202, 245, 196),
      "back_candy.png"
  );
}
