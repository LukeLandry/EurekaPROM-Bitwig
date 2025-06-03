package com.honeyflamemusic.bitwig.eurekaprom;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.CursorRemoteControlsPage;
import com.bitwig.extension.controller.api.MidiIn;
import com.bitwig.extension.controller.api.MidiOut;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Model {

    private static Model _model = new Model();

    public static Model getInstance() {
        return _model;
    }

    private ControllerHost host;
    private MidiIn midiIn;
    private MidiOut midiOut;
    private CursorRemoteControlsPage cursorRemoteControlsPage;

}
