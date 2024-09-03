module boardgame.boardgameproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens boardgame to javafx.fxml;
    exports boardgame;
}