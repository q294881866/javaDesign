package command;

public class ShoppingCommand extends Command {

	@Override
	public void execute() {
		System.out.println("go shopping");
	}

	@Override
	public void unDo() {
		System.out.println("undo shopping");
	}

}
