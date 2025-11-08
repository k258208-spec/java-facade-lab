package lab.facade;
public class Cpu {
	public void freeze() { System.out.println(System.currentTimeMillis() + " | CPU: freeze"); }
	public void jump(long position) { System.out.println(System.currentTimeMillis() + " | CPU: jump to " + position); }
	public void execute() { System.out.println(System.currentTimeMillis() + " | CPU: execute"); }
}