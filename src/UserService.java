import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<String,User> userMap = new HashMap<>();
    private User currentUser = null;

    public boolean registerUser(String userName, String password, String fullName, String phoneNo){
        if(userMap.containsKey(userName)){
            System.out.println("User name is already taken, Please choose another!");
            return false;
        }
        User user = new User(userName, password, fullName, phoneNo);
        userMap.put(userName, user);
        System.out.println("Registration Successful!");
        return true;
    }

    public boolean login(String userName, String password){
        if(!userMap.containsKey(userName)){
            System.out.println("No user found!");
            return false;
        }

        User user = userMap.get(userName);
        if(!user.getPassword().equals(password)){
            System.out.println("Incorrect password");
            return false;
        }

        currentUser = user;
        System.out.println("Welcome : "+currentUser.getFullName()+"!");
        return true;
    }

    public void logOutUser(){
        if(currentUser!=null){
            System.out.println("Logged Out "+currentUser.getFullName());
        }
        currentUser = null;
    }

    public User getCurrentUser(){
        return currentUser;
    }

    public boolean isLoggedIn(){
        return currentUser!=null;
    }
}