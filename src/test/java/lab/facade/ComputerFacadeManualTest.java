package lab.facade;

/**
 * Simple manual test runner (no JUnit) to verify ComputerFacade.start() calls IHardDrive.read().
 * Exits with code 0 on success, 1 on failure.
 */
public class ComputerFacadeManualTest {
    static class StubHardDrive implements IHardDrive {
        boolean readCalled = false;
        long lastLba = -1;
        int lastSize = -1;

        @Override
        public byte[] read(long lba, int size) {
            this.readCalled = true;
            this.lastLba = lba;
            this.lastSize = size;
            byte[] data = new byte[size];
            for (int i = 0; i < size; i++) data[i] = (byte) 0x55;
            return data;
        }
    }

    public static void main(String[] args) {
        StubHardDrive stub = new StubHardDrive();
        ComputerFacade facade = new ComputerFacade(new Cpu(), new Memory(), stub);

        try {
            facade.start();
        } catch (Exception e) {
            System.err.println("ERROR: start() threw an exception: " + e);
            e.printStackTrace();
            System.exit(1);
        }

        if (!stub.readCalled) {
            System.err.println("FAIL: IHardDrive.read was not called by ComputerFacade.start()");
            System.exit(1);
        }
        if (stub.lastSize != 64) {
            System.err.println("FAIL: expected read size 64 but was " + stub.lastSize);
            System.exit(1);
        }

        System.out.println("PASS: ComputerFacade.start() invoked IHardDrive.read() as expected");
        System.exit(0);
    }
}
