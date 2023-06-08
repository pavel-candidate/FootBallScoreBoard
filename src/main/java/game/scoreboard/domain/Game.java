package game.scoreboard.domain;

import game.scoreboard.enums.GameStatus;
import game.scoreboard.interfaces.ScoreTeamsInformation;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Getter
@EqualsAndHashCode
/**
 * This class uses a Builder pattern
 */
public class Game implements ScoreTeamsInformation {



  private final Team homeTeam;
  private final Score homeTeamScore;

  private final Team awayTeam;
  private final Score awayTeamScore;
  private GameStatus status;
  private LocalDate dateOfGame;
  private LocalTime timeOfGame;

  @Builder
  public Game(Team homeTeam, Team awayTeam,
              LocalDate dateOfGame, LocalTime timeOfGame) {

    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.dateOfGame = dateOfGame;
    this.timeOfGame = timeOfGame;

    Objects.requireNonNull(this.homeTeam, "Home Team should not be null");
    Objects.requireNonNull(this.awayTeam, "Away Team should not be null");

    this.status = GameStatus.WAITING;
    this.homeTeamScore = new Score();
    this.awayTeamScore = new Score();
  }

  public Score incrementHomeTeamScore() {
    if (this.status == GameStatus.WAITING) {
      this.homeTeamScore.increment();
    }
    return getHomeTeamScore();
  }

  public Score incrementAwayTeamScore() {
    if (this.status == GameStatus.WAITING) {
      this.awayTeamScore.increment();
    }
    return getAwayTeamScore();
  }

  public void updateHomeTeamScore(int input) {
    if (this.status == GameStatus.WAITING) {
      this.homeTeamScore.setScore(input);
    }
  }

  public void updateAwayTeamScore(int input) {
    if (this.status == GameStatus.WAITING) {
      this.awayTeamScore.setScore(input);
    }
  }

  public void gameOver() {
    if (this.status == GameStatus.WAITING) {
      this.status = GameStatus.FINISHED;
    }
  }

  @Override
  public String getTeamsAndScoreInformation() {
    return MessageFormat.format("{0} {1} - {2} {3}",
        homeTeam.getName(), homeTeamScore.getScore(),
        awayTeam.getName(), awayTeamScore.getScore());
  }
}
