import java.io.Serializable;
import java.util.TreeMap;

public class PhoneBook implements Serializable {

	private TreeMap<String, String> phoneBook = new TreeMap<String, String>(); 

	public void setPhoneBook(PhoneBook phoneBook) {
		this.phoneBook = phoneBook.getPhoneBook();
	}

	public TreeMap<String, String> getPhoneBook() {
		return phoneBook;
	}

	public void add(String name, String phoneNumber) throws DuplicateContactException, InvalidPhoneNumException {
		if (phoneBook.containsKey(name)) {
			throw new DuplicateContactException("A contact with the name " + name + " already exists");
		} else if (!validatePhoneNum(phoneNumber)) {
			throw new InvalidPhoneNumException("Phone numbers must contain exactly 10 digits.");
		}
		phoneBook.put(name, phoneNumber);
	}

	public void update(String name, String phoneNumber) throws ContactNotFoundException, InvalidPhoneNumException {
		if (!phoneBook.containsKey(name)) {
			throw new ContactNotFoundException("A contact with the name " + name + " does not exist");
		} else if (!validatePhoneNum(phoneNumber)) {
			throw new InvalidPhoneNumException("Phone numbers must contain exactly 10 digits.");
		}
		phoneBook.put(name, phoneNumber);
	}

	public void delete(String name) throws ContactNotFoundException {
		if (!phoneBook.containsKey(name)) {
			throw new ContactNotFoundException("A contact with the name " + name + " does not exist");
		}
		phoneBook.remove(name);
	}

	public TreeMap<String, String> search(String name) {
		if (!phoneBook.containsKey(name)) {
			return new TreeMap<String,String>();
		}
		return new TreeMap<String, String>() {{put(name, phoneBook.get(name));}};
	}

	private boolean validatePhoneNum(String phoneNumber) {
		if (phoneNumber.length() == 10 && phoneNumber.matches("^[0-9]*$")) {
			return true;
		}
		return false;
	}
}