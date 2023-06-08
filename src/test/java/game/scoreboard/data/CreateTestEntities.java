package game.scoreboard.data;

import game.scoreboard.domain.Game;
import game.scoreboard.domain.Team;
import game.scoreboard.enums.GameStatus;

import java.util.List;
import java.util.stream.Collectors;

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

  public static Game createGameForTest(String homeTeamName, String awayTeamName) {
    return Game.builder()
        .homeTeam(createTeamForTest(homeTeamName))
        .awayTeam(createTeamForTest(awayTeamName))
        .build();
  }


  public static List<Game> createGameScoreItemsList(GameStatus status) {
    List<Game> toReturn = List.of(createMexicoCanadaGame(),
        createSpainBrazilGame(),
        createGermanyFranceGame(),
        createUruguayItalyGame(),
        createArgentinaAustraliaGame()
    );
    if (status == GameStatus.FINISHED) {
      return toReturn.stream().map(s -> {
            s.gameOver();
            return s;
          })
          .collect(Collectors.toList());
    }
    return toReturn;
  }

  private static Game createMexicoCanadaGame() {
    Game toReturn = createGameForTest("Mexico", "Canada");
    toReturn.updateAwayTeamScore(5);
    return toReturn;
  }

  public static Game createSpainBrazilGame() {
    Game toReturn = createGameForTest("Spain", "Brazil");
    toReturn.updateHomeTeamScore(10);
    toReturn.updateAwayTeamScore(2);
    return toReturn;
  }

  private static Game createGermanyFranceGame() {
    Game toReturn = createGameForTest("Germany", "France");
    toReturn.updateHomeTeamScore(2);
    toReturn.updateAwayTeamScore(2);
    return toReturn;
  }

  private static Game createUruguayItalyGame() {
    Game toReturn = createGameForTest("Uruguay", "Italy");
    toReturn.updateHomeTeamScore(6);
    toReturn.updateAwayTeamScore(6);
    return toReturn;
  }

  private static Game createArgentinaAustraliaGame() {
    Game toReturn = createGameForTest("Argentina", "Australia");
    toReturn.updateHomeTeamScore(3);
    toReturn.updateAwayTeamScore(1);
    return toReturn;
  }


}
