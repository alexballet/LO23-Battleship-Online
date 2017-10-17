/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Interface for the Ihm Table team
 * @author corentinhembise
 */
public interface GuiTableInterface {
    /**
     * Displays the window where the player will place their ships.
     */
    public void displayPlacementPhase();
    
    /**
     * Notifies the IHM Table that the opponent is ready so that it can adapt its views.
     * \param myTurn 
     */
    public void opponentReady(Boolean myTurn);
}
