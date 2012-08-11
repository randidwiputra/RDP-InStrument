/*
 *
 * Copyright (c) 2010, 
 * Randi Dwi Putra a.k.a Godenk Haxor Binary a.k.a Dayax Geeks
 * a.k.a d4y4x a.k.a high_anonymous a.k.a Matahari Malam at RDP Labs.
 *
 *
 * contact me : 
 *               d4y4x74@yahoo.com randi.dwi.putra@gmail.com
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  * Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *  * Neither the name of Sun Microsystems nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.MidiSystem;

@SuppressWarnings("serial")
public class RDPInstrumentMusic extends JFrame {

  MidiChannel channel;
  static JButton drums, pianos, stop;

  int volume = 100;
  boolean mute = true;

  public RDPInstrumentMusic(final Synthesizer synth) {
    super("RDP Instrument Music");
    drums = new JButton("Drums Instrument");
    pianos = new JButton("Pianos Instrument");
    stop = new JButton("Stop");

    stop.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
    channel.getMute();
    }
  });

   drums.addKeyListener(new KeyAdapter() {
   public void keyPressed(KeyEvent e) {
   channel = synth.getChannels()[9];
   int key = e.getKeyCode();

   if (key >= 35 && key <= 127) {
     channel.noteOn(key, volume);
     }
   }
});

   pianos.addKeyListener(new KeyAdapter() {
    public void keyPressed(KeyEvent e) {
    channel = synth.getChannels()[0];
    int key = e.getKeyCode();

   if (key >= 35 && key <= 127) {
     channel.noteOn(key, volume);
     }
   }
  });
}

  public static void main(String[] args) throws MidiUnavailableException {
    Synthesizer synthesizer = MidiSystem.getSynthesizer();
    synthesizer.open();

    JFrame frame = new RDPInstrumentMusic(synthesizer);
    frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);

    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout());
    panel.add(stop);
    panel.add(pianos);
    panel.add(drums);

    frame.add(panel);
    frame.pack();
    frame.setLocation(300, 300);
    frame.setVisible(true);
    }
  }
