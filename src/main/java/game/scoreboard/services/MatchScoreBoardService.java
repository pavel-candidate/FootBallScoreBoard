package game.scoreboard.services;

import game.scoreboard.domain.Match;
import game.scoreboard.domain.Team;
import game.scoreboard.enums.MatchStatus;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MatchScoreBoardService {

  private final static Comparator<Match> finalComparatorForMatchesWithRoolsFromTask = getComparatorForSortingMatches();
  private final List<Match> fakeDataStore = new ArrayList<>();

  public Match startANewMatch(Team homeTeam, Team awayTeam) {
    var toReturn = Match.builder()
        .homeTeam(homeTeam)
        .awayTeam(awayTeam)
        .build();
    startANewMatch(toReturn);
    return toReturn;
  }

  public Match startANewMatch(Match match) {
    fakeDataStore.add(match);
    return match;
  }

  public void addItems(Match... items) {
    for (Match item : items) {
      this.fakeDataStore.add(item);
    }
  }

  public List<Match> getMatchesScoreItems() {
    return fakeDataStore.stream()
        .filter(i -> i.getStatus() == MatchStatus.WAITING)
        .collect(Collectors.toList());
  }

  /**
   *
   * @return returns the matches sorting (desc) by Highest Scores
   */
  public List<Match> getMatchesWithHighestScores() {
    return fakeDataStore.stream()
        .filter(i -> i.getStatus() == MatchStatus.WAITING)
        .sorted((o1, o2) -> Math.max(o2.getHomeTeamScore().getScore(), o2.getAwayTeamScore().getScore()) -
            Math.max(o1.getHomeTeamScore().getScore(), o1.getAwayTeamScore().getScore()))
        .collect(Collectors.toList());
  }

  /**
   *
   * @return returns sorted collection by max value of score (desc) + sorting by date & time
   */
  public List<Match> getMatchesBySumOfScores() {
    return fakeDataStore.stream()
        .filter(i -> i.getStatus() == MatchStatus.WAITING)
        .sorted(finalComparatorForMatchesWithRoolsFromTask)
        .collect(Collectors.toList());
  }

  private static Comparator<Match> getComparatorForSortingMatches() {

    Comparator<Match> compareForScore = (o1, o2) -> {
      int result = o2.getHomeTeamScore().getScore() + o2.getAwayTeamScore().getScore() -
          (o1.getHomeTeamScore().getScore() + o1.getAwayTeamScore().getScore());
      // I did it here because comparator doesn't sort duplicate values
      return result == 0 ? -1: result;
    };

    return compareForScore
        .thenComparing(Match::getDateOfGame)
        .thenComparing(Match::getTimeOfGame);
  }

  public Optional<Match> findMatch(Match param) {
    return fakeDataStore.stream().filter(ds ->
            predicateForMatchFinding(param, ds))
        .findAny();
  }

  /**
   *
   * @param param
   * @return true if at least one element was removed
   */
  public boolean removeMatch(Match param) {
    var toReturn = fakeDataStore.removeIf(ds -> predicateForMatchFinding(param, ds));
    if (toReturn) {
      System.out.println(MessageFormat.format("The match {0} was deleted",
          param.getTeamsAndScoreInformation()));
    } else {
      System.out.println(MessageFormat.format("Cannot find match {0}",
          param.getTeamsAndScoreInformation()));
    }
    return toReturn;
  }

  private static boolean predicateForMatchFinding(Match param, Match ds) {
    return ds.getHomeTeam().getName().equals(param.getHomeTeam().getName()) &&
        ds.getAwayTeam().getName().equals(param.getAwayTeam().getName());
  }

  public int sizeOfDS() {
    return fakeDataStore.size();
  }
}
