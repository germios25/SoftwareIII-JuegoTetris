package TetrisAvanzado;

import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.swing.JOptionPane;

public class AudioPlayer{
    private Sequence gameMusic;
    private Sequencer player;
    private boolean initialized;

    public AudioPlayer(){
        try{
          this.gameMusic = MidiSystem.getSequence(new File("src/musica/musica.mid"));
        }catch (InvalidMidiDataException | IOException e){
          JOptionPane.showMessageDialog(null, "Incapaz para recuperar archivo de música");
          return;
        }
        try{
          this.player = MidiSystem.getSequencer();
          this.player.setSequence(this.gameMusic);
          this.player.open();
        }catch (MidiUnavailableException | InvalidMidiDataException e){
          JOptionPane.showMessageDialog(null, "Error de Secuencia!");
          return;
        }
      this.player.setLoopCount(-1);
      this.initialized = true;
    }
    public void sonido(String cadena){
        AudioClip sonido=java.applet.Applet.newAudioClip(getClass().getResource(cadena));
        sonido.play();
    }
    public void start(){
      this.player.start();
    }

    public void stop(){
      this.player.stop();
    }

    public boolean Initialized(){
      return this.initialized;
    }
}
