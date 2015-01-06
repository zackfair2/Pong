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
    private int verticalvector;
    private int horizonvector;
    private int leftHeight;
    private int rightHeight;
    private int rightPos;
    private int leftPos;
    private double ballspeed;
    private String message;
    private int stop;
    //private PongView view;	
	
	
	public MyPongModel(String string1, String string2){
		this.window = new Dimension(1200,800);
		this.pongBall = new Point(600,400);
		this.spelare1 = string1;
		this.spelare2 = string2;
		this.player1score = 0;
		this.player2score = 0;
		this.verticalvector = 0;
		this.horizonvector = -100;
		this.leftPos = 400;
		this.rightPos = 400;
		this.leftHeight = 200;
		this.rightHeight = 200;
		this.ballspeed = 1.0;
		this.message = "";
		this.stop = 0;
		}



    
	public void compute(Set<Input> input, long delta_t) {
	    moveBar(input);
	    checkCollision();
	    scorePoint();
	    updateBall();
	    
	    }
    
    public void gameOver(){
	if(this.player1score == 10){
	    this.message = "Player 1 won";
	    newGame();
	}
	if(this.player2score == 10){
	    this.message = "Player 2 won";
	    while(this.stop == 0){
	    }
	    newGame();
	}
    }
    public void scorePoint(){
	if(outofboundleft() && (!hitLeftBar())){
	    this.player2score++;
	    this.message = "Player 2 scored";
	    resetGame();
	}
    
	if(outofboundright() && (!hitRightBar())){
	    this.player1score++;
	    this.message = "Player 1 scored";
	    resetGame();
	}
	if(this.player1score == 10 || this.player2score == 10){
	    
	    gameOver();
	}
    }
    public void resetGame(){
	this.pongBall.setLocation(600,400);
	this.stop = 0;

}
    
    public void checkCollision(){
	toHigh();
	toLow();
	if(outofboundleft() || outofboundright()){
	    System.out.println("Collision");
	    if(hitRightBar() || hitLeftBar()){
		changeAngle();
		System.out.println("collision 2");
	    }
	}
     }


    public boolean outofboundleft(){
	if(this.pongBall.getX() <= 0){
	    return true;
	}
	return false;
    }
    public boolean outofboundright(){
	if(this.pongBall.getX() >= window.getWidth()){
	    return true;
	}
	return false;
    }

    public void updateBall(){
	this.pongBall.setLocation(((int)(this.pongBall.getX()-((this.horizonvector)*this.stop))),((int)(this.pongBall.getY()-this.verticalvector)));
	
	System.out.println(this.pongBall.toString());
}
    public void newGame(){
	this.pongBall.setLocation(600,400);
	this.player1score = 0;
	this.player2score = 0;
	this.verticalvector = -5;
	this.horizonvector = 50;
	this.leftPos = 400;
	this.rightPos = 400;
	this.leftHeight = 200;
	this.rightHeight = 200;
	this.ballspeed = 1;
	this.message = "Press space to play";
	this.stop = 0;
    }
	//TODO Change this//
private void moveBar(Set<Input> input){
	for(Input i : input){
		switch(i.key){
			case RIGHT:
				switch(i.dir){
				case UP:
					if(this.rightPos - (this.rightHeight/2) <= 0){
					    this.stop = 1;
							break;
					}
					else{
					    this.stop = 1;
					this.rightPos = this.rightPos - 20;
				break;
					}
			case DOWN:
				if(this.rightPos + (this.rightHeight/2) >= this.window.getHeight()){
				    this.stop = 1;
					break;
				}
				else{
				    this.stop = 1;
			this.rightPos = this.rightPos + 20;
			break;
				}
			}
			break;
		case LEFT:
			switch(i.dir){
			case UP:
					if(this.leftPos - (this.leftHeight/2) <= 0){
					    this.stop = 1;
						break;
					}
					else{
					    this.stop = 1;
						this.leftPos = this.leftPos - 20;
						break;
						}
				case DOWN:
						if(this.leftPos + (this.leftHeight/2) >= this.window.getHeight()){
						    this.stop = 1;
								break;
						}
						else{
						    this.stop = 1;
					this.leftPos = this.leftPos + 20;
					break;
						}
			}
			break;
		}
	}
}

public void toHigh(){
    if(this.pongBall.getY() > (this.window.getHeight()-10))
		this.verticalvector = this.verticalvector - (2 * this.verticalvector);
}

public void toLow(){
	if(this.pongBall.getY() < 10)
	    this.verticalvector = this.verticalvector - (2 * this.verticalvector);
}
//TODO ändra lite i den här//
private void changeAngle(){
    if(hitLeftBar()){
	this.horizonvector = (this.horizonvector*(-1));
    }
	else if(hitRightBar()){
	    this.horizonvector = (this.horizonvector*(-1));
	}
}


public boolean hitRightBar(){
    if(this.pongBall.getX() > 1190){
	System.out.println("hitRightbar");
	if((this.rightPos+(this.rightHeight/2))>= this.pongBall.getY() && (this.rightPos-(this.rightHeight/2)) <= this.pongBall.getY()){
	    System.out.println("hitrightbar true");
	    return true;
	}
    }
    return false;
}
 
public boolean hitLeftBar(){
    if(this.pongBall.getX() <10){
	if((this.leftPos+(this.leftHeight/2))>= this.pongBall.getY() && (this.leftPos-(this.leftHeight/2)) <= this.pongBall.getY())
		return true;
    }
    return false;
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
