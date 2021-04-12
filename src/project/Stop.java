package project;

public class Stop extends Thread {
    public Stop() {

    }
    @Override
    public void run() {
        System.out.println("STOPPED");
    }
}
