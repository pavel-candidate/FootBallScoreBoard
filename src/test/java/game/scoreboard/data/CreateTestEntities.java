package game.scoreboard.data;

import game.scoreboard.domain.Match;
import game.scoreboard.domain.Team;
import game.scoreboard.enums.MatchStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Here we have several methods for creation values for unit testing.
 */
public class CreateTestEntities {
  private CreateTestEntities() {}

  public static Team createHomeTeamForTest() {
    return createTeamForTest("Home team for test");
  }

  public static Team createAwayTeamForTest() {
    return createTeamForTest("Away team for test");
  }

  public static Team createTeamForTest(String name) {
    return new Team(name);
  }

  public static Match createGameForTest(String homeTeamName, String awayTeamName) {
    return Match.builder()
        .homeTeam(createTeamForTest(homeTeamName))
        .awayTeam(createTeamForTest(awayTeamName))
        .build();
  }


  public static Match createSpainPortugalMatch() {
    Match toReturn = createGameForTest("Spain", "Portugal");
    toReturn.updateScore(10, 4);
    return toReturn;
  }


  public static List<Match> createGameScoreItemsList(MatchStatus status) {
    List<Match> toReturn = List.of(createMexicoCanadaMatch(),
        createSpainBrazilMatch(),
        createGermanyFranceMatch(),
        createUruguayItalyMatch(),
        createArgentinaAustraliaMatch()
    );
    if (status == MatchStatus.FINISHED) {
      // return toReturn.stream().map(s -> {
      //       s.matchOver();
      //       return s;
      //     })
      //     .collect(Collectors.toList());
      // this will be shorter :
      return toReturn.stream().peek(Match::matchOver)
          .collect(Collectors.toList());
    }
    return toReturn;
  }

  private static Match createSpainBrazilMatch() {
    Match toReturn = createGameForTest("Spain", "Brazil");
    toReturn.updateScore(10, 2);
    return toReturn;
  }

  private static Match createMexicoCanadaMatch() {
    Match toReturn = createGameForTest("Mexico", "Canada");
    toReturn.updateScore(1, 5);
    return toReturn;
  }

  private static Match createGermanyFranceMatch() {
    Match toReturn = createGameForTest("Germany", "France");
    toReturn.updateScore(2, 2);
    return toReturn;
  }

  private static Match createUruguayItalyMatch() {
    Match toReturn = createGameForTest("Uruguay", "Italy");
    toReturn.updateScore(6, 6);
    return toReturn;
  }

  private static Match createArgentinaAustraliaMatch() {
    Match toReturn = createGameForTest("Argentina", "Australia");
    toReturn.updateScore(3, 1);
    return toReturn;
  }
}
