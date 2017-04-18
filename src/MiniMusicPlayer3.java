import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;

public class MiniMusicPlayer3 {
    static JFrame frame = new JFrame("My First Music Video");
    static MyDrawPanel ml;

    public static void main(String[] args) {
        MiniMusicPlayer3 mini = new MiniMusicPlayer3();
        mini.go();
    }

    public void setUpGui(){
        ml = new MyDrawPanel();
        frame.setContentPane(ml);
        frame.setBounds(30,30,300,300);
        frame.setVisible(true);
    }

    public void go(){
        setUpGui();

        try {
            Sequencer sequencer = MidiSystem.getSequencer();   //make a Sequencer
            sequencer.open();                                  //and open
            sequencer.addControllerEventListener(ml, new int[] {127});
            Sequence seq = new Sequence(Sequence.PPQ, 4);   //make a sequence
            Track track = seq.createTrack();                          //make a track

            for (int i = 5; i < 100; i += 1) { //the notes keep going up (from piano note 5 to piano note 100)
                track.add(makeEvent(144, 1, i, 100, i));
                track.add(makeEvent(176,1,127,0, i));
                track.add(makeEvent(128, 1, i, 100, i + 2));
            }

            sequencer.setSequence(seq);
            sequencer.setTempoInBPM(220);
            sequencer.start();              //start it running
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage a = new ShortMessage();    //make a message
            a.setMessage(comd, chan, one, two);     //using its parameters
            event = new MidiEvent(a, tick);         //make a event
        } catch (Exception e) {
        }
        return event; //return event (MidiEvent)
    }



    class MyDrawPanel extends JPanel implements ControllerEventListener{
        boolean msg = false;    //set it true only when we get an event

        public void controlChange(ShortMessage event) {
            msg = true; //we got an event, so we set the flag to true
            repaint();
        }

        public void paintComponent(Graphics g){
            if(msg){
                Graphics2D g2 = (Graphics2D) g;

                int red = (int) (Math.random() * 250);
                int green = (int) (Math.random() * 250);
                int blue = (int) (Math.random() * 250);

                g.setColor(new Color(red, green, blue));

                int height = (int) ((Math.random() * 120) + 10);
                int width = (int) ((Math.random() * 120) + 10);
                int x = (int) ((Math.random() * 40) + 10);
                int y = (int) ((Math.random() * 40) + 10);
                g.fillRect(x, y, height, width);
                msg = false;
            }
        }
    }
}