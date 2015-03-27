package ahmed.castro.mi.juego;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ClasePrincipal extends ApplicationAdapter{
	SpriteBatch batch;
    Texture character_textures[];
    Texture nemesis[];
    Texture fondo;
    Texture bg;
    Texture game_over_texture;


    int frame;
    int textura_actual;
    int nemesis_actual;

    double y;
    double velocidad_y;

    int bg_x;
    int enemigo_x;

    boolean game_over;

    double VELOCIDAD_INICIAL=20;
    double GRAVEDAD=-1;
    double PISO = 20;
    int VELOCIDAD=5;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
        character_textures = new Texture[3];
        nemesis = new Texture[3];
        nemesis[0] = new Texture("enemigo1.png");
        nemesis[1] = new Texture("enemigo2.png");
        nemesis[2] = new Texture("enemigo3.png");
        character_textures[0] = new Texture("1.png");
        character_textures[1] = new Texture("2.png");
        character_textures[2] = new Texture("3.png");
        fondo = new Texture("BackGround.png");
        bg = new Texture("bg.png");
        game_over_texture = new Texture("game_over.png");
        System.out.println(Gdx.graphics.getWidth());
        System.out.println(Gdx.graphics.getHeight());
        frame = 0;
        textura_actual = 0;
        nemesis_actual = 0;
        bg_x=0;
        enemigo_x=640;
        game_over=false;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        batch.draw(fondo,0,0);
        batch.draw(bg, bg_x, 0);
        batch.draw(bg, bg_x+640, 0);
        batch.draw(nemesis[nemesis_actual], enemigo_x, 20);
        batch.draw(character_textures[textura_actual], 0, (int)y);
        if(game_over)
            batch.draw(game_over_texture, 0, 0);
		batch.end();

        enemigo_x-=VELOCIDAD;
        if(enemigo_x<=-800)
            enemigo_x=640;

        if(enemigo_x<character_textures[textura_actual].getWidth()-9
                && enemigo_x-nemesis[nemesis_actual].getWidth()>0
                && y<200)
        {
            game_over=true;
        }

        bg_x-=VELOCIDAD;
        if(bg_x<=-640)
            bg_x=0;

        if(frame%20==0){

            textura_actual++;
            nemesis_actual++;
            if(textura_actual>2) {
                textura_actual = 0;
                nemesis_actual = 0;
            }
        }

        if(Gdx.input.justTouched())
        {
            velocidad_y=VELOCIDAD_INICIAL;
            game_over=false;
        }
        velocidad_y+=GRAVEDAD;
        y+=velocidad_y;
        if(y<PISO)
            y=PISO;

        frame++;

	}
}
