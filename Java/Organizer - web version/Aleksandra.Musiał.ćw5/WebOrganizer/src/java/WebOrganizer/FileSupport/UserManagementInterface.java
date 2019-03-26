/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebOrganizer.FileSupport;

/**
 * Interface providing methosd for checking users in the users base and adding users
 * 
 * @author Aleksandra Musiał
 * @version 2.0
 */
public interface UserManagementInterface {
    
    boolean checkUser(String nick, String password);
    void addUser(String nick, String password);
}
