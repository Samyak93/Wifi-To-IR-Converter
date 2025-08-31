# WiFi-to-IR Converter (ESP8266)

## Overview

This project enables you to control traditional IR-only appliances (TVs, A/Cs, set-top boxes, etc.) over Wi-Fi using an **ESP8266** board and a companion Android app. The ESP8266 acts as a Wi-Fi-enabled IR transmitter that sends IR signals to your devices, while the Android app allows you to trigger commands directly from your phone. The system works fully offline on your LAN without requiring any cloud services.

---

## System Architecture

The system consists of three primary components:

1. **ESP8266 IR Sender Module**  
   - **Wi-Fi Connectivity:** Connects the ESP8266 board to your local Wi-Fi network.  
   - **IR Transmission:** Sends pre-configured IR signals through an IR LED driven by a transistor circuit.  
   - **HTTP API:** Exposes endpoints to send commands or test connectivity.  
   - **Optional IR Learning:** If an IR receiver is attached, the system can capture codes from existing remotes.  

2. **Android Client App**  
   - **Connectivity:** Connects to the ESP8266 module over LAN using its IP address.  
   - **UI Controls:** Provides buttons mapped to IR codes (e.g., power, volume, input).  
   - **Customization:** Can be extended with new commands and layouts through the app source code.  

3. **Optional HTTP/REST Integration**  
   - **Endpoints:** Allows direct interaction with the ESP8266 using simple HTTP GET/POST requests.  
   - **Automation:** Can be integrated with scripts, smart home systems, or third-party apps for automation.  

---

## Hardware

- **ESP8266 Board:** NodeMCU v3 or Wemos D1 mini.  
- **IR LED (940 nm):** Driven by a transistor for higher current output.  
- **Transistor:** NPN type (2N2222 or BC547).  
- **Resistors:** ~220 Ω for LED limiting, ~1 kΩ for transistor base.  
- **IR Receiver (optional):** VS1838B or TSOP38238 for learning IR codes.  
- **Power Supply:** 5 V USB power supply (≥500 mA).  

**Example Pinout (ESP8266 NodeMCU):**
- IR Transmit pin: **D2 (GPIO4)**.  
- Optional IR Receive pin: **D5 (GPIO14)**.  

---

## Repository Layout
Wifi-To-IR-Converter/ <br>
├─ WifiTOIrSender/ # ESP8266 firmware code <br>
├─ WifiToIRClient/ # Android app source <br>
├─ wifiToIR.apk # Prebuilt Android app <br>
└─ README.md <br>


---

## Module Descriptions

### ESP8266 IR Sender Module
- Written for **ESP8266** with the `IRremoteESP8266` library.  
- Joins your Wi-Fi network (SSID and password configured in the firmware).  
- Hosts an HTTP server to receive commands.  
- Blinks the IR LED according to the stored IR code when triggered.  
- Optional IR learning mode captures codes via a connected IR receiver and logs them through Serial Monitor.

### Android Client Module
- Provides a simple interface to control appliances.  
- Connects to ESP8266 by entering its IP address.  
- Sends HTTP requests corresponding to buttons (e.g., `POWER`, `VOL+`, etc.).  
- Prebuilt APK available (`wifiToIR.apk`), or you can compile from `WifiToIRClient/`.

### HTTP / REST API
Typical API endpoints (adjust according to firmware):
- `GET http://<esp-ip>/send?key=POWER` → Send named command.  
- `GET http://<esp-ip>/sendhex?protocol=NEC&value=0x20DF10EF&bits=32` → Send raw hex command.  
- `GET http://<esp-ip>/ping` → Health check (`{"ok":true}`).  

---

## Integrated System Workflow

1. **Setup & Flashing:**  
   - Flash the ESP8266 with code from `WifiTOIrSender/`.  
   - Configure Wi-Fi credentials and IR pin in the firmware.  

2. **Deployment:**  
   - Place the ESP8266 near your device, pointing the IR LED toward it.  

3. **Android Control:**  
   - Install the APK and configure the ESP8266 IP.  
   - Use the buttons to send IR commands.  

4. **Automation (Optional):**  
   - Use HTTP endpoints to integrate with smart home platforms or scripts.  

---

## Environment Setup

### Arduino IDE / PlatformIO
- Add **ESP8266 Board Support** through Boards Manager.  
- Install libraries:  
  - [`IRremoteESP8266`](https://github.com/crankyoldgit/IRremoteESP8266)  
  - `ESP8266WiFi`  

### Android Studio (Optional)
- Open `WifiToIRClient/`.  
- Build and install on your phone if you prefer not to use the provided APK.  

---

## Troubleshooting

- **No IR Output:**  
  - Verify IR LED polarity and wiring.  
  - Use a phone camera to check for IR flicker.  
- **Wi-Fi Not Connecting:**  
  - Double-check SSID/PASSWORD in firmware.  
  - Ensure router supports 2.4 GHz.  
- **Device Doesn’t Respond:**  
  - Confirm protocol matches your remote (most use 38 kHz NEC/Sony).  
  - Increase number of repeats for volume/channel buttons.  
- **App Can’t Find Device:**  
  - Verify ESP8266 IP address and test `/ping`.  

---

## Future Work

- [ ] Add mDNS for automatic device discovery.  
- [ ] Support multiple ESP8266 IR modules (multi-room).  
- [ ] Enhance Android UI with custom button layouts.  
- [ ] Integrate MQTT for smart home platforms (e.g., Home Assistant).  

---

## Conclusion

The WiFi-to-IR Converter with ESP8266 provides a simple, affordable way to control traditional IR appliances over Wi-Fi. With both a firmware module and a companion Android app, the system is easy to deploy and extend. Optional learning and HTTP endpoints make it flexible for integration with smart home systems or custom scripts.  

For contributions or issues, please open a Pull Request or Issue on GitHub.

