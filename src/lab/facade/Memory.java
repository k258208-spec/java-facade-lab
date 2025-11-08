package lab.facade;
public class Memory {
public void load(long position, byte[] data) {
	System.out.println(System.currentTimeMillis() + " | Memory: loading " + data.length + " bytes at " + position);
}
}