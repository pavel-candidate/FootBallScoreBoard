package game.scoreboard.domain;

import game.scoreboard.enums.MatchStatus;
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
public class Match implements ScoreTeamsInformation {

  private final Team homeTeam;
  private final Score homeTeamScore;
  private final Team awayTeam;
  private final Score awayTeamScore;
  private MatchStatus status;
  private LocalDate dateOfGame;
  private LocalTime timeOfGame;

  @Builder
  public Match(Team homeTeam, Team awayTeam,
               LocalDate dateOfGame, LocalTime timeOfGame) {

    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.dateOfGame = dateOfGame;
    this.timeOfGame = timeOfGame;

    Objects.requireNonNull(this.homeTeam, "Home Team should not be null");
    Objects.requireNonNull(this.awayTeam, "Away Team should not be null");

    this.status = MatchStatus.WAITING;
    this.homeTeamScore = new Score();
    this.awayTeamScore = new Score();
  }

  public Score incrementHomeTeamScore() {
    if (this.status == MatchStatus.WAITING) {
      this.homeTeamScore.increment();
    }
    return getHomeTeamScore();
  }

  public Score incrementAwayTeamScore() {
    if (this.status == MatchStatus.WAITING) {
      this.awayTeamScore.increment();
    }
    return getAwayTeamScore();
  }

  public void updateScore(int homeTeamScore, int awayTeamScore) {
    updateHomeTeamScore(homeTeamScore);
    updateAwayTeamScore(awayTeamScore);
  }

  public void updateHomeTeamScore(int input) {
    if (this.status == MatchStatus.WAITING) {
      this.homeTeamScore.setScore(input);
    }
  }

  public void updateAwayTeamScore(int input) {
    if (this.status == MatchStatus.WAITING) {
      this.awayTeamScore.setScore(input);
    }
  }

  public void matchOver() {
    if (this.status == MatchStatus.WAITING) {
      this.status = MatchStatus.FINISHED;
    }
  }

  @Override
  public String getTeamsAndScoreInformation() {
    return MessageFormat.format("{0} {1} - {2} {3}",
        homeTeam.getName(), homeTeamScore.getScore(),
        awayTeam.getName(), awayTeamScore.getScore());
  }

}
