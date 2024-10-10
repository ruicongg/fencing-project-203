package org.fencing.demo.player;

import java.util.List;


public interface PlayerService {
    List<Player> listPlayers();
    Player getPlayer(Long id);
    Player addPlayer(Player player);
    Player updatePlayer(Long id, Player player);
    void deletePlayer(Long id);
}
