# Football World Cup Score Board
Small project with demonstration of JUnit 5 


The boards support the following operations:
1. **Start a game**. When a game starts, it should capture (being initial score 0-0)
( Home team and Away Team)

2. **Finish a game**. It will remove a match from the scoreboard. Game.gameOver

3. **Update score**. There are 2 methods in the Game object: updateHomeTeamScore and updateAwayTeamScore.

I also did incrementHomeTeamScore and incrementAwayTeamScore methods. They increment the score by 1.

4. **Get a summary of games by total score**. GameScoreBoardService#getGamesBySumOfScores

This example demonstrates JUnit 5 testing:

- Usual unit testing
- Testing exceptions
- Parameterized testing.
