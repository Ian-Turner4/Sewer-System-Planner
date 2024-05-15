
public class Button {
	public int x, y, size;
	public String sym;
	
	Button(int x, int y, int size, String sym){
		this.x = x;
		this.y = y;
		this.size = size;
		this.sym = sym;
	}
	
	boolean mouseOver(int mousex, int mousey) {
		return ((mousex < this.x + (this.size))&&(mousex > this.x)&&(mousey < this.y + (this.size))&&(mousey > this.y)) ? (true):(false);
	}
}
