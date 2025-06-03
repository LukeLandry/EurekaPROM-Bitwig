package com.honeyflamemusic.bitwig.eurekaprom;
import java.util.UUID;

import com.bitwig.extension.api.PlatformType;
import com.bitwig.extension.controller.AutoDetectionMidiPortNamesList;
import com.bitwig.extension.controller.ControllerExtensionDefinition;
import com.bitwig.extension.controller.api.ControllerHost;

public class EurekaPROMBitwigExtensionDefinition extends ControllerExtensionDefinition
{
   private static final UUID DRIVER_ID = UUID.fromString("897ef364-6fd5-442e-94be-7c9bf5cc9cab");
   
   public EurekaPROMBitwigExtensionDefinition()
   {
   }

   @Override
   public String getName()
   {
      return "EurekaPROM Bitwig";
   }
   
   @Override
   public String getAuthor()
   {
      return "crowminded";
   }

   @Override
   public String getVersion()
   {
      return "0.1";
   }

   @Override
   public UUID getId()
   {
      return DRIVER_ID;
   }
   
   @Override
   public String getHardwareVendor()
   {
      return "Honey Flame Music";
   }
   
   @Override
   public String getHardwareModel()
   {
      return "EurekaPROM Bitwig";
   }

   @Override
   public int getRequiredAPIVersion()
   {
      return 22;
   }

   @Override
   public int getNumMidiInPorts()
   {
      return 1;
   }

   @Override
   public int getNumMidiOutPorts()
   {
      return 1;
   }

   @Override
   public void listAutoDetectionMidiPortNames(final AutoDetectionMidiPortNamesList list, final PlatformType platformType)
   {
   }

   @Override
   public EurekaPROMBitwigExtension createInstance(final ControllerHost host)
   {
      return new EurekaPROMBitwigExtension(this, host);
   }
}
