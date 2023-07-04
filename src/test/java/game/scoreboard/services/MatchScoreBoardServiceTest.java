package game.scoreboard.services;

import game.scoreboard.data.CreateTestEntities;
import game.scoreboard.domain.Match;
import game.scoreboard.domain.Team;
import game.scoreboard.enums.MatchStatus;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MatchScoreBoardServiceTest {
  private final static MatchStatus GAME_STATUS_WAITING = MatchStatus.WAITING;


  @Test
  void startANewMatch_ok() {
    // given
    var service = new MatchScoreBoardService();
    var homeTeam = new Team("Home Team");
    var awayTeam = new Team("Away Team");

    // when
    Match result = service.startANewMatch(homeTeam, awayTeam);

    // then
    assertNotNull(result);
    assertEquals(0, result.getHomeTeamScore().getScore());
    assertEquals(0, result.getAwayTeamScore().getScore());
  }

  @Test
  void findMatch_ok() {
    // given
    var match = CreateTestEntities.createSpainPortugalMatch();
    var service = new MatchScoreBoardService();
    service.startANewMatch(match);

    // when
    Optional<Match> resOptional = service.findMatch(match);

    // then
    assertTrue(resOptional.isPresent());
    Match result = resOptional.get();
    assertEquals(match, result);
  }

  @Test
  void findMatch_not_found() {
    // given
    var match = CreateTestEntities.createSpainPortugalMatch();
    var notFoundMatch = CreateTestEntities.createGameForTest("home test", "away test");
    var service = new MatchScoreBoardService();
    service.startANewMatch(match);

    // when
    Optional<Match> resOptional = service.findMatch(notFoundMatch);

    // then
    assertFalse(resOptional.isPresent());
  }

  @Test
  void removeMatch_ok() {
    // given
    var match = CreateTestEntities.createSpainPortugalMatch();
    var service = new MatchScoreBoardService();
    assertEquals(0, service.sizeOfDS());

    service.startANewMatch(match);

    assertEquals(1, service.sizeOfDS());

    // when then
    assertTrue(service.removeMatch(match));
    assertEquals(0, service.sizeOfDS());
  }

  @Test
  void getMatchesScoreItems() {
    // given
    var service = new MatchScoreBoardService();
    List<Match> listOfCompletedMatches = createMatchesScoreItemsList(GAME_STATUS_WAITING);
    listOfCompletedMatches.forEach(service::addItems);

    // when
    List<Match> result = service.getMatchesScoreItems();

    // then
    assertNotNull(result);
  }

  @Test
  void getMatchesWithHighestScores() {
    // given
    var service = new MatchScoreBoardService();
    List<Match> listOfCompletedMatches = createMatchesScoreItemsList(GAME_STATUS_WAITING);
    listOfCompletedMatches.forEach(service::addItems);

    // when
    List<Match> result = service.getMatchesWithHighestScores();

    // then
    assertNotNull(result);
    // for (var iter : result) {
    //   System.out.println(iter.getTeamsAndScoreInformation());
    // }
  }

  @Test
  void getMatchesBySumOfScores() {
    // given
    var service = new MatchScoreBoardService();
    List<Match> listOfCompletedMatches = createMatchesScoreItemsList(GAME_STATUS_WAITING);
    listOfCompletedMatches.forEach(service::addItems);

    // when
    List<Match> result = service.getMatchesBySumOfScores();

    // then
    assertNotNull(result);
    // for (var item : result) {
    //   System.out.println(item.getTeamsAndScoreInformation());
    // }
  }

  private static List<Match> createMatchesScoreItemsList(MatchStatus status) {
    return CreateTestEntities.createGameScoreItemsList(status);
  }
}