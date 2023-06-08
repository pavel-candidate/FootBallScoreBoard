package game.scoreboard.services;

import game.scoreboard.domain.Game;
import game.scoreboard.enums.GameStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameScoreBoardService {

  private final List<Game> fakeDataStore = new ArrayList<>();

  public void addItems(Game... items) {
    for (Game item : items) {
      this.fakeDataStore.add(item);
    }
  }

  public List<Game> getGameScoreItems() {
    return fakeDataStore.stream()
        .filter(i -> i.getStatus() == GameStatus.WAITING)
        .collect(Collectors.toList());
  }

  /**
   *
   * @return It will return the game with Highest Score
   */
  public List<Game> getGamesWithHighestScores() {
    return fakeDataStore.stream()
        .filter(i -> i.getStatus() == GameStatus.WAITING)
        .sorted((o1, o2) -> Math.max(o2.getHomeTeamScore().getScore(), o2.getAwayTeamScore().getScore()) -
            Math.max(o1.getHomeTeamScore().getScore(), o1.getAwayTeamScore().getScore()))
        .collect(Collectors.toList());
  }

  public List<Game> getGamesBySumOfScores() {
    return fakeDataStore.stream()
        .filter(i -> i.getStatus() == GameStatus.WAITING)
        .sorted((o1, o2) -> (o2.getHomeTeamScore().getScore() + o2.getAwayTeamScore().getScore()) -
            (o1.getHomeTeamScore().getScore() + o1.getAwayTeamScore().getScore()))
        .collect(Collectors.toList());
  }


}
