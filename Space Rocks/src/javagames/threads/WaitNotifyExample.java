package javagames.threads;

public class WaitNotifyExample implements FakeHardwareListener {
    public void runTest() throws Exception {
        FakeHardware hardware = new FakeHardware("name");
        hardware.addListener(this);

        synchronized (this) {
            hardware.turnOn();
            while (!hardware.isOn()) {
                wait();
            }
        }
        System.out.println("Hardware is on!");
        synchronized (this) {
            hardware.start(1000, 4);
            while (!hardware.isRunning()) {
                wait();
            }
        }
        System.out.println("Hardware is running");
        synchronized (this) {
            while (hardware.isRunning()) {
                wait();
            }
        }
        System.out.println("Hardware has stopped!");
        synchronized (this) {
            hardware.turnOff();
            while (hardware.isOn()) {
                wait();
            }
        }
    }

    @Override
    public void event(FakeHardware source, FakeHardware.FakeHardwareEvent event) {
        System.out.println("Got Event:" + event);
        notifyAll();
    }

    public static void main(String[] args) throws Exception {
        new WaitNotifyExample().runTest();
    }
}
