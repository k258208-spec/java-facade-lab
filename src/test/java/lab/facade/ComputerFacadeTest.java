package lab.facade;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComputerFacadeTest {

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
            for (int i = 0; i < size; i++) data[i] = (byte) 0xAA; // predictable pattern
            return data;
        }
    }

    @Test
    void start_shouldInvokeHardDriveRead() {
        StubHardDrive stub = new StubHardDrive();
        ComputerFacade facade = new ComputerFacade(new Cpu(), new Memory(), stub);

        // Run start; expected to call hd.read(...)
        facade.start();

        assertTrue(stub.readCalled, "ComputerFacade.start() should call IHardDrive.read()");
        assertEquals(64, stub.lastSize, "Should read 64 bytes for boot sector in sample code");
    }
}
