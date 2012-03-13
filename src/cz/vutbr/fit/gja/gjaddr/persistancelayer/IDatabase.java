package cz.vutbr.fit.gja.gjaddr.persistancelayer;

import cz.vutbr.fit.gja.gjaddr.persistancelayer.tables.Contact;
import cz.vutbr.fit.gja.gjaddr.persistancelayer.tables.Group;
import java.util.List;

/**
 * Interface for persistence layer.
 *
 * @author Bc. Radek Gajdušek <xgajdu07@stud.fit.vutbr.cz>
 */
public interface IDatabase {
	
	// CONTACTS
	List<Contact> getAllContacts(); 
	List<Contact> getSpecificContacts(List<Integer> id);		
	List<Contact> getAllContactsFromGroup(int groupId);
	//void saveContacts(List<Contact> contacts);		
	
	// GROUPS
	List<Group> getAllGroups();	
	List<Group> addNewGroup(String name);	
	List<Group> updateGroup(Group group);	
	List<Group> removeGroups(List<Integer> groupIds);
}