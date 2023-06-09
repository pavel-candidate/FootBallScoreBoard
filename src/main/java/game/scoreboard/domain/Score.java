package game.scoreboard.domain;

import game.scoreboard.exceptions.MatchScoreIllegalArgumentException;
import game.scoreboard.interfaces.IntIncrementable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@ToString
@EqualsAndHashCode
public class Score implements IntIncrementable {

  interface ErrMsg { // this just for easy constants
    String SCORE_CANNOT_BE_LESS_ZERO = "Score cannot be < 0";
  }
  @Getter
  private int score;

  public Score() {
    this(0);
  }

  public Score(int score) {
    this.setScore(score);
  }

  public void setScore(int score) {
    if (score < 0) {
      throw new MatchScoreIllegalArgumentException(ErrMsg.SCORE_CANNOT_BE_LESS_ZERO);
    }
    this.score = score;
  }

  @Override
  public int increment() {
    return ++this.score;
  }

}
