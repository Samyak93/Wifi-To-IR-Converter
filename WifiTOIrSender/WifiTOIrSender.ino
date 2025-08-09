
#include <IRremoteESP8266.h>
#include <IRrecv.h>
#include <IRsend.h>
#include <IRutils.h>
#include <ESP8266WiFi.h>

// An IR detector/demodulator is connected to GPIO pin 12
#define RECV_PIN 12
#define BAUD_RATE 115200
#define CAPTURE_BUFFER_SIZE 1024
#define TIMEOUT 50U
#define MIN_UNKNOWN_SIZE 12

// Use turn on the save buffer feature for more complete capture coverage.
IRrecv irrecv(RECV_PIN, CAPTURE_BUFFER_SIZE, TIMEOUT, true);
IRsend irsend(14); //an IR led is connected to GPIO pin 0
void ConnectionToClient(void);

//TV
uint16_t tempUpData[71] = {9076, 4532,  534, 600,  532, 602,  530, 1724,  532, 600,  534, 600,  536, 598,  530, 604,  530, 604,  530, 1724,  534, 1720,  534, 598,  534, 1720,  540, 1714,  534, 1720,  532, 1722,  534, 1720,  534, 598,  532, 1722,  534, 600,  536, 598,  536, 596,  538, 596,  532, 600,  542, 592,  534, 1720,  538, 596,  534, 1720,  536, 1718,  532, 1720,  536, 1716,  538, 1718,  534, 1718,  536, 39962,  8980, 2304,  522};  // NEC 20DF40BF
uint16_t OFFData[71] = {9058, 4488,  562, 572,  556, 578,  554, 1700,  552, 582,  552, 580,  558, 576,  554, 578,  554, 578,  552, 1702,  550, 1704,  554, 578,  556, 1698,  556, 1700,  556, 1698,  556, 1696,  560, 1696,  552, 580,  556, 576,  554, 580,  556, 1698,  556, 578,  552, 582,  552, 582,  550, 584,  550, 1704,  554, 1700,  552, 1702,  548, 584,  546, 1710,  550, 1704,  552, 1702,  552, 1702,  548, 39954,  8984, 2304,  546};  // NEC 20DF10EF
uint16_t ONData[71] = {9150, 4348,  718, 420,  712, 422,  710, 1544,  710, 436,  698, 438,  694, 428,  706, 436,  696, 424,  710, 1560,  694, 1562,  696, 436,  696, 1558,  696, 1558,  696, 1560,  694, 1560,  696, 1558,  696, 438,  696, 436,  696, 438,  698, 1556,  698, 434,  744, 390,  746, 388,  702, 432,  686, 1570,  684, 1570,  674, 1582,  664, 472,  658, 1596,  656, 1598,  650, 1604,  648, 1612,  642, 39874,  8946, 2360,  492};  // NEC 20DF10EF
uint16_t tempDownData[71] = {9038, 4512,  556, 578,  554, 578,  556, 1698,  560, 574,  556, 576,  556, 578,  558, 576,  556, 576,  556, 1698,  554, 1702,  556, 576,  556, 1696,  564, 1690,  560, 1694,  560, 1694,  560, 1696,  560, 1694,  562, 1690,  560, 572,  558, 576,  558, 576,  556, 578,  556, 578,  560, 574,  558, 574,  556, 578,  560, 1694,  558, 1696,  558, 1696,  562, 1692,  556, 1698,  558, 1696,  560, 39960,  9000, 2262,  562};  // NEC 20DFC03F

const char* ssid = "wifi";
const char* password = "12345678";//192.168.43.198
WiFiServer server(80);

decode_results results;  // Somewhere to store the results

// The section of code run only once at start-up.
void setup() {

  Serial.begin(BAUD_RATE, SERIAL_8N1, SERIAL_TX_ONLY);

  // Wait for the serial connection to be establised.
  while (!Serial)
    delay(50);

  Serial.println();
  Serial.print("IRrecvDumpV2 is now running and waiting for IR input on Pin ");
  Serial.println(RECV_PIN);

  // Start the receiver
  irrecv.enableIRIn();
  irsend.begin();

  // WiFi setup
  Serial.print("Connecting to ");
  Serial.println(ssid);
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
    Serial.println("");
    Serial.println("WiFi connected");

    // Start the server
    server.begin();
    Serial.println("Server started");

    delay(5000);
    // Print the IP address
    Serial.print("Use this URL : ");
    Serial.print("http://");
    Serial.print(WiFi.localIP());
    Serial.println("/");

    delay(1000);
  }
  pinMode(LED_BUILTIN, OUTPUT);
  digitalWrite(LED_BUILTIN, HIGH);
}
// The repeating section of the code
void loop()
{
  ConnectionToClient();
  delay(1000);
}


void ConnectionToClient()
{
  // Serial.println("In ConectionToClient Method");
  WiFiClient client = server.available();
  if (!client) {
    return;
  }

  // Wait until the client sends some data
  Serial.println("new client");
  if (!client.available()) {
    Serial.println("Client Disconnected.");
    delay(1);
  }

  if (client.available())
  {
    // Read the first line of the request
    String request = client.readStringUntil('\r');
    Serial.println(request);

    //Training...
    if (request.equals("a")) {
      delay(500);
      client.print(readIR());
    }
    else
    {
      int nextComma = 0;      
      nextComma = request.indexOf(',');
      String array_size = request.substring(0, nextComma);
      Serial.print("array_size:");
      Serial.println(array_size);
      uint16_t finalDataArray[array_size.toInt()];
      int arrayIndex = 0;

      request = request.substring(nextComma + 1);
      while (request.length() > 2)
      {
        nextComma = request.indexOf(',');
        String sValue = request.substring(0, nextComma);

        if (nextComma != -1)
        {
          Serial.print("sValue:");
          Serial.println(sValue);
          finalDataArray[arrayIndex++] = (int)sValue.toInt();
        }
        else
        {
          Serial.print("sValue:");
          Serial.println(request);
          finalDataArray[arrayIndex++] = (int)request.toInt();
          break;
        }
        request = request.substring(nextComma + 1);
      }
      delay(1000);
      Serial.println("a rawData is sent to IR");
      irsend.sendRaw(finalDataArray, array_size.toInt(), 38);
    }

  }
  delay(1);
  client.stop();
  Serial.println("Client disconnected");
  Serial.println("");
  delay(1000);
}

/*void ConnectionToClient()
  {
  Serial.println("In ConectionToClient Method");
  WiFiClient client = server.available();
  if (!client) {
    return;
  }

  // Wait until the client sends some data
  Serial.println("new client");
  if (!client.available()) {
    Serial.println("Client Disconnected.");
    delay(1);
  }

  if (client.available())
  {
    // Read the first line of the request
    String request = client.readStringUntil('\r');
    Serial.println(request);

    // Match the request
    if (request.equals("1")) {
      sendIR("ON");
    }
    else if (request.equals("2")) {
      sendIR("OFF");
    }
    else if (request.equals("3")) {
      sendIR("up");
    }
    else if (request.equals("4")) {
      sendIR("down");
    }

    //Training...
    else if (request.equals("a")) {
      delay(500);
      client.print(readIR());
    }
  }
  delay(1);
  client.stop();
  Serial.println("Client disconnected");
  Serial.println("");
  delay(1000);
  }*/

/*void sendIR(String command) {

  Serial.print("Command");
  Serial.println(command);
  if (command.equals("ON"))
  {
    Serial.println("a rawData capture from IRrecvDumpV2 to ON");
    irsend.sendRaw(ONData, 71, 38);
  }
  else if (command.equals("OFF"))
  {
    Serial.println("a rawData capture from IRrecvDumpV2 to OFF");
    irsend.sendRaw(OFFData, 71, 38);
  }
  else if (command.equals("up"))
  {
    Serial.println("a rawData capture from IRrecvDumpV2 to UP");
    irsend.sendRaw(tempUpData, 71, 38);
  }
  else if (command.equals("down"))
  {
    Serial.println("a rawData capture from IRrecvDumpV2 to down");
    irsend.sendRaw(tempDownData, 71, 38);
  }

}*/

String readIR() {

  digitalWrite(LED_BUILTIN, LOW);
  delay(200);
  digitalWrite(LED_BUILTIN, HIGH);
  delay(200);
  digitalWrite(LED_BUILTIN, LOW);
  delay(200);
  digitalWrite(LED_BUILTIN, HIGH);
  delay(200);
  digitalWrite(LED_BUILTIN, LOW);
  delay(200);
  digitalWrite(LED_BUILTIN, HIGH);
  delay(200);
  digitalWrite(LED_BUILTIN, LOW);
  delay(200);
  digitalWrite(LED_BUILTIN, HIGH);
  delay(200);
  digitalWrite(LED_BUILTIN, LOW);
  delay(200);
  digitalWrite(LED_BUILTIN, HIGH);
  delay(200);
  digitalWrite(LED_BUILTIN, LOW);
  delay(1000);
  digitalWrite(LED_BUILTIN, HIGH);

  // Check if the IR code has been received.
  String str = "";
  while (str.length() < 200)
  {
    Serial.println("IR Not received yet... Try again...");
    if (irrecv.decode(&results)) {
      //str = uint64ToString(getCorrectedRawLength(&results), 10);
      str = resultToSourceCode(&results);
      //Serial.println(str);
      //Serial.println("");  // Blank line between entries
      yield();  // Feed the WDT (again)
    }
    delay(1000);
  }
  digitalWrite(LED_BUILTIN, LOW);
  Serial.println(resultToSourceCode(&results));
  Serial.println("");  // Blank line between entries
  return resultToSourceCode(&results);
}
