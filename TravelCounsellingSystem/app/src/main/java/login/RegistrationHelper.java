package login;
import java.util.Vector;
public class RegistrationHelper {

    private Vector <Character> specialCharacters=new Vector<>();

    public RegistrationHelper() {
        specialCharacters.addElement('!');
        specialCharacters.addElement('"');
        specialCharacters.addElement('?');
        specialCharacters.addElement('$');
        specialCharacters.addElement('%');
        specialCharacters.addElement('^');
        specialCharacters.addElement('&');
        specialCharacters.addElement('*');
        specialCharacters.addElement('(');
        specialCharacters.addElement(')');
    }

    public boolean checkUsernamePassword(String password) {
        /**
         * Check username is not null
         */
       // if (username==null) return(false);
        /**
         * Check password is not null
         */
       // if (password==null) return(false);
        /**
         * Check username is long enough
         */
        //if (username.length()<8) {
        //    return(false);
        //}
        /**
         * Check username is not too long
         */
      //  if (username.length()>12) {
       //     return(false);
       // }
        /**
         * Check username starts with an alphabetic character
         */
       // if (!Character.isAlphabetic(username.charAt(0))) {
       //     return(false);
      //  }
        /**
         * Check password is long enough
         */
        if (password.length()<8) {
            return(false);
        }
        /**
         * Now look for a lower case char in the password, no lower case then check fails
         */
        boolean isLower=false;
        for (int idx=0;idx<password.length();idx++) {
            if (Character.isLowerCase(password.charAt(idx))) {
                isLower=true;
            }
        }
        if (!isLower) return(false);
        /**
         * Now look for an upper case char in the password, no upper case then check fails
         */
        boolean isUpper=false;
        for (int idx=0;idx<password.length();idx++) {
            if (Character.isUpperCase(password.charAt(idx))) {
                isUpper=true;
            }
        }
        if (!isUpper) return(false);
        /**
         * Now look for an digit in the password, no digit then the test fails
         */
        boolean isDigit=false;
        for (int idx=0;idx<password.length();idx++) {
            if (Character.isDigit(password.charAt(idx))) {
                isDigit=true;
            }
        }
        if (!isDigit) {
            return(false);
        }
        /*
         * Now look for a special char in the password, no special char then test fails
         */
        boolean gotSpecial=false;
        for (int idx=0;idx<password.length();idx++) {
            Character character=password.charAt(idx);
            if (this.specialCharacters.contains(character)) {
                gotSpecial=true;
                break;
            }
        }
        return gotSpecial;
    }
}
