# Football World Cup Score Board
Small project with demonstration of JUnit 5 


The implementation supports the following operations:
1. **Start a game**. When a game starts, it should capture (being initial score 0-0)
( Home team and Away Team)
   `MatchScoreBoardService.startANewMatch`

2. **Update score**. There are 2 methods in the Game object: updateHomeTeamScore and updateAwayTeamScore.
   `Match.updateScore`

I also did incrementHomeTeamScore and incrementAwayTeamScore methods. They increment the score by 1 (one).


3. **Finish a match**. It will remove a match from the scoreboard: `Match.matchOver`

4. **Get a summary of games by total score**. `MatchScoreBoardService#getMatchesBySumOfScores`

I added: `MatchScoreBoardService.findMatch` for finding
and `MatchScoreBoardService.removeMatch` for removing game from the datastore.

This example demonstrates JUnit 5 testing:

- Usual unit testing
- Testing exceptions
- Parameterized testing.

It is possible to run all tests from the command line: `mvn clean test`
