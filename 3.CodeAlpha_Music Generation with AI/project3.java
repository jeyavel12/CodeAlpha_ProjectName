import javax.sound.midi.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    // -------- MIDI READER --------
    static class MidiReader {
        public static List<Integer> readMidi(String filePath) throws Exception {
            List<Integer> notes = new ArrayList<>();
            Sequence sequence = MidiSystem.getSequence(new File(filePath));

            for (Track track : sequence.getTracks()) {
                for (int i = 0; i < track.size(); i++) {
                    MidiEvent event = track.get(i);
                    MidiMessage message = event.getMessage();

                    if (message instanceof ShortMessage) {
                        ShortMessage sm = (ShortMessage) message;
                        if (sm.getCommand() == ShortMessage.NOTE_ON) {
                            notes.add(sm.getData1());
                        }
                    }
                }
            }
            return notes;
        }
    }

    // -------- SIMPLE AI MODEL --------
    static class SimpleMusicModel {
        private List<Integer> trainedNotes = new ArrayList<>();
        private Random random = new Random();

        public void train(List<Integer> notes) {
            trainedNotes.addAll(notes);
            System.out.println("Model trained with " + notes.size() + " notes");
        }

        public List<Integer> generateMusic(int length) {
            List<Integer> generated = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                int note = trainedNotes.get(random.nextInt(trainedNotes.size()));
                generated.add(note);
            }
            return generated;
        }
    }

    // -------- MIDI WRITER --------
    static class MidiWriter {
        public static void writeMidi(List<Integer> notes, String fileName) throws Exception {
            Sequence sequence = new Sequence(Sequence.PPQ, 4);
            Track track = sequence.createTrack();

            int time = 0;
            for (int note : notes) {
                ShortMessage on = new ShortMessage();
                on.setMessage(ShortMessage.NOTE_ON, 0, note, 100);
                track.add(new MidiEvent(on, time));

                ShortMessage off = new ShortMessage();
                off.setMessage(ShortMessage.NOTE_OFF, 0, note, 100);
                track.add(new MidiEvent(off, time + 2));

                time += 4;
            }

            MidiSystem.write(sequence, 1, new File(fileName));
            System.out.println("Output MIDI saved as " + fileName);
        }
    }

    // -------- MAIN METHOD --------
    public static void main(String[] args) {
        try {
            List<Integer> notes = MidiReader.readMidi("input.mid");

            SimpleMusicModel model = new SimpleMusicModel();
            model.train(notes);

            List<Integer> newMusic = model.generateMusic(50);
            MidiWriter.writeMidi(newMusic, "output.mid");

            System.out.println("Music Generation Successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}