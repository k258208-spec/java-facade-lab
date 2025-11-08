package lab.facade;

public class Hdd implements IHardDrive {
    @Override
    public byte[] read(long lba, int size) {
        System.out.println(System.currentTimeMillis() + " | HDD: reading " + size + " bytes from LBA " + lba);
        byte[] data = new byte[size];
        for (int i = 0; i < size; i++) data[i] = (byte) (i % 256);
        return data;
    }
}
