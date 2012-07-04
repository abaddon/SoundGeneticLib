package tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;

public class SynthNote {
	private static boolean	DEBUG = true;
	private Synthesizer synth = null;
	private int nChannelNumber = 4;
	private int nVelocity = 50; //93
	private int nDuration = 100;
	private MidiChannel	channel=null;
	
	
	public SynthNote(int channel, int velocity, int duration){
		try {
			synth = MidiSystem.getSynthesizer();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setChannell(channel);
		setVelocity(velocity);
		setDuration(duration);
	}
	public SynthNote(){
		try {
			synth = MidiSystem.getSynthesizer();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setChannell(int num){
		this.nChannelNumber=num;
		MidiChannel[]	channels = synth.getChannels();
		channel = channels[nChannelNumber];
	}
	
	public void setDuration(int value){
		this.nDuration=value;
	}
	
	public void setVelocity(int value){
		this.nVelocity=value;
	}
	
	public void stop(){
		synth.close();
	}
	
	public  void play(int[] pitchs)
	{
		try{
			synth.open();
		}catch (MidiUnavailableException e){
			e.printStackTrace();
			System.exit(1);
		}
		
		if (DEBUG) out("MidiChannel: " + channel);
		
		for(int i=0;i<pitchs.length;i++){
			channel.noteOn(pitchs[i], nVelocity);
			
			try{
				Thread.sleep(nDuration);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			/*
			 *	Turn the note off.
			 */
			channel.noteOff(pitchs[i]);
		}

		/* Close the synthesizer.
		 */
		synth.close();
		
	}
	
	public  void play(ArrayList<Integer> pitchs)
	{
		try{
			synth.open();
		}catch (MidiUnavailableException e){
			e.printStackTrace();
			System.exit(1);
		}
		
		if (DEBUG) out("MidiChannel: " + channel);
		
		for(Integer pitch: pitchs){
			channel.noteOn(pitch, nVelocity);
			try{
				Thread.sleep(nDuration);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			/*
			 *	Turn the note off.
			 */
			channel.noteOff(pitch);
		}

		/* Close the synthesizer.
		 */
		synth.close();
		
	}
	
	public File getMidi(){
		Track testmiditrack =null;
		Sequence testmidi;
		try {
			testmidi = new Sequence(Sequence.PPQ, 480);
			
			testmiditrack = testmidi.createTrack();
			
			ShortMessage sm = new ShortMessage();
			sm.setMessage(144, 72, 100);
			testmiditrack.add(new MidiEvent(sm, 0));
			testmiditrack.add(new MidiEvent(sm, 1));
			testmiditrack.add(new MidiEvent(sm, 2));
			testmiditrack.add(new MidiEvent(sm, 3));
			testmiditrack.add(new MidiEvent(sm, 4));
			
			MidiSystem.write(testmidi, 0, new File("miditest.mid"));
		
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	private static void printUsageAndExit()
	{
		out("SynthNote: usage:");
		out("java SynthNote [<channel>] <note_number> <velocity> <duration>");
		System.exit(1);
	}


	private static void out(String strMessage)
	{
		System.out.println(strMessage);
	}
}


