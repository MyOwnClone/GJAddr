package cz.vutbr.fit.gja.gjaddr.persistancelayer;

import cz.vutbr.fit.gja.gjaddr.persistancelayer.util.EventsEnum;
import cz.vutbr.fit.gja.gjaddr.persistancelayer.util.NameDays;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;

/**
 * Contact entry representation.
 *
 * @author Bc. Radek Gajdušek <xgajdu07@stud.fit.vutbr.cz>
 */
public class Contact implements Serializable {

  static private final long serialVersionUID = 6L;
  
  /**
   * Maximum lenght for method getFullName(). If is the string larger, returns 30
   * characters and three dots.
   */
  private final int MAX_DETAIL_LENGTH = 30;
  /**
   * Contact id - not visible for GUI, only for DB usage
   */
  int id = -1;
  /**
   * First name.
   */
  private String firstName;
  /**
   * Surname
   */
  private String surName;
  /**
   * Nickname
   */
  private String nickName;
  /**
   * Photo.
   */
  private ImageIcon Photo;
  /**
   * Note
   */
  private String note;
  /**
   * List of dates.
   */
  private List<Event> dates;
  /**
   * List of messengers.
   */
  private List<Messenger> messenger;
  /**
   * List or urls.
   */
  private List<Url> urls;
  /**
   * List of addresses.
   */
  private List<Address> adresses;
  /**
   * List of phone numbers.
   */
  private List<PhoneNumber> phoneNumbers;
  /**
   * List of emails.
   */
  private List<Email> emails;
  /**
   * List of customs.
   */
  private List<Custom> customs;

  /**
   * Get contact id
   *
   * @return
   */
  public int getId() {
    return id;
  }

  /**
   * Get dates.
   */
  public List<Event> getDates() {
    return dates;
  }

  /**
   * Set dates.
   */
  public void setDates(List<Event> dates) {
    this.dates = dates;
  }

  /**
   * Get messengers.
   */
  public List<Messenger> getMessenger() {
    return (messenger != null) ? messenger : new ArrayList<Messenger>();
  }

  /**
   * Set messengers.
   */
  public void setMessenger(List<Messenger> messenger) {
    this.messenger = messenger;
  }

  /**
   * Get urls.
   */
  public List<Url> getUrls() {
    return (urls != null) ? urls : new ArrayList<Url>();
  }

  /**
   * Set urls.
   */
  public void setUrls(List<Url> urls) {
    this.urls = urls;
  }

  /**
   * Get addresses.
   */
  public List<Address> getAdresses() {
    return (adresses != null) ? this.adresses : new ArrayList<Address>();
  }

  /**
   * Set addresses.
   */
  public void setAdresses(List<Address> adresses) {
    this.adresses = adresses;
  }

  /**
   * Get phone numbers.
   */
  public List<PhoneNumber> getPhoneNumbers() {
    return (phoneNumbers != null) ? this.phoneNumbers : new ArrayList<PhoneNumber>();
  }

  /**
   * Set phone numbers.
   */
  public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
  }

  /**
   * Get emails.
   */
  public List<Email> getEmails() {
    return (emails != null) ? emails : new ArrayList<Email>();
  }

  /**
   * Set emails.
   */
  public void setEmails(List<Email> emails) {
    this.emails = emails;
  }

  /**
   * Get customs.
   */
  public List<Custom> getCustoms() {
    return (customs != null) ? customs : new ArrayList<Custom>();
  }

  /**
   * Set customs.
   */
  public void setCustoms(List<Custom> customs) {
    this.customs = customs;
  }

  /**
   * Get first name.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Set first name.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Get nickname.
   */
  public String getNickName() {
    return nickName;
  }

  /**
   * Set nickname.
   */
  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  /**
   * Get surname.
   */
  public String getSurName() {
    return surName;
  }

  /**
   * Set surname.
   */
  public void setSurName(String surName) {
    this.surName = surName;
  }

  /**
   * Get note.
   */
  public String getNote() {
    return note;
  }
  
  /**
   * Set note.
   */
  public void setNote(String note) {
    this.note = note;
  }

  /**
   * Get photo.
   */
  public ImageIcon getPhoto() {
    return Photo;
  }

  /**
   * Set photo.
   */
  public void setPhoto(ImageIcon photo) {
    this.Photo = photo;
  }

  /**
   * Check if contact has any event today.
   *
   * @param type
   * @return
   */
  private boolean hasEvent(EventsEnum type) {
    if (this.getDates() == null) {
      return false;
    }
    for (Event e : this.getDates()) {
      if (e.getType().equals(type)) {
        if (e.getDate() == null) {
          return false;
        }
        // date today
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DAY_OF_YEAR, -1);
        // date within one month
        Calendar month = Calendar.getInstance();
        month.add(Calendar.DAY_OF_YEAR, 0);
        // birthday of contact
        Calendar bday = Calendar.getInstance();
        bday.setTime(e.getDate());
        bday.set(Calendar.YEAR, 2012);
        if (bday.after(today) && bday.before(month)) {
          return true;
        } else {
          return false;
        }
      }
    }
    return false;
  }

  /**
   * Return true if contact has birthday within one month.
   *
   * @return
   */
  public boolean hasBirthday() {
    return this.hasEvent(EventsEnum.BIRTHDAY);
  }

  /**
   * Return true if contact has name day within one month.
   *
   * @return
   */
  public boolean hasNameDay() {
    return this.hasEvent(EventsEnum.NAMEDAY);
  }

  /**
   * Return true if contact has celebration within one month.
   *
   * @return
   */
  public boolean hasCelebration() {
    return this.hasEvent(EventsEnum.CELEBRATION);
  }

  /**
   * Return true if contact has any other event within one month.
   *
   * @return
   */
  public boolean hasOtherEvent() {
    return this.hasEvent(EventsEnum.OTHER);
  }

  /**
   * Get event by type.
   *
   * @param type
   * @return
   */
  private Event getEvent(EventsEnum type) {
    if (this.getDates() == null) {
      return null;
    }
    for (Event e : this.getDates()) {
      if (e.getType().equals(type)) {
        return e;
      }
    }
    return null;
  }

  /**
   * Get contact birthday.
   *
   * @return
   */
  public Event getBirthday() {
    return this.getEvent(EventsEnum.BIRTHDAY);
  }

  /**
   * Get contact name day.
   *
   * @return
   */
  public Event getNameDay() {
    return this.getEvent(EventsEnum.NAMEDAY);
  }

  /**
   * Get contact celebration.
   *
   * @return
   */
  public Event getCelebration() {
    return this.getEvent(EventsEnum.CELEBRATION);
  }

  /**
   * Disable showing of some event.
   *
   * @param type
   */
  private void disableEventShowing(EventsEnum type) {
    if (this.dates == null) {
      return;
    }
    Event e = null;
    for (Event event : this.dates) {
      if (event.getType().equals(type)) {
        e = event;
      }
    }
    if (e == null) {
      return;
    }
    this.dates.remove(e);
    e.setYearShowingDisabled();
    this.dates.add(e);
  }

  /**
   * Disable showing of birthday.
   */
  public void disableBirthdayShowing() {
    this.disableEventShowing(EventsEnum.BIRTHDAY);
  }

  /**
   * Disable showing of name day.
   */
  public void disableNameDayShowing() {
    this.disableEventShowing(EventsEnum.NAMEDAY);
  }

  /**
   * Disable showing of celebration.
   */
  public void disableCelebrationShowing() {
    this.disableEventShowing(EventsEnum.CELEBRATION);
  }

  /**
   * Enable showing of event.
   *
   * @param type
   */
  private void enableEventShowing(EventsEnum type) {
    if (this.dates == null) {
      return;
    }
    Event e = null;
    for (Event event : this.dates) {
      if (event.getType().equals(type)) {
        e = event;
      }
    }
    if (e == null) {
      return;
    }
    this.dates.remove(e);
    e.setShowingEnabled();
    this.dates.add(e);
  }

  /**
   * Enable showing of birthday.
   */
  public void enableBirthdayShowing() {
    this.enableEventShowing(EventsEnum.BIRTHDAY);
  }

  /**
   * Enable name day showing.
   */
  public void enableNamedayShowing() {
    this.enableEventShowing(EventsEnum.NAMEDAY);
  }

  /**
   * Enable celebration showing.
   */
  public void enableCelebrationShowing() {
    this.enableEventShowing(EventsEnum.CELEBRATION);
  }

  /**
   * Set event to specified date.
   *
   * @param type
   * @param date
   */
  private void setEvent(EventsEnum type, Date date) {
    if (this.dates == null) {
      this.dates = new ArrayList<Event>();
    }
    Event e = null;
    for (Event event : this.dates) {
      if (event.getType().equals(type)) {
        e = event;
      }
    }
    if (e == null) {
      e = new Event(type, date);
      this.dates.add(e);
    } else {
      this.dates.remove(e);
      e.setDate(date);
      this.dates.add(e);
    }
  }

  /**
   * Set contact birthday.
   *
   * @param date
   */
  public void setBirthday(Date date) {
    this.setEvent(EventsEnum.BIRTHDAY, date);
  }

  /**
   * Set contact nameday.
   *
   * @param date
   */
  public void setNameDay(Date date) {
    this.setEvent(EventsEnum.NAMEDAY, date);
  }

  /**
   * Set contact celebration date.
   *
   * @param date
   */
  public void setCelebration(Date date) {
    this.setEvent(EventsEnum.CELEBRATION, date);
  }

  /**
   * Gets the fullname for user (no trimmed).
   *
   * @return
   */
  public String getFullName() {
    StringBuilder fullName = new StringBuilder();

    try {
      if ((this.firstName == null || this.firstName.isEmpty()) && (this.surName == null || this.surName.isEmpty())) {
        fullName.append(this.nickName);
      } else if (Settings.instance().isNameFirst()) {
        if (this.firstName != null && !this.firstName.isEmpty()) {
          fullName.append(this.firstName);
          fullName.append(" ");
        }
        fullName.append(this.surName);
      } else {
        if (this.surName != null && !this.surName.isEmpty()) {
          fullName.append(this.surName);
          fullName.append(" ");
        }
        fullName.append(this.firstName);
      }
    } catch (NullPointerException e) {
      return "";
    }

    return fullName.toString();
  }

  /**
   * Gets the trimmed fullname. Max name lenght is specified in constant
   * FULLNAME_MAX_LENGHT.
   *
   * @return
   */
  public String getFullNameForDetail() {
    String fullname = this.getFullName();

    if (fullname.length() > MAX_DETAIL_LENGTH) {
      return fullname.substring(0, MAX_DETAIL_LENGTH - 1) + " ...";
    }

    return fullname;
  }

  /**
   * Get all addresses string.
   *
   * @return string with addresses.
   */
  public String getAllAddresses() {
    String separator = "";
    StringBuilder addresses = new StringBuilder();

    try {
      for (Address address : this.adresses) {
        addresses.append(separator);
        addresses.append(address.getAddress());
        separator = "; ";
      }
    } catch (NullPointerException e) {
      return "";
    }

    return addresses.toString();
  }

  /**
   * Get all phones string.
   *
   * @return string with phones.
   */
  public String getAllPhones() {
    String separator = "";
    final StringBuilder phones = new StringBuilder();
    try {
      for (PhoneNumber phone : this.phoneNumbers) {
        phones.append(separator);
        phones.append(phone.getNumber());
        separator = ", ";
      }
    } catch (NullPointerException e) {
      return "";
    }

    return phones.toString();
  }

  /**
   * Get all emails string.
   *
   * @return string with emails.
   */
  public String getAllEmails() {
    String separator = "";
    final StringBuilder emails = new StringBuilder();

    try {
      for (Email email : this.emails) {
        emails.append(separator);
        emails.append(email.getEmail());
        separator = ", ";
      }
    } catch (NullPointerException e) {
      return "";
    }

    return emails.toString();
  }

  /**
   * Get all urls string.
   *
   * @return string with urls.
   */
  public String getAllUrls() {
    String separator = "";
    StringBuilder links = new StringBuilder();

    try {
      for (Url url : this.urls) {
        links.append(separator);
        links.append(url.getValue());
        separator = ", ";
      }
    } catch (NullPointerException e) {
      return "";
    }

    return links.toString();
  }
  
    /**
     * Get all messengers as single string.
     *
     * @return
     */
  public String getAllMessengers() {
      String separator = "";
      StringBuilder ims = new StringBuilder();

      try {
          for (Messenger m : this.messenger) {
              ims.append(separator);
              ims.append(m.getValue());
              separator = ", ";
          }
      } catch (NullPointerException e) {
          return "";
      }

      return ims.toString();
  }

  /**
    * Get all messengers as single string.
    *
    * @return
    */
  public String getAllMessengersWithType() {
        String separator = "";
        StringBuilder ims = new StringBuilder();

        try {
            for (Messenger m : this.messenger) {
                ims.append(separator);
                ims.append(m.getType());
                ims.append(": ");
                ims.append(m.getValue());
                separator = ", ";
            }
        } catch (NullPointerException e) {
            return "";
        }

        return ims.toString();
    }

  /**
   * Empty constructor.
   */
  public Contact() {
    this.dates = new ArrayList<Event>();
  }

  /**
   * Contact short constructor.
   *
   * @param firstName
   * @param surName
   * @param nickName
   * @param note
   */
  public Contact(String firstName, String surName, String nickName, String note) {
    this();
    this.firstName = firstName;
    this.surName = surName;
    this.nickName = nickName;
    this.note = note;
    Calendar nameDay = NameDays.getInstance().getNameDay(firstName);
    if (nameDay != null) {
      this.dates.add(new Event(EventsEnum.NAMEDAY, nameDay.getTime()));
    }
  }

  /**
   * Contact large constructor.
   *
   * @param firstName
   * @param surName
   * @param nickName
   * @param note
   * @param messenger
   * @param urls
   * @param adresses
   * @param phoneNumbers
   * @param emails
   * @param customs
   */
  public Contact(String firstName, String surName, String nickName,
          String note, List<Messenger> messenger, List<Url> urls, List<Address> adresses,
          List<PhoneNumber> phoneNumbers, List<Email> emails, List<Custom> customs) {
    this(firstName, surName, nickName, note);
    this.messenger = messenger;
    this.urls = urls;
    this.adresses = adresses;
    this.phoneNumbers = phoneNumbers;
    this.emails = emails;
    this.customs = customs;
  }

  /**
   * Custom equals implementation.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Contact other = (Contact) obj;
    if ((this.firstName == null) ? (other.firstName != null) : !this.firstName.equals(other.firstName)) {
      return false;
    }
    if ((this.surName == null) ? (other.surName != null) : !this.surName.equals(other.surName)) {
      return false;
    }
    if ((this.nickName == null) ? (other.nickName != null) : !this.nickName.equals(other.nickName)) {
      return false;
    }
    if (this.Photo != other.Photo && (this.Photo == null || !this.Photo.equals(other.Photo))) {
      return false;
    }
    if ((this.note == null) ? (other.note != null) : !this.note.equals(other.note)) {
      return false;
    }
    if (this.dates != other.dates && (this.dates == null || !this.dates.equals(other.dates))) {
      return false;
    }
    if (this.messenger != other.messenger && (this.messenger == null || !this.messenger.equals(other.messenger))) {
      return false;
    }
    if (this.urls != other.urls && (this.urls == null || !this.urls.equals(other.urls))) {
      return false;
    }
    if (this.adresses != other.adresses && (this.adresses == null || !this.adresses.equals(other.adresses))) {
      return false;
    }
    if (this.phoneNumbers != other.phoneNumbers && (this.phoneNumbers == null || !this.phoneNumbers.equals(other.phoneNumbers))) {
      return false;
    }
    if (this.emails != other.emails && (this.emails == null || !this.emails.equals(other.emails))) {
      return false;
    }
    if (this.customs != other.customs && (this.customs == null || !this.customs.equals(other.customs))) {
      return false;
    }
    return true;
  }

  /**
   * Custom toString method implementation.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    if (this.surName != null && !this.surName.isEmpty()) {
      sb.append(this.surName);
    }

    if (this.firstName != null && !this.firstName.isEmpty()) {
      if (sb.length() != 0) {
        sb.append(" ");
      }
      sb.append(this.firstName);
    }

    if (this.nickName != null && !this.nickName.isEmpty()) {
      if (sb.length() == 0) {
        sb.append(this.nickName);
      } else {
        sb.append(" (");
        sb.append(this.nickName);
        sb.append(")");
      }
    }

    return sb.toString();
  }
}
