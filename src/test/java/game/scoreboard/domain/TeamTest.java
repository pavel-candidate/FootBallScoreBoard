package game.scoreboard.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

  @Test
  void exceptionBecauseOfNullParameter() {
    var exception = assertThrows(NullPointerException.class,
        () -> new Team(null));
  }


}