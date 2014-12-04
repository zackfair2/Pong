import java.awt.Dimension;
import java.awt.Point;
import java.util.Set;


public class implementedPongModel implements PongModel {

	private Dimension window;
	private Point pongBall;
	private string spelare1;
	private string spelare2;
	private int player1score;
	private int player2score;
	private double verticalvector;
	private double horizonvector;
	private int leftHeight;
	private int rightHeight;
	private double ballspeed;
	private String message;
	
	
	
	public implementedPongModel(String string1, String string2){
		this.field = new Dimension(1200,800);
		this.pongBall = new Point(600,400);
		this.player1 = string1;
		this.player2 = string2;
		this.leftScore = 0;
		this.rightScore = 0;
		this.dirX = -5;
		this.dirY = 0;
		this.leftPos = 400;
		this.rightPos = 400;
		this.leftsize = 200;
		this.rightsize = 200;
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
					if(rightPos - (rightHeight/2) <= 0){
							break;
					}
					else{
					rightPos = rightPos - 7;
				break;
					}
			case DOWN:
				if(rightPos + (rightHeight/2) >= field.getHeight()){
					break;
				}
				else{
			rightPos = rightPos + 7;
			break;
				}
			}
			break;
		case LEFT:
			switch(i.dir){
			case UP:
					if(leftPos - (leftHeight/2) <= 0){
						break;
					}
					else{
						leftPos = leftPos - 7;
						break;
						}
				case DOWN:
						if(leftPos + (leftHeight/2) >= field.getHeight()){
								break;
						}
						else{
					leftPos = leftPos + 7;
					break;
						}
			}
			break;
		}
	}
}

public void toHigh(){
	if(this.pongBall.getY() > this.window.getHeight())
		this.dirY = this.dirY - (2 * this.dirY);
}

public void toLow(){
	if(this.pongball.getY()< this.window.getHeight())
		this.dirY = this.dirY - (2 * this.dirY)
}
//TODO ändra lite i den här//
private void changeAngle(){
	if(hitLeftBar()){
		if(this.getBarPos(BarKey.Left) < this.pongBall.getY()){
			Y--;
				}
		else{
			Y++
			}
		}
	else if(hitRightBar()){
		if(this.getBarPos(BarKey.Right) < this.pongBall.getY()){
			Y--;
		}
		else{
			Y++;
		}
	}
}


public boolean hitRightBar(){
	if((this.getBarPos(BarKey.Right)+(this.getBarHeight(BarKey.Right)/2))>= this.pongBall.getY() && (this.getBarPos(BarKey.Right)-(this.getBarHeight(BarKey.Right)/2)) <= this.pongBall.getY())
		return true;
	else{
		return false;
	}
	
}

public boolean hitLeftBar(){
	if((this.getBarPos(BarKey.Left)+(this.getBarHeight(BarKey.Left)/2))>= this.pongBall.getY() && (this.getBarPos(BarKey.Left)-(this.getBarHeight(BarKey.Left)/2)) <= this.pongBall.getY())
		return true;
	else{
		return false;
	}
}
	public int getBarPos(BarKey k) {
		switch(k){
		case RIGHT:
			return rightPos;
		case LEFT:
			return leftPos;
		default:
			return 0;
		}
	}

	@Override
	public int getBarHeight(BarKey k) {
		switch(k);
		case RIGHT:
			return rightsize;
		case LEFT:
			return leftsize;
		default:
			return 0;
	}

	@Override
	public Point getBallPos() {
		return pongBall;
	}

	@Override
	public String getMessage() {
		
		return this.message;
	}

	@Override
	public String getScore(BarKey k) {
		switch(k);
		case RIGHT:
			return Integer.toString(player1score);
		case LEFT:
			return Integer.toString(player2score);
		default:
		return "0";
	}

	@Override
	public Dimension getFieldSize() {
		return this.window;
	}

}
