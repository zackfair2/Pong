package model;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Set;


public class MyPongModel implements PongModel {

	private Dimension window;
	private Point pongBall;
	private String spelare1;
	private String spelare2;
	private int player1score;
	private int player2score;
	private double verticalvector;
	private double horizonvector;
	private int leftHeight;
    private int rightHeight;
    private int rightPos;
    private int leftPos;
	private double ballspeed;
	private String message;
	
	
	
	public MyPongModel(String string1, String string2){
		this.window = new Dimension(1200,800);
		this.pongBall = new Point(600,400);
		this.spelare1 = string1;
		this.spelare2 = string2;
		this.player1score = 0;
		this.player2score = 0;
		this.verticalvector = -5;
		this.horizonvector = 0;
		this.leftPos = 400;
		this.rightPos = 400;
		this.leftHeight = 200;
		this.rightHeight = 200;
		this.ballspeed = 1;
		this.message = "";
		}
	public void compute(Set<Input> input, long delta_t) {
		// TODO Auto-generated method stub
		
	}

	//TODO Change this//
private void moveBar(Set<Input> input){
	for(Input i : input){
		switch(i.key){
			case RIGHT:
				switch(i.dir){
				case UP:
					if(this.rightPos - (this.rightHeight/2) <= 0){
							break;
					}
					else{
					this.rightPos = this.rightPos - 7;
				break;
					}
			case DOWN:
				if(this.rightPos + (this.rightHeight/2) >= this.window.getHeight()){
					break;
				}
				else{
			this.rightPos = this.rightPos + 7;
			break;
				}
			}
			break;
		case LEFT:
			switch(i.dir){
			case UP:
					if(this.leftPos - (this.leftHeight/2) <= 0){
						break;
					}
					else{
						this.leftPos = this.leftPos - 7;
						break;
						}
				case DOWN:
						if(this.leftPos + (this.leftHeight/2) >= this.window.getHeight()){
								break;
						}
						else{
					this.leftPos = this.leftPos + 7;
					break;
						}
			}
			break;
		}
	}
}

public void toHigh(){
	if(this.pongBall.getY() > this.window.getHeight())
		this.verticalvector = this.verticalvector - (2 * this.verticalvector);
}

public void toLow(){
	if(this.pongBall.getY()< this.window.getHeight())
	    this.verticalvector = this.verticalvector - (2 * this.verticalvector);
}
//TODO ändra lite i den här//
private void changeAngle(){
	if(hitLeftBar()){
		if(this.leftPos < this.pongBall.getY()){
			this.verticalvector--;
				}
		else{
		    this.verticalvector++;
			}
		}
	else if(hitRightBar()){
		if(this.rightPos < this.pongBall.getY()){
			this.verticalvector--;
		}
		else{
			this.verticalvector++;
		}
	}
}


public boolean hitRightBar(){
	if((this.rightPos+(this.rightHeight/2))>= this.pongBall.getY() && (this.rightPos-(this.rightHeight/2)) <= this.pongBall.getY())
		return true;
	else{
		return false;
	}
	
}
 
public boolean hitLeftBar(){
	if((this.leftPos+(this.leftHeight/2))>= this.pongBall.getY() && (this.leftPos-(this.leftHeight/2)) <= this.pongBall.getY())
		return true;
	else{
		return false;
	}
}
	public int getBarPos(BarKey k) {
		switch(k){
		case RIGHT:
			return this.rightPos;
		case LEFT:
			return this.leftPos;
		default:
			return 0;
		}
	}

	@Override
	public int getBarHeight(BarKey k) {
	    switch(k){
		case RIGHT:
			return this.rightHeight;
		case LEFT:
			return this.leftHeight;
		default:
			return 0;
	    }
	}

	@Override
	public Point getBallPos() {
		return this.pongBall;
	}

	@Override
	public String getMessage() {
		
		return this.message;
	}

	@Override
	public String getScore(BarKey k) {
	    switch(k){
		case RIGHT:
			return Integer.toString(this.player1score);
		case LEFT:
			return Integer.toString(this.player2score);
		default:
		return "0";
	    }
	}

	@Override
	public Dimension getFieldSize() {
		return this.window;
	}

}
