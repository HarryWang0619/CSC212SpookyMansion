package edu.smith.cs.csc212.spooky;


public class SecretExit extends Exit{
    

    /**
	 * is hidden!
	 */
    private boolean hidden;

    /**
	 * Create a new Exit. But it's SECRET!
	 * @param target - where it goes.
	 * @param description - how it looks.
	 */
    public SecretExit(String target, String description) {
		super(target, description); //call super EXIT!
		this.hidden = true;
    }
    
    /**
	 * when type search, search(), then not hidden.
	 */
    @Override
    public void search() {
		this.hidden = false;
        //dont work for some reason wwww.
	}
	
	@Override
	public boolean isSecret() {
		return this.hidden;
	}
	
	@Override
	public boolean hidden() {
		return this.hidden;
		
	}
}