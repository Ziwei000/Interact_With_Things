import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import net.sf.json.JSONObject;

public class Demo {
    public static void main(String[] args) {
    	Scanner in = new Scanner(System.in);
        OutputStreamWriter osw;
        BufferedWriter rw;
        try {

            while(true) {
            	//create socket to send service call to rpi ip...(192.168.0.41  10.254.0.2)
            	Socket socket = new Socket("localhost", 6668);
                osw = new OutputStreamWriter(socket.getOutputStream());
                rw = new BufferedWriter(osw);
                System.out.println("----Menu----");
                System.out.println("1.BlinkLED(blink the led for input number of times)");
                System.out.println("2.Button(press the button to light up led with input time limit)");
                System.out.println("3.Buzzer ring(ring the buzzer for input number of times)");
                System.out.println("4.Two-color-led blink(blink the colorled for input number of times)");
                System.out.println("Please enter service number...");
                //select a service and corresponding service call will be made
                int serviceN = 0;
                serviceN = in.nextInt();
                switch(serviceN) {
                case 1 :
                	//blink led for "input" number of times
                	JSONObject jsout1 = new JSONObject();
                	jsout1.put("Tweet Type", "Service call");
                    jsout1.put("Thing ID", "MySmartThing01");
                    jsout1.put("Space ID", "MySmartSpace");
                    jsout1.put("Service Name", "led_blink");
                    //the input number is how many time you want the led to blink, you can modify it by the way you like
                    jsout1.put("Service Inputs", "(5)");
                    rw.write(jsout1.toString()+"\n");
                    rw.flush();
                    rw.close();
                    socket.close();
                	break;
                case 2 :
                	//tap the button to light up the led, and led will be off after input time limit
                	JSONObject jsout2 = new JSONObject();
                	jsout2.put("Tweet Type", "Service call");
                    jsout2.put("Thing ID", "MySmartThing01");
                    jsout2.put("Space ID", "MySmartSpace");
                    jsout2.put("Service Name", "button_tolight_led");
                    //input integer for time of lighting up the light(/ms), you can modify it by the way you like
                    jsout2.put("Service Inputs", "(3000)");
                    rw.write(jsout2.toString()+"\n");
                    rw.flush();
                    rw.close();
                    socket.close();
                	break;
                case 3 :
                	//ring the buzzer for "input" number of times
                	JSONObject jsout3 = new JSONObject();
                	jsout3.put("Tweet Type", "Service call");
                    jsout3.put("Thing ID", "MySmartThing01");
                    jsout3.put("Space ID", "MySmartSpace");
                    jsout3.put("Service Name", "buzzer_ring");
                    //the input number is how many time you want the buzzer to ring, you can modify it by the way you like
                    jsout3.put("Service Inputs", "(2)");
                    rw.write(jsout3.toString()+"\n");
                    rw.flush();
                    rw.close();
                    socket.close();
                	break;
                case 4 :
                	//blink the two color led for "input" number of times
                	JSONObject jsout4 = new JSONObject();
                	jsout4.put("Tweet Type", "Service call");
                    jsout4.put("Thing ID", "MySmartThing01");
                    jsout4.put("Space ID", "MySmartSpace");
                    jsout4.put("Service Name", "color_led_blink");
                    //the input number is how many time you want the colorled to blink, you can modify it by the way you like
                    jsout4.put("Service Inputs", "(7)");
                    rw.write(jsout4.toString()+"\n");
                    rw.flush();
                    rw.close();
                    socket.close();
                	break;
                default :
                	System.out.println("Invalid service number, please enter again...");
                	rw.close();
                    socket.close();
                	break;
                }
            }

           // socket.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
