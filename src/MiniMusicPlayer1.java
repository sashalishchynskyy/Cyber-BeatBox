import javax.sound.midi.*;

public class MiniMusicPlayer1 {
    public static void main(String[] args) {
        try {
            Sequencer sequencer = MidiSystem.getSequencer();   //make a Sequencer
            sequencer.open();                                  //and open

            Sequence seq = new Sequence(Sequence.PPQ, 4);   //make a sequence
            Track track = seq.createTrack();                          //make a track

            for (int i = 5; i < 61; i += 2) { //the notes keep going up (from piano note 5 to piano note 61)
                track.add(makeEvent(144, 1, i, 100, i));
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
}