package game.scoreboard.domain;

import game.scoreboard.data.CreateTestEntities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {


  @Test
  void incrementHomeTeamScore_ok() {
    // given
    var given = Game.builder()
        .homeTeam(createHomeTeamForTest())
        .awayTeam(createAwayTeamForTest())
        .build();

    assertNotNull(given.getHomeTeamScore());
    assertEquals(0, given.getHomeTeamScore().getScore());
    assertNotNull(given.getAwayTeamScore());
    assertEquals(0, given.getAwayTeamScore().getScore());

    // when
    Score returned = given.incrementHomeTeamScore();

    // then
    assertNotNull(returned);
    assertEquals(1, returned.getScore());
    Score result = given.getHomeTeamScore();
    assertEquals(1, result.getScore());
  }

  @Test
  void incrementAwayTeamScore_ok() {
    // given
    var given = Game.builder()
        .homeTeam(createHomeTeamForTest())
        .awayTeam(createAwayTeamForTest())
        .build();

    assertNotNull(given.getHomeTeamScore());
    assertEquals(0, given.getHomeTeamScore().getScore());
    assertNotNull(given.getAwayTeamScore());
    assertEquals(0, given.getAwayTeamScore().getScore());

    // when
    Score returned = given.incrementAwayTeamScore();

    // then
    assertNotNull(returned);
    assertEquals(1, returned.getScore());
    Score result = given.getAwayTeamScore();
    assertEquals(1, result.getScore());
  }

  /* begin of

  /* begin of null parameter checking tests */

  @Test
  void exceptionBecauseOfHomeTeamNullParameter() {
    var exception = assertThrows(NullPointerException.class, () ->
        Game.builder().awayTeam(createAwayTeamForTest()).build());
  }


  private static Stream<Arguments> exceptionBecauseOfNullParameterData() {
    return Stream.of(
        Arguments.of(null, null),
        Arguments.of(null, createAwayTeamForTest()),
        Arguments.of(createHomeTeamForTest(), null));
  }

  @ParameterizedTest
  @MethodSource("exceptionBecauseOfNullParameterData")
  void exceptionBecauseOfNullParameter(Team homeTeam, Team awayTeam) {
    var exception = assertThrows(NullPointerException.class,
        () -> Game.builder()
          .homeTeam(homeTeam)
          .awayTeam(awayTeam)
          .build());
  }
  /* end of null parameter checking tests */


  private static Team createHomeTeamForTest() {
    return CreateTestEntities.createHomeTeamForTest();
  }

  private static Team createAwayTeamForTest() {
    return CreateTestEntities.createAwayTeamForTest();
  }

  @Test
  void getTeamsAndScoreInformation() {
    // given
    var game = CreateTestEntities.createSpainBrazilGame();
    // when
    String result = game.getTeamsAndScoreInformation();
    // then
    assertNotNull(result);
    assertEquals("Spain 10 - Brazil 2", result);
  }
}