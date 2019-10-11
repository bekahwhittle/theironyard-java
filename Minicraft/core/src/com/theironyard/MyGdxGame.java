package com.theironyard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture tiles;

	static final float MAX_VELOCITY = 100;
	static final float FRICTION = 0.7f;
	static final int WIDTH = 16;
	static final int HEIGHT = 16;
	static final int DRAW_WIDTH = WIDTH * 3;
	static final int DRAW_HEIGHT = HEIGHT * 3;
	static final int GRAVITY = 0;

	TextureRegion up, down, left, right;

	String direction = "goDown";
	Animation walkRight, walkLeft, walkUp, walkDown;
	float x, y, xv, yv, totalTime;

	@Override
	public void create () {
		batch = new SpriteBatch();
		Texture tiles;
		tiles = new Texture("tiles.png");
		TextureRegion [][] view = TextureRegion.split(tiles, WIDTH,HEIGHT);
		up = view[6][1];
		down = view [6] [0];
		right = view [6][3];
		left = new TextureRegion(right);
		left.flip(true, false);
		TextureRegion walkLeftA = new TextureRegion(view[6][2]);
		TextureRegion walkLeftB = new TextureRegion(view[6][3]);
		walkLeftA.flip(true, false);
		walkLeftB.flip(true, false);
		walkRight = new Animation(FRICTION,view[6][2],view [6][3]);
		walkLeft = new Animation(FRICTION, walkLeftA, walkLeftB);
		TextureRegion walkUpA = new TextureRegion(view[6][1]);
		TextureRegion walkUpB = new TextureRegion(view [6][1]);
		walkUpB.flip(true,false);
		walkUp = new Animation(FRICTION, walkUpA, walkUpB);
		TextureRegion walkDownA = new TextureRegion(view[6][0]);
		TextureRegion walkDownB = new TextureRegion(view [6][0]);
		walkDownB.flip(true,false);
		walkDown = new Animation(FRICTION, walkDownA, walkDownB);
	}

	@Override
	public void render () {
		totalTime += Gdx.graphics.getDeltaTime();
		move();

		TextureRegion miniMan = new TextureRegion();

		if(direction.equals("goRight")){
			if(xv != 0){
				miniMan = walkRight.getKeyFrame(totalTime,true);
			}
			else {
				miniMan = right;
			}
		}
		if(direction.equals("goLeft")){
			if(xv != 0){
				miniMan = walkLeft.getKeyFrame(totalTime,true);
			}
			else {
				miniMan = left;
			}
		}
		if(direction.equals("goUp")){
			if(yv != 0){
				miniMan = walkUp.getKeyFrame(totalTime, true);
			}
			else {
				miniMan = up;
			}
		}
		if(direction.equals("goDown")){
			if(yv != 0){
				miniMan = walkDown.getKeyFrame(totalTime, true);
			}
			else {
				miniMan = down;
			}
		}

		Gdx.gl.glClearColor(0.0f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(miniMan, x, y, DRAW_WIDTH, DRAW_HEIGHT);
		batch.end();
	}


	@Override
	public void dispose () {
		batch.dispose();
//		tiles.dispose();
	}

	private void move() {
		if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)){
			yv = MAX_VELOCITY;
			direction = "goUp";
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			yv = MAX_VELOCITY * -1;
			direction = "goDown";
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			xv = MAX_VELOCITY;
			direction = "goRight";
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			xv = MAX_VELOCITY *-1;
			direction = "goLeft";
		}
		if ((Gdx.input.isKeyPressed(Input.Keys.SPACE) && Gdx.input.isKeyPressed(Input.Keys.W)) ||
			(Gdx.input.isKeyPressed(Input.Keys.SPACE) && Gdx.input.isKeyPressed(Input.Keys.UP))){
			yv = MAX_VELOCITY * 5;
			direction = "goUp";
		}
		else if ((Gdx.input.isKeyPressed(Input.Keys.SPACE) && Gdx.input.isKeyPressed(Input.Keys.S)) ||
				Gdx.input.isKeyPressed(Input.Keys.SPACE) && Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			yv = MAX_VELOCITY * -5;
			direction = "goDown";
		}
		else if ((Gdx.input.isKeyPressed(Input.Keys.SPACE) && Gdx.input.isKeyPressed(Input.Keys.A)) ||
				Gdx.input.isKeyPressed(Input.Keys.SPACE) && Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			xv = MAX_VELOCITY * -5;
			direction = "goLeft";
		}
		else if ((Gdx.input.isKeyPressed(Input.Keys.SPACE) && Gdx.input.isKeyPressed(Input.Keys.D)) ||
				Gdx.input.isKeyPressed(Input.Keys.SPACE) && Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			xv = MAX_VELOCITY * 5;
			direction = "goRight";
		}

		yv += GRAVITY;

		x += xv * Gdx.graphics.getDeltaTime();
		y += yv * Gdx.graphics.getDeltaTime();

		xv = decelerate(xv);
		yv = decelerate(yv);

		if (y < 0 - DRAW_HEIGHT) {
			y = Gdx.graphics.getHeight();
		}
		if (y > Gdx.graphics.getHeight()) {
			y = 0 - DRAW_HEIGHT;
		}

		if (x > Gdx.graphics.getWidth()) {
			x = 0 - DRAW_WIDTH;
		}
		if (x < 0-DRAW_WIDTH) {
			x = Gdx.graphics.getWidth();
		}
		System.out.println(xv + " " + yv);

	}

	public float decelerate(float velocity){
		velocity *= FRICTION;
		if(Math.abs(velocity) < 60){
			velocity = 0;
		}
		return velocity;
	}
}
