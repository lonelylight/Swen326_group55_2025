package interface;
public class Display{
    public void showVisualAlert(String message){
        System.out.println("[Visual]" + message);
    }

    public void playSound(String tone){
        System.out.println("[Sound] Playing tone:" + tone);
    }


}