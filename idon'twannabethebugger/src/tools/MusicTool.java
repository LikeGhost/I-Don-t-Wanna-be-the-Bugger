package tools;

import javax.naming.Context;
import javax.sound.sampled.*;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;

public class MusicTool {
    public static Clip jump,doubleJump,death,appleTrap,spikeTrap,blockChange,blockDown,airplaneTrap,save,victory;


    public static void init(){
        try {
            AudioInputStream jumpStream = AudioSystem.getAudioInputStream(new File("music/Jump.wav"));
            AudioInputStream doubleJumpStream = AudioSystem.getAudioInputStream(new File("music/DoubleJump.wav"));
            AudioInputStream deathStream = AudioSystem.getAudioInputStream(new File("music/Death.wav"));
            AudioInputStream appleTrapStream = AudioSystem.getAudioInputStream(new File("music/AppleTrap.wav"));
            AudioInputStream spikeTrapStream = AudioSystem.getAudioInputStream(new File("music/SpikeTrap.wav"));
            AudioInputStream blockChangeStream = AudioSystem.getAudioInputStream(new File("music/BlockChange.wav"));
            AudioInputStream blockDownStream = AudioSystem.getAudioInputStream(new File("music/BlockDown.wav"));
            AudioInputStream airplaneTrapStream = AudioSystem.getAudioInputStream(new File("music/AirplaneTrap.wav"));
            AudioInputStream saveStream = AudioSystem.getAudioInputStream(new File("music/Save.wav"));
            AudioInputStream victoryStream = AudioSystem.getAudioInputStream(new File("music/victory.wav"));

            jump=AudioSystem.getClip();
            doubleJump=AudioSystem.getClip();
            death=AudioSystem.getClip();
            appleTrap=AudioSystem.getClip();
            spikeTrap=AudioSystem.getClip();
            blockDown=AudioSystem.getClip();
            blockChange=AudioSystem.getClip();
            airplaneTrap=AudioSystem.getClip();
            save=AudioSystem.getClip();
            victory=AudioSystem.getClip();

            jump.open(jumpStream);
            doubleJump.open(doubleJumpStream);
            death.open(deathStream);
            appleTrap.open(appleTrapStream);
            spikeTrap.open(spikeTrapStream);
            blockDown.open(blockDownStream);
            blockChange.open(blockChangeStream);
            airplaneTrap.open(airplaneTrapStream);
            save.open(saveStream);
            victory.open(victoryStream);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

    }


    public static void play(Clip music){
        if(music.isRunning()){
            music.stop();
        }
        music.setFramePosition(0);
        music.start();
        music.flush();
    }
    public static void reset(){
        jump.stop();
        doubleJump.stop();
        death.stop();
        appleTrap.stop();
        spikeTrap.stop();
        blockDown.stop();
        blockChange.stop();
        airplaneTrap.stop();
        save.stop();
        victory.stop();

        jump.flush();
        doubleJump.flush();
        death.flush();
        appleTrap.flush();
        spikeTrap.flush();
        blockDown.flush();
        blockChange.flush();
        airplaneTrap.flush();
        save.flush();
        victory.flush();
    }
}
