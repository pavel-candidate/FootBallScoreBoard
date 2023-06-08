package game.scoreboard.services;

import game.scoreboard.data.CreateTestEntities;
import game.scoreboard.domain.Game;
import game.scoreboard.enums.GameStatus;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameScoreBoardServiceTest {
  private final static GameStatus GAME_STATUS_WAITING = GameStatus.WAITING;

  @Test
  void getGameScoreItems() {
    // given
    var service = new GameScoreBoardService();
    List<Game> listOfCompletedGames = createGameScoreItemsList(GAME_STATUS_WAITING);
    listOfCompletedGames.forEach(service::addItems);

    // when
    List<Game> result = service.getGameScoreItems();

    // then
    assertNotNull(result);

  }

  @Test
  void gatGamesWithHighestScores() {
    // given
    var service = new GameScoreBoardService();
    List<Game> listOfCompletedGames = createGameScoreItemsList(GAME_STATUS_WAITING);
    listOfCompletedGames.forEach(service::addItems);

    // when
    List<Game> result = service.getGamesWithHighestScores();

    // then
    assertNotNull(result);

  }

  @Test
  void gatGamesBySumOfScores() {
    // given
    var service = new GameScoreBoardService();
    List<Game> listOfCompletedGames = createGameScoreItemsList(GAME_STATUS_WAITING);
    listOfCompletedGames.forEach(service::addItems);

    // when
    List<Game> result = service.getGamesBySumOfScores();

    // then
    assertNotNull(result);

  }


  private static List<Game> createGameScoreItemsList(GameStatus status) {
    return CreateTestEntities.createGameScoreItemsList(status);
  }
}