package game.scoreboard.exceptions;

public class GameScoreIllegalArgumentException extends IllegalArgumentException {

  public GameScoreIllegalArgumentException(String message) {
    super(message);
  }

  public GameScoreIllegalArgumentException(String message, Throwable cause) {
    super(message, cause);
  }

}
