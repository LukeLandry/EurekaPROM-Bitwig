package com.honeyflamemusic.bitwig.eurekaprom;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.callback.ShortMidiDataReceivedCallback;
import com.bitwig.extension.controller.api.*;
import com.bitwig.extension.controller.ControllerExtension;

public class EurekaPROMBitwigExtension extends ControllerExtension {
    protected EurekaPROMBitwigExtension(final EurekaPROMBitwigExtensionDefinition definition, final ControllerHost host) {
        super(definition, host);
    }

    private Model model;

    @Override
    public void init() {
        model = Model.getInstance();
        final ControllerHost host = getHost();
        final MidiIn midiIn = host.getMidiInPort(0);
        final MidiOut midiOut = host.getMidiOutPort(0);

        final CursorTrack cursorTrack = host.createCursorTrack(0, 0);
        final CursorDevice cursorDevice = cursorTrack.createCursorDevice();

        final CursorRemoteControlsPage cursorRemoteControlsPage = cursorDevice.createCursorRemoteControlsPage("pedals", 8, "pedals");

        for (int i = 0; i < 8; i++) {
            cursorRemoteControlsPage.getParameter(i).markInterested();
            cursorRemoteControlsPage.getParameter(i).value().markInterested();
        }

        model.setHost(host);
        model.setMidiIn(midiIn);
        model.setMidiOut(midiOut);
        model.setCursorRemoteControlsPage(cursorRemoteControlsPage);

        midiIn.setMidiCallback(this::onMidi);
        midiIn.createNoteInput("FCB1010 EurekaPROM", "B066??", "B067??");
        //TODO Setup feedback circuit


        // TODO: Perform your driver initialization here.
        // For now just show a popup notification for verification that it is running.
        host.showPopupNotification("EurekaPROM Bitwig Initialized");
    }

    private void onMidi(int status, int data1, int data2) {
        if (data1 == 104) {
            int paramNumber = -1;
            if (data2 >= 1 && data2 <= 4) {
                paramNumber = data2 + 3;
            } else if (data2 >= 6 && data2 <= 9) {
                paramNumber = data2 - 6;
            }
            if (paramNumber >= 0) {
                final RemoteControl control = model.getCursorRemoteControlsPage().getParameter(paramNumber);
                double newValue = control.value().get() < 0.5 ? 1.0 : 0.0;
                control.value().setImmediately(newValue);
            } else {
//                getHost().showPopupNotification("Pressed button " + data2 + ", not mapped");
            }
        }
    }

    @Override
    public void exit() {
        // TODO: Perform any cleanup once the driver exits
        // For now just show a popup notification for verification that it is no longer running.
        getHost().showPopupNotification("EurekaPROM Bitwig Exited");
    }

    @Override
    public void flush() {
        // TODO Send any updates you need here.

    }


}
