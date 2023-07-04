package game.scoreboard.domain;

import game.scoreboard.exceptions.MatchScoreIllegalArgumentException;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScoreTest {

  @Test
  void afterInitScoreMustBeZero_ok() {
    Score score = new Score();

    assertEquals(0, score.getScore());
  }

  @Test
  void incrementThreeTimes_ok() {
    Score score = new Score();

    IntStream.rangeClosed(1, 3).forEach(i -> {
      assertEquals(i, score.increment());
      assertEquals(i, score.getScore());
    });
  }

  @Test
  void exceptionSettingTheNegativeValue() {
    // given
    Score score = new Score();

    // when then
    var exception = assertThrows(MatchScoreIllegalArgumentException.class,
        () -> score.setScore(-2));
    assertEquals(Score.ErrMsg.SCORE_CANNOT_BE_LESS_ZERO, exception.getMessage());
  }

}
